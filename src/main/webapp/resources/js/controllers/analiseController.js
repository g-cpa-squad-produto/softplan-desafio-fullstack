app.controller('AnaliseController', ['$scope', 'analise', function($scope, analise) {
	  
	$scope.nomeCliente;
	$scope.limiteCredito;
	$scope.risco;
	
	$scope.analisePorRisco = function() {
		var resultado = analise.analisePorRisco($scope.nomeCliente, $scope.limiteCredito, $scope.risco ); 
		resultado.success(function(data) { 
			analise.gravar(data);
		});
	 };
	 
	 $scope.analisePorLimtiteCredito = function() {
		var resultado = analise.analisePorLimtiteCredito($scope.nomeCliente, $scope.limiteCredito); 
		resultado.success(function(data) { 
			analise.gravar(data);
		});
	 };
	 
}]);
