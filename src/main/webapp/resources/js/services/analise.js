app.factory('analise', ['$http', function($http) { 
	
	var analise = {};
	
	analise.gravar = function(analise) {
		$http.post('http://localhost:9000/salvar', analise);
	};
	
	analise.analisePorRisco = function(nomeCliente, limiteCredito, risco) {
		var analise = {
				nomeCliente: nomeCliente,
				limiteCredito: limiteCredito,
				risco: risco,
				taxeDeJuros: 0
		};
		return $http.post('http://localhost:9000/analisePorRisco', analise);
	};
	
	analise.analisePorLimtiteCredito = function(nomeCliente, limiteCredito) {
		var analise = {
				nomeCliente: nomeCliente,
				limiteCredito: limiteCredito,
				risco: '',
				taxeDeJuros: 0
		};
		return $http.post('http://localhost:9000/analisePorLimtiteCredito', analise);
	};
	
	return analise;
	
}]);


//app.factory('voto', ['$http', function($http) { 
//	var voto = {};
//	
//	voto.votar = function() {
//		  console.log("sucesso o nome :");
//		  var voto = {
//			codigoRestaurante: 1,
//			codigoUsuario: 1
//		  };
//		  $http.post('http://localhost:9000/votar', voto);
//	  };
//  
//  return voto;
//}]);


