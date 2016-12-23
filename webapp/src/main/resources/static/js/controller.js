app.controller('twitterHistoryController', function ($scope, $http) {
    loadHistory();

    $http.get('/twitter/locations').then(function successCallback(response) {
        $scope.twitterLocations = response.data;
    }, errorCallback);

    $scope.search = function () {
        console.log($scope.customPopupSelected)
        $http.get('/twitter/search/' + $scope.customPopupSelected.woeid).then(
            function successCallback(response) {
                loadHistory();
            }, errorCallback);

    };

    function loadHistory() {
        $http.get('/twitter/history').then(
            function successCallback(response) {
                $scope.twitterHistory = response.data;
            }, errorCallback);
    }
});

app.controller('twitterDetailController', function ($scope, $http, $routeParams) {
    $http.get('/twitter/history/' + $routeParams.id).then(function successCallback(response) {
        $scope.twitterHistory = response.data;
    }, errorCallback);
});

function errorCallback(response) {
    console.log(response)
};

