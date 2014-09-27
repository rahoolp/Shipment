function Hello($scope, $http) {
    $http.get('http://localhost:8080/shipments/5409239ba0ee7e2473721825').
        success(function(data) {
            $scope.shipment = data;
        });
}
