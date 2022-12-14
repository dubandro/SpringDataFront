angular.module('angular', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    let min, max;

    $scope.minMaxValue = function (minPrice, maxPrice) {
        // console.log(minPrice, maxPrice);
        min = minPrice;
        max = maxPrice;
        $scope.loadProducts();
    }

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                minPrice: min,
                maxPrice: max
            }
        }).then(function (response) {
            $scope.productList = response.data;
            $scope.size = response.data.length;
        });
    };

    $scope.loadProducts();

    $scope.changeValue = function (productID, delta) {
        $http({
            url: contextPath + '/change_price',
            method: 'GET',
            params: {
                id: productID,
                delta: delta
            }
        }).then(function (){
            $scope.loadProducts();
        });
    };

    $scope.deleteProduct = function (productID) {
        $http({
            url: contextPath + '/products/delete',
            method: 'GET',
            params: {
                id: productID
            }
        }).then(function (){
            $scope.loadProducts();
        });
    };
});