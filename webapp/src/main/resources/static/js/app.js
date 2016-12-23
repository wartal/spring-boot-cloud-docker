var app = angular.module('app', ['ngRoute', 'ngResource', 'ui.bootstrap.typeahead']);
app.config(function ($routeProvider) {
    $routeProvider
        .when('/twitter', {
            templateUrl: '/views/twitterHistoryList.html',
            controller: 'twitterHistoryController'
        })
        .when('/twitter/:id', {
            templateUrl: '/views/twitterDetailView.html',
            controller: 'twitterDetailController'
        })
        .otherwise(
        {redirectTo: '/twitter'}
    );
}).run(function ($rootScope, $http) {

    $http.get('/twitter/message').then(function successCallback(response) {
        $rootScope.data = response.data;
    });
});