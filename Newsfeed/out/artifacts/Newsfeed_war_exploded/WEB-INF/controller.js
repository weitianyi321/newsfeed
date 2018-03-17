/**
 * Created by tianyi on 2018/3/16.
 */
'use strict'
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
    $http({
        method: "GET",
        url: "http://localhost:8081/Newsfeed/JsonServlet"
    }).then(function(response) {
        $scope.data = response.data;
        //console.log(response.data);
        $scope.myRes = response.data;
        //console.log($scope.myRes);
        $scope.statuscode = response.status;
    });


    //var parameter = JSON.stringify({content:user_email, date:user_password});
    console.log($scope.content);
    $scope.submit = function() {
        $http.post('http://localhost:8081/Newsfeed/JsonServlet').success(function(data) {
            console.log(data);
            $scope.myData = data;
        });
    }


});
app.controller('controller1', ['$scope', '$http', function($scope, $http) {
        console.log('controller1 working');
        $scope.master = {};

        $scope.update = function (news) {
            $scope.master = angular.copy(news);
            console.log($scope.master);
            $http({
                method: "POST",
                url: "http://localhost:8081/Newsfeed/JsonServlet",
                data: $scope.master,
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            }).then(function(response) {

            });
        };

        $scope.reset = function () {
            $scope.user = angular.copy($scope.master);
        };
        console.log($scope.master);
        $scope.reset()


}]);