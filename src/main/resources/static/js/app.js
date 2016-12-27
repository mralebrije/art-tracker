(function() {
	var artTrackerApp = angular.module('artTrackerApp', ['ngTouch', 'ui.grid', 'ui.grid.selection', 'ui.grid.pagination']);

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

	artTrackerApp.controller('MuseumController', [ '$scope', '$interval','ApiFactory',
			MuseumController ]);


	function MuseumController($scope, $interval, ApiFactory) {
		var reloadInterval = 1200000;

		// VARIABLES
		angular.extend($scope, {
			gridOptions : {
			    columnDefs: [
                    { name:'Name', field: 'name', headerCellClass: 'purple'},
                    { name:'Postal Code', field: 'zip' },
                    { name:'Neighborhood', field: 'neighborhood' },
                    { name:'Council District', field: 'council_district'},
                    { name:'Police District', field: 'police_district'},
                    { name:'Address', field: 'address'},
                    { name: 'Actions', enableFiltering: false, enableSorting: false,
                      cellTemplate:'<button   type="button" class="btn btn-warning btn-customized" ng-click="grid.appScope.editMuseum()">Edit</button> <button   type="button" class="btn btn-danger btn-customized" ng-click="grid.appScope.deleteMuseum()">Delete</button>'  }
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
			editMuseum : editMuseum,
			deleteMuseum : deleteMuseum
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

        function editMuseum() {
            alert("wwwww");

        }

        function deleteMuseum() {
            alert("deleteMuseum");
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
})();