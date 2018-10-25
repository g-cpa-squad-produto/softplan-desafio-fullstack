/**
 * 
 */
package br.com.softplan.response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author emanuel
 * 	Classe criada para passar resposta e os erros gerados durante a requisicao da API
 */
public class Response<T> {
	private T data;
	private List<String> erros;

	public Response() {

	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErros() {
		if (this.erros == null) {
			this.erros = new ArrayList<>();
		}
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

}
