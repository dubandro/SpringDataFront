angular.module('angular', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    let minPrice, maxPrice;
    let currentPageIndex = 1;

    $scope.minMaxValue = function (min, max) {
        // console.log(minPrice, maxPrice);
        minPrice = min;
        maxPrice = max;
        $scope.loadProducts();
    }

    $scope.loadProducts = function (pageIndex=1) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min: minPrice,
                max: maxPrice,
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response.data);
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
            $scope.totalProducts = $scope.productsPage.totalElements;
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

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