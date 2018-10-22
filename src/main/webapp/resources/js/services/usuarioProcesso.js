app.factory('usuarioProcesso', ['$http', function($http) { 
	
	var usuarioProcessoAction = {};
	
	usuarioProcessoAction.gravar = function(analise) {
		$http.post('http://localhost:9000/salvar', analise);
	};
	
	usuarioProcessoAction.analisePorRisco = function(nomeCliente, limiteCredito, risco) {
		var analise = {
				nomeCliente: nomeCliente,
				limiteCredito: limiteCredito,
				risco: risco,
				taxeDeJuros: 0
		};
		return $http.post('http://localhost:9000/analisePorRisco', analise);
	};
	
	usuarioProcessoAction.analisePorLimtiteCredito = function(nomeCliente, limiteCredito) {
		var analise = {
				nomeCliente: nomeCliente,
				limiteCredito: limiteCredito,
				risco: '',
				taxeDeJuros: 0
		};
		return $http.post('http://localhost:9000/analisePorLimtiteCredito', analise);
	};
	
	return usuarioProcessoAction;
	
}]);
