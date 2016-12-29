(function() {
	var artTrackerApp = angular.module('artTrackerApp', ['ngTouch', 'ui.grid', 'ui.grid.selection', 'ui.grid.pagination', 'ui.grid.edit', 'ui.bootstrap', 'schemaForm']);

	artTrackerApp.factory('ApiFactory', function($http) {
		return {
			getMuseums : function() {
				return $http.get('/art-tracker/museum', {
				});
			},
			postMuseum : function(data) {
            	return $http.post('/art-tracker/museum',data);
            },
            deleteMuseum : function(data) {
                         	return $http.delete('/art-tracker/museum' + '/'+data, {
                });
            }

		}
	});

    artTrackerApp.controller('RowEditController', [
                RowEditController ]);
    artTrackerApp.controller('RowAddController', [
                RowAddController ]);
    artTrackerApp.controller('MuseumController', [ '$scope', '$interval','ApiFactory', '$modal',
    			MuseumController ]);

	function MuseumController($scope, $interval, ApiFactory, $modal) {
		var reloadInterval = 1200000;

		// VARIABLES
		angular.extend($scope, {
			gridOptions : {
			    columnDefs: [
			        { name:'Id', field: 'id', visible:false},
                    { name:'Name', field: 'name', headerCellClass: 'purple'},
                    { name:'Zip Code', field: 'zip_code' },
                    { name:'Neighborhood', field: 'neighborhood' },
                    { name:'Council District', field: 'council_district'},
                    { name:'Police District', field: 'police_district'},
                    { name:'Address', field: 'address'},
                    { name: 'Actions', editable:false, enableFiltering: false, enableSorting: false,
                      cellTemplate:'action'  }
			    ],

			    paginationPageSize: 10,
                enableSorting: true,
                multiSelect: true,
                enableColumnMenus: true,
                enableFiltering: true,
                showGridFooter: true,
                showColumnFooter: true,
                enableRowHashing:false
            },
            hideGrid:false,
			error : false
		});

		// METHODS
		angular.extend($scope, {
			retrieveMuseums : retrieveMuseums,
			reload : reload,
			addMuseum : addMuseum,
			deleteMuseum : deleteMuseum,
            editMuseum : editMuseum
		});

		function retrieveMuseums() {
			ApiFactory.getMuseums().then(
					function(response) {
						$scope.gridOptions.data =  response.data.museums;
					}, function(error) {
						handleApiError(error);
					});
		}

		function addMuseum() {
            var modalInstance = $modal.open({
                templateUrl: 'add',
                controller: ['ApiFactory', '$modalInstance',  RowAddController],
                controllerAs: 'vm',
                resolve: {
                                    ApiFactory: function () {return ApiFactory}
                                }
                });

                  modalInstance.result.then(function (entity, isSuccess) {
                               $scope.gridOptions.data.push(entity);
                               alert("Museum added successfully");
                  });

        }

        function editMuseum(grid, row) {
            var modalInstance =  $modal.open({
                templateUrl: 'edit',
                controller: ['ApiFactory', '$modalInstance', 'grid', 'row', RowEditController],
                controllerAs: 'vm',
                resolve: {
                    grid: function () { return grid; },
                    row: function () { return row; },
                    ApiFactory: function () {return ApiFactory}
                }
             });

             modalInstance.result.then(function (entity, isSuccess) {

                if(isSuccess)
                  alert("Museum edited successfully");
             });

        }


        function deleteMuseum(row) {
            ApiFactory.deleteMuseum(row.entity.id).then(
            					function(response) {
            						var i = $scope.gridOptions.data.indexOf(row.entity);
                                    $scope.gridOptions.data.splice(i, 1);
                                    alert("Museum deleted successfully");
            					}, function(error) {
            						handleApiError(error);
            					});
		}

		function handleApiError(error) {
		            alert("Error in REST service")
        			if (error.config.errorCause === 1) {
        				//do something
        			}
        }

		function initialize() {
				$scope.error = false;
				retrieveMuseums();
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
                             name: { type: 'string', title: 'Name' },
                             zip_code: { type: 'string', title: 'Zip Code' },
                             neighborhood: { type: 'string', title: 'Neighborhood' },
                             council_district: { type: 'integer', title: 'Council District' },
                             police_district: { type: 'string', title: 'Police District' },
                             address: { type: 'string', title: 'Address' }
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
          vm.model  = angular.copy(row.entity);
          vm.save = save;

          function save() {

alert(vm.model.id);
            var parameter = JSON.stringify({id:vm.model.id, name: vm.model.name, zip_code:vm.model.zip_code, neighborhood:vm.model.neighborhood,
                    council_district:vm.model.council_district, police_district:vm.model.police_district, address:vm.model.address});

            ApiFactory.postMuseum(parameter).then(
                                function(response) {
                                      row.entity = angular.extend(row.entity, vm.model);
                                      $modalInstance.close(vm.model, true);
                                }, function(error) {
                                    alert("error");
            });
          }
    }

    function RowAddController( ApiFactory, $modalInstance) {
              var vm = this;
              vm.schema = {
                              type: "object",
                              properties: {
                                 name: { type: 'string', title: 'Name' },
                                 zip_code: { type: 'string', title: 'Zip Code' },
                                 neighborhood: { type: 'string', title: 'Neighborhood' },
                                 council_district: { type: 'integer', title: 'Council District' },
                                 police_district: { type: 'string', title: 'Police District' },
                                 address: { type: 'string', title: 'Address' }
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
              vm.model  = {};
              vm.save = save;

              function save() {
                var parameter = JSON.stringify({name: vm.model.name, zip_code:vm.model.zip_code, neighborhood:vm.model.neighborhood,
                    council_district:vm.model.council_district, police_district:vm.model.police_district, address:vm.model.address});

                ApiFactory.postMuseum(parameter).then(
                    function(response) {
                         vm.model.id = response.id;
                         $modalInstance.close( vm.model , true);
                    }, function(error) {
                        alert("error");
                    });
              }
        }
})();