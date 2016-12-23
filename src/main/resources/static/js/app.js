(function() {
	var artTrackerApp = angular.module('artTrackerApp', []);

	artTrackerApp.factory('ApiFactory', function($http) {
		return {
			getMuseums : function() {
				return $http.get('/art-tracker/museum', {
				});
			},
			getDistricts : function() {
				return $http.get('/art-tracker/museum/district', {
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
			districts : [],
			selectedDistrict : undefined,
			data : {
				districtSelect : ""
			},
			error : false
		});

		// methods
		angular.extend($scope, {
			retrieveMuseums : retrieveMuseums,
			retrieveDistricts : retrieveDistricts,
			setFilter : setFilter
		});

		function retrieveMuseums() {
			ApiFactory.getMuseums().then(
					function(response) {
						$scope.museums = response.data.museums;
					}, function(error) {
						handleApiError(error);
					});
		}

		function handleApiError(error) {
        			if (error.config.errorCause === 1) {
        				//do something
        			}
        		}

		function retrieveDistricts() {
			ApiFactory.getDistricts().then(
					function(response) {
						$scope.districts = response.data.districts;
					}, function(error) {

					});
		}

		function setFilter() {
			$scope.selectedDistrict = "";
			if ($scope.data.districtSelect != null) {
				angular.forEach($scope.districts, function(element) {
					if ($scope.data.districtSelect.districtItem === element.districtItem) {
						$scope.selectedDistrict = element.districtItem;
						return;
					}
				})
			}

		}

		function initialize() {
				$scope.error = false;
				retrieveMuseums();
				retrieveDistricts();
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