/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.soft.resources;

import br.com.soft.as.ProcessoAS;
import br.com.soft.as.UsuarioAS;
import br.com.soft.resources.dto.ResponseModel;
import br.com.soft.resources.dto.StatusResponse;
import com.soft.core.entidades.Processo;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Moquiuti
 */
@Path("processos")
@Api(value = "Controle de Processos", protocols = "http", tags = "Processo")
public class ProcessoResource {

    private final Logger log = LogManager.getLogger(getClass());

    @EJB
    ProcessoAS as;

    @EJB
    UsuarioAS usuAs;

    @GET
    @Path("/triador/{id}")
    @ApiOperation(
            value = "Realiza a busca de todos os processos para visualização, somente para usuário triador.",
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
    public ResponseModel getTodos(@PathParam("id") Long id) throws URISyntaxException {
        ResponseModel response = new ResponseModel();
        Long usuario = usuAs.getFindByIdTipo(id, "TRIADOR");
        if (usuario != null) {
            List<Processo> processos = as.getTodos();
            if (processos != null) {
                if (processos.size() > 0) {
                    response.getData().addAll(processos);
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
        } else {
            response.setStatus(StatusResponse.SERVER_ERROR);
            response.setMessage("Sem Permissão para Visualizar Processos!");
        }
        return response;
    }

    @GET
    @Path("/finalizador/{id}")
    @ApiOperation(
            value = "Realiza a busca de todos os processos para inserir um parecer, somente para usuário finalizador.",
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
    public ResponseModel getTodosSemParecer(@PathParam("id") Long id) throws URISyntaxException {
        ResponseModel response = new ResponseModel();
        Long usuario = usuAs.getFindByIdTipo(id, "FINALIZADOR");
        if (usuario != null) {
            List<Processo> processos = as.getTodosSemParecer();
            if (processos != null) {
                if (processos.size() > 0) {
                    response.getData().addAll(processos);
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
        } else {
            response.setStatus(StatusResponse.SERVER_ERROR);
            response.setMessage("Sem Permissão para apresentar um parecer sobre o processo!");
        }
        return response;
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Processo por id",
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

    public ResponseModel getFindByID(@PathParam("id") Long id) throws URISyntaxException {
        ResponseModel response = new ResponseModel();
        Processo processo = as.getFindById(id);
        if (processo != null) {
            if (processo.getId() != null) {
                response.getData().add(processo);
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

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseModel inserir(Processo processo) {
        ResponseModel response = new ResponseModel();
        if (as.inserir(processo) != null) {
            response.setStatus(StatusResponse.OK);
            response.setMessage("Ok!");
        } else {
            response.setStatus(StatusResponse.NOT_FOUND);
            response.setMessage("ERROR!");
        }
        return response;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseModel atualizar(Processo processo) {
        ResponseModel response = new ResponseModel();
        if (as.atualizar(processo) != null) {
            response.setStatus(StatusResponse.OK);
            response.setMessage("Ok!");
        } else {
            response.setStatus(StatusResponse.NOT_FOUND);
            response.setMessage("ERROR!");
        }
        return response;
    }

    @GET
    @Path("/pendentes")
    @ApiOperation(
            value = "Realiza a busca de todos os processos pendentes de parecer",
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
    public ResponseModel getTodosPendentes() throws URISyntaxException {
        ResponseModel response = new ResponseModel();
        List<Processo> processos = as.getTodosSemParecer();
        if (processos != null) {
            if (processos.size() > 0) {
                response.getData().addAll(processos);
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
