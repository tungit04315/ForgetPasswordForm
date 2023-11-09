let urlFirebase = "https://poly-java-6-c6bbe-default-rtdb.firebaseio.com/";
const app = angular.module("app", []);
app.controller("myctrl", function($scope, $http) {
    $scope.form = {};
    $scope.items = {};

    $scope.reset = function() {
        $scope.form = { gender: true, country: "VN" };
        $scope.key = null;
    }

    $scope.loadAll = function() {
        var url = `${urlFirebase}/students.json`;
        $http.get(url).then(function(response) {
            $scope.items = response.data;
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
    }

    $scope.edit = function(key) {
        var url = `${urlFirebase}/students/${key}.json`;
        $http.get(url).then(function(response) {
            $scope.form = response.data;
            $scope.key = key;
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
    }

    $scope.update = function() {
        var item = angular.copy($scope.form);
        var url = `${urlFirebase}/students/${$scope.key}.json`;
        $http.put(url, item).then(function(response) {
            $scope.items[$scope.key] = response.data;

        })
    }

    $scope.delete = function(key) {
        var url = `${urlFirebase}/students/${key}.json`;
        $http.delete(url).then(response => {
            delete $scope.items[key];
            $scope.reset();
        })
    }

    $scope.add = function() {
        var item = angular.copy($scope.form);
        var url = `${urlFirebase}/students.json`;
        $http.post(url, item).then(response => {
            $scope.key = response.data.name;
            $scope.items[$scope.key] = item;
            $scope.reset();
        })
    }

    $scope.loadAll();
    $scope.reset();
})