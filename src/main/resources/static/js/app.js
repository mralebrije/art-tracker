(function() {
    var artTrackerApp = angular.module('artTrackerApp', ['ngTouch', 'ui.grid', 'ui.grid.pagination', 'ui.grid.edit', 'ui.bootstrap', 'schemaForm', 'ngMap', "chart.js"]);

    artTrackerApp.factory('ApiFactory', function($http) {
        return {
            getMuseums: function() {
                return $http.get('/art-tracker/museum');
            },
            postMuseum: function(data) {
                return $http.post('/art-tracker/museum', data);
            },
            deleteMuseum: function(id) {
                return $http.delete('/art-tracker/museum/' + id);
            },
            getArtOrganizations: function(id) {
                return $http.get('/art-tracker/organization/' + id);
            },
            getZipCodeStatistics: function() {
                return $http.get('/art-tracker/statistics/zip');
            },
            getCouncilDistrictStatistics: function() {
                return $http.get('/art-tracker/statistics/council');
            }
        }
    });

    artTrackerApp.controller('RowEditController', [
        RowEditController
    ]);
    artTrackerApp.controller('RowAddController', [
        RowAddController
    ]);
    artTrackerApp.controller('MuseumController', ['$scope', '$interval', 'ApiFactory', '$modal',
        MuseumController
    ]);

    function MuseumController($scope, $interval, ApiFactory, $modal) {
        var reloadInterval = 1200000;

        // VARIABLES
        angular.extend($scope, {
            gridOptions: {
                columnDefs: [{
                        name: 'Id',
                        field: 'id',
                        visible: false
                    },
                    {
                        name: 'Name',
                        field: 'name',
                        headerCellClass: 'purple'
                    },
                    {
                        name: 'Zip Code',
                        field: 'zip_code',
                        width: 80,
                        headerCellClass: 'red'
                    },
                    {
                        name: 'Neighborhood',
                        field: 'neighborhood',
                        width: 140
                    },
                    {
                        name: 'Council District',
                        field: 'council_district',
                        width: 110
                    },
                    {
                        name: 'Police District',
                        field: 'police_district',
                        width: 110
                    },
                    {
                        name: 'Address',
                        field: 'address',
                        width: 180
                    },
                    {
                        name: 'Actions',
                        editable: false,
                        enableFiltering: false,
                        enableSorting: false,
                        cellTemplate: 'action'
                    }
                ],

                paginationPageSize: 10,
                enablePaginationControls: false,
                enableSorting: true,
                enableColumnMenus: true,
                enableFiltering: true,
                showGridFooter: true,
                showColumnFooter: false,
                enableRowHashing: false,
                rowHeight: 40,
                gridApi2: ''
            },
            hideGrid: false,
            error: false,
            organizations: [{
                latitude: 39.299236,
                longitude: -76.609383
            }],
            selectedZip: '',
            positions: [],
            selectedOrganization: '',
            zipCodeLabels: [],
            zipCodeSeries: ['Museums', 'Art Organizations'],
            zipCodeData: [],
            maxZipCode: '',
            maxZipCodeTotal: '',
            maxZipCodeMuseums: '',
            maxZipCodeOrganizations: '',
            hideOrganizations: true,
            councilDistrictLabels: [],
            councilDistrictData: [],
            maxCouncilDistrict: '',
            maxCouncilDistrictMuseums: '',
            mapCenter: '39.299236, -76.609383'

        });

        // METHODS
        angular.extend($scope, {
            retrieveMuseums: retrieveMuseums,
            reload: reload,
            addMuseum: addMuseum,
            deleteMuseum: deleteMuseum,
            editMuseum: editMuseum,
            findOrganizations: findOrganizations,
            updateOrganizationInfo: updateOrganizationInfo,
            retrieveZipCodeStatistics: retrieveZipCodeStatistics,
            retrieveCouncilDistrictStatistics: retrieveCouncilDistrictStatistics
        });

        function retrieveMuseums() {
            ApiFactory.getMuseums().then(
                function(response) {
                    $scope.gridOptions.data = response.data.museums;

                    $scope.gridApi2.core.refresh();
                },
                function(error) {
                    handleApiError(error);
                });
        }

        function addMuseum() {
            var modalInstance = $modal.open({
                templateUrl: 'add',
                controller: ['ApiFactory', '$modalInstance', RowAddController],
                controllerAs: 'vm',
                resolve: {
                    ApiFactory: function() {
                        return ApiFactory
                    }
                }
            });

            modalInstance.result.then(function(transferData) {

                if (transferData.isSuccess) {
                    $scope.gridOptions.data.push(transferData.entity);
                    swal(
                        'Successful addition!',
                        'Museum has added',
                        'success'
                    );
                }
            });

        }

        function editMuseum(grid, row) {
            var modalInstance = $modal.open({
                templateUrl: 'edit',
                controller: ['ApiFactory', '$modalInstance', 'grid', 'row', RowEditController],
                controllerAs: 'vm',
                resolve: {
                    grid: function() {
                        return grid;
                    },
                    row: function() {
                        return row;
                    },
                    ApiFactory: function() {
                        return ApiFactory
                    }
                }
            });

            modalInstance.result.then(function(transferData) {

                if (transferData.isSuccess) {
                    swal(
                        'Successful edition!',
                        'Museum has been updated',
                        'success'
                    );
                }
            });

        }

        function deleteMuseum(row) {
            ApiFactory.deleteMuseum(row.entity.id).then(
                function(response) {
                    var i = $scope.gridOptions.data.indexOf(row.entity);
                    $scope.gridOptions.data.splice(i, 1);
                    swal(
                        'Successful deletion!',
                        'Museum has been removed',
                        'success'
                    );
                },
                function(error) {
                    handleApiError(error);
                });
        }

        function findOrganizations(row) {
            ApiFactory.getArtOrganizations(row.entity.zip_code).then(
                function(response) {

                    if (response.data.total > 0) {


                        $scope.organizations = response.data.organizations;
                        $scope.selectedZip = row.entity.zip_code;


                        updateOrganizationInfo($scope.organizations[0]);


                        swal(
                            response.data.total + ' Art Organizations found!',
                            'Great this museum is really close to other interesting art places that you can visit!',
                            'success'
                        );

                        $scope.hideOrganizations = false;

                        $scope.mapCenter= $scope.organizations[0].latitude + ',' + $scope.organizations[0].longitude ;

                    } else {
                        swal('No nearby Art Organizations found');
                    }

                },
                function(error) {
                    handleApiError(error);
                });
        }

        function updateOrganizationInfo(organization) {
            $scope.selectedOrganization = organization;


        }

        function retrieveZipCodeStatistics() {
            ApiFactory.getZipCodeStatistics().then(
                function(response) {

                    var museumsAmountList = [];
                    var organizationsAmountList = [];

                    angular.forEach(response.data.zipCodeStatisticsList, function(zipCodeStatistic, key) {
                        $scope.zipCodeLabels.push(zipCodeStatistic.zip_code);
                        museumsAmountList.push(zipCodeStatistic.museums_count);
                        organizationsAmountList.push(zipCodeStatistic.organizations_count);

                    });

                    $scope.zipCodeData.push(museumsAmountList);
                    $scope.zipCodeData.push(organizationsAmountList);

                    $scope.maxZipCode = response.data.maxZipCode.zip_code;
                    $scope.maxZipCodeMuseums = response.data.maxZipCode.museums_count;
                    $scope.maxZipCodeOrganizations = response.data.maxZipCode.organizations_count;

                    $scope.maxZipCodeTotal = $scope.maxZipCodeMuseums + $scope.maxZipCodeOrganizations;
                },
                function(error) {
                    handleApiError(error);
                });
        }

        function retrieveCouncilDistrictStatistics() {
            ApiFactory.getCouncilDistrictStatistics().then(
                function(response) {

                    angular.forEach(response.data.councilStatisticsList, function(councilStatistic, key) {
                        $scope.councilDistrictData.push(councilStatistic.museums_count);
                        $scope.councilDistrictLabels.push('Museums');
                    });

                    $scope.maxCouncilDistrict = response.data.maxCouncil.council_district;
                    $scope.maxCouncilDistrictMuseums = response.data.maxCouncil.museums_count;

                },
                function(error) {
                    handleApiError(error);
                });
        }

        function handleApiError(error) {
            swal(
                'Oops...',
                'Error in REST service call',
                'error'
            );
            if (error.config.errorCause === 1) {
                //do something
            }
        }

        function initialize() {
            $scope.error = false;

            $scope.gridOptions.onRegisterApi = function(gridApi) {
                $scope.gridApi2 = gridApi;
            }

            retrieveMuseums();
            retrieveZipCodeStatistics();
            retrieveCouncilDistrictStatistics();
            reload();
        }

        function reload() {
            $interval(retrieveMuseums, reloadInterval);
        }

        function init() {
            initialize();
        }
        init();
    }


    function RowEditController(ApiFactory, $modalInstance, grid, row) {
        var vm = this;
        vm.schema = {
            type: "object",
            properties: {
                name: {
                    type: 'string',
                    title: 'Name'
                },
                zip_code: {
                    type: 'string',
                    title: 'Zip Code'
                },
                neighborhood: {
                    type: 'string',
                    title: 'Neighborhood'
                },
                council_district: {
                    type: 'integer',
                    minimum: 0,
                    title: 'Council District'
                },
                police_district: {
                    type: 'string',
                    title: 'Police District'
                },
                address: {
                    type: 'string',
                    title: 'Address'
                }
            }
        };
        vm.form = [
            'name',
            'zip_code',
            'neighborhood',
            'council_district',
            'police_district',
            'address'
        ];
        vm.model = angular.copy(row.entity);
        vm.save = save;

        function save() {

            var parameter = JSON.stringify({
                id: vm.model.id,
                name: vm.model.name,
                zip_code: vm.model.zip_code,
                neighborhood: vm.model.neighborhood,
                council_district: vm.model.council_district,
                police_district: vm.model.police_district,
                address: vm.model.address
            });

            ApiFactory.postMuseum(parameter).then(
                function(response) {
                    row.entity = angular.extend(row.entity, vm.model);
                    $modalInstance.close({
                        entity: vm.model,
                        isSuccess: true
                    });
                },
                function(error) {
                    swal(
                        'Oops...',
                        'Error in REST service call',
                        'error'
                    );
                });
        }
    }

    function RowAddController(ApiFactory, $modalInstance) {
        var vm = this;
        vm.schema = {
            type: "object",
            properties: {
                name: {
                    type: 'string',
                    title: 'Name'
                },
                zip_code: {
                    type: 'string',
                    title: 'Zip Code'
                },
                neighborhood: {
                    type: 'string',
                    title: 'Neighborhood'
                },
                council_district: {
                    type: 'integer',
                    minimum: 0,
                    title: 'Council District'
                },
                police_district: {
                    type: 'string',
                    title: 'Police District'
                },
                address: {
                    type: 'string',
                    title: 'Address'
                }
            }
        };
        vm.form = [
            'name',
            'zip_code',
            'neighborhood',
            'council_district',
            'police_district',
            'address'
        ];
        vm.model = {};
        vm.save = save;

        function save() {
            var parameter = JSON.stringify({
                name: vm.model.name,
                zip_code: vm.model.zip_code,
                neighborhood: vm.model.neighborhood,
                council_district: vm.model.council_district,
                police_district: vm.model.police_district,
                address: vm.model.address
            });

            ApiFactory.postMuseum(parameter).then(
                function(response) {
                    vm.model.id = response.data.id;
                    $modalInstance.close({
                        entity: vm.model,
                        isSuccess: true
                    });
                },
                function(error) {
                    swal(
                        'Oops...',
                        'Error in REST service call',
                        'error'
                    );
                });
        }
    }
})();