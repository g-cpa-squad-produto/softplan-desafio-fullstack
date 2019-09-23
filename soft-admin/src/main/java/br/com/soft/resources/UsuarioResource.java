/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.soft.resources;

import br.com.soft.as.UsuarioAS;
import br.com.soft.resources.dto.ResponseModel;
import br.com.soft.resources.dto.StatusResponse;
import com.soft.core.entidades.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URISyntaxException;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Moquiuti
 */
@Path("usuarios")
@Api(value = "Controle de Usuários", protocols = "http", tags = "Usuario")
public class UsuarioResource {

    @EJB
    UsuarioAS as;

    @POST
    @ApiOperation(
            value = "Insere um novo usuário, não é necessário informar ID",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public ResponseModel inserir(Usuario usuario) {
        ResponseModel response = new ResponseModel();
        if (as.criar(usuario) != null) {
            response.setStatus(StatusResponse.OK);
            response.setMessage("Ok!");
        } else {
            response.setStatus(StatusResponse.NOT_FOUND);
            response.setMessage("ERROR!");
        }
        return response;
    }

    @PUT
    @ApiOperation(
            value = "Atualiza o usuário informado",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public ResponseModel atualizar(Usuario usuario) {
        ResponseModel response = new ResponseModel();
        if (as.atualizar(usuario) != null) {
            response.setStatus(StatusResponse.OK);
            response.setMessage("Ok!");
        } else {
            response.setStatus(StatusResponse.NOT_FOUND);
            response.setMessage("ERROR!");
        }
        return response;
    }

//    @DELETE
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public ResponseModel remover(@PathParam("id") Long id) {
//        ResponseModel response = new ResponseModel();
//        as.remover(id);
//        response.setStatus(StatusResponse.OK);
//        response.setMessage("Ok!");
//        return response;
//    }

    @GET
    @ApiOperation(
            value = "Realiza a Busca de todos os usuários cadastrados",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    @ApiResponses(
            value = {
                @ApiResponse(code = 200, message = "OK.")
                ,
                @ApiResponse(code = 404, message = "Nenhum Resultado Encontrado")
                ,
                @ApiResponse(code = 400, message = "Bad Request")
                ,
                @ApiResponse(code = 500, message = "Failure")
            })
    public ResponseModel getTodos() throws URISyntaxException {
        ResponseModel response = new ResponseModel();
        List<Usuario> usuario = as.getTodos();
        usuario.forEach(u -> {
            u.setSenhaAcesso(null);
        });
        if (usuario != null) {
            if (usuario.size() > 0) {
                response.getData().addAll(usuario);
                response.setStatus(StatusResponse.OK);
                response.setMessage("Ok!");
            } else {
                response.setStatus(StatusResponse.NOT_FOUND);
                response.setMessage("Nenhum Resultado Encontrado!");
            }
        } else {
            response.setStatus(StatusResponse.SERVER_ERROR);
            response.setMessage("Failure!");
        }
        return response;
    }
}
