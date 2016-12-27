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
            }
		}
	});

    artTrackerApp.controller('RowEditController', RowEditController);

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
                    { name:'Postal Code', field: 'zip' },
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
                showColumnFooter: true
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
            editMuseum : editMuseum,
            openMuseumModel : openMuseumModel
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

            var parameter = JSON.stringify({name:"user", zip_code:"user", neighborhood:"user",
            council_district:1, police_district:"user", address:"user"});

        			ApiFactory.postMuseum(parameter).then(
        					function(response) {

        						$scope.gridOptions.data.push(parameter);

        					}, function(error) {
        						handleApiError(error);
        					});
        }

        function editMuseum(grid, row) {
                openMuseumModel(grid,row);
        }

        function openMuseumModel(){
            $modal.open({
                                    templateUrl: 'edit',
                                    controller: ['$modalInstance', 'grid', 'row', RowEditController],
                                    controllerAs: 'vm',
                                    resolve: {
                                        grid: function () { return grid; },
                                        row: function () { return row; }
                                    }
                                  });
        }

        function deleteMuseum(id) {
            alert(id);
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


    function RowEditController($modalInstance, grid, row) {
          var vm = this;

          vm.schema = {
                          type: "object",
                          properties: {
                             name: { type: 'string', title: 'Name' },
                             zip: { type: 'string', title: 'Zip' },
                             neighborhood: { type: 'string', title: 'Neighborhood' },
                             council_district: { type: 'integer', title: 'Council District' },
                             police_district: { type: 'string', title: 'Police District' },
                             address: { type: 'string', title: 'Address' }
                          }
          };

          vm.form = [
                          'name',
                          'zip',
                          'neighborhood',
                          'council_district',
                          'police_district',
                          'address'
          ];

          vm.model  = angular.copy(row.entity);

          vm.save = save;

          function save() {
            // Copy row values over
            row.entity = angular.extend(row.entity, vm.model);
            $modalInstance.close(row.entity);
          }
    }
})();