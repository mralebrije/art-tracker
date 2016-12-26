(function() {
	var artTrackerApp = angular.module('artTrackerApp', ['ngTouch', 'ui.grid', 'ui.grid.selection']);

	artTrackerApp.factory('ApiFactory', function($http) {
		return {
			getMuseums : function() {
				return $http.get('/art-tracker/museum', {
				});
			}
		}
	});

	artTrackerApp.controller('MuseumController', [ '$scope', '$interval', 'ApiFactory',
			MuseumController ]);

	function MuseumController($scope, $interval, ApiFactory) {
		var reloadInterval = 1200000;

		// variables
		angular.extend($scope, {
			museums : [],
			gridOptions : {
                enableSorting: true,
                enableRowSelection: true,
                enableFullRowSelection: true,
                multiSelect: true,
                enableRowHeaderSelection: false,
                enableColumnMenus: false,
                enableFiltering: true,
                showGridFooter: true,
                showColumnFooter: true
            },
			error : false
		});

		// methods
		angular.extend($scope, {
			retrieveMuseums : retrieveMuseums
		});





		function retrieveMuseums() {
			ApiFactory.getMuseums().then(
					function(response) {
						$scope.museums = response.data.museums;
						$scope.gridOptions.data =  $scope.museums;
					}, function(error) {
						handleApiError(error);
					});
		}

		function handleApiError(error) {
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