package br.com.softplan.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.softplan.controle.UsuarioCon;
import br.com.softplan.modelo.Grupo;
import br.com.softplan.modelo.Permissao;
import br.com.softplan.modelo.Processo;
import br.com.softplan.modelo.Usuario;

/**
 * @since 13/08/2018 21:07
 * @author Rauber
 */
@WebServlet(name = "servletIndex", urlPatterns = {"/home"}, loadOnStartup = 1)
public class ServletIndex extends HttpServlet {

	private static final long serialVersionUID = 8091337851763991189L;
	
	private final int LOGIN = 1;
	private final int USUARIO = 2;
	private final int MNG_USUARIO = 3;
	private final int GRAVAR_USUARIO = 4;
	private final int PERMISSAO = 5;
	private final int GRUPO = 6;
	private final int PROCESSO = 7;
	private final int MNG_PROCESSO = 8;
	private final int EXC_PROCESSO = 9;
	private final int LOGOFF = 10;
	
	private UsuarioCon usuarioCon = new UsuarioCon();
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

//        if (!StringUtils.isEmpty(request.getParameter("ajax"))) {
//            processAjax(request, response);
//        } else {
            process(request, response);
//        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        if (request.getSession().getAttribute("idUsuario") != null) {

            int acao = (request.getParameter("acao") != null) ? Integer.parseInt(request.getParameter("acao")) : 0;

            if (acao != 0) {

                switch (acao) {

                    case LOGIN:

                        break;

                    case USUARIO: // Carregar lista de usuários

                    	//usuarioCon.salvar();
                    	request.setAttribute("usuarios", usuarioCon.listar());
                    	
                        getServletContext()
		                        .getRequestDispatcher("/WEB-INF/view/usuario.jsp")
		                        .forward(request, response);
                        break;

                    /*case MNG_USUARIO: // Incluir / Alterar usuários

                        String idUsuario = request.getParameter("idUsuario");

                        if (!idUsuario.isEmpty()) {
                            request.setAttribute("user", getUsuario(idUsuario));
                        }

                        getServletContext()
                                .getRequestDispatcher("/WEB-INF/view/usuario-cadastro.jsp")
                                .forward(request, response);
                        break;

                    case GRAVAR_USUARIO: // Grava usuário

                        usuario = (Usuario) request.getSession().getAttribute("usuario");
                        
                        gravarUsuario(request, usuario);

                        request.setAttribute("usuarios", getUsuarios());

                        getServletContext()
                                .getRequestDispatcher("/WEB-INF/view/usuario.jsp")
                                .forward(request, response);
                        break;*/
                        
                    case PERMISSAO: // Carregar lista de permissões

                    	request.setAttribute("permissoes", getPermissoes());

                        getServletContext()
		                        .getRequestDispatcher("/WEB-INF/view/permissao.jsp")
		                        .forward(request, response);
                        break;
                        
                    case GRUPO: // Carregar lista de grupos

                    	request.setAttribute("grupos", getGrupos());

                        getServletContext()
		                        .getRequestDispatcher("/WEB-INF/view/grupo.jsp")
		                        .forward(request, response);
                        break;
                        
                    case PROCESSO: // Carregar lista de processos

                    	request.setAttribute("processos", getProcessos());

                        getServletContext()
		                        .getRequestDispatcher("/WEB-INF/view/processo.jsp")
		                        .forward(request, response);
                        break;
                        
                    case MNG_PROCESSO: // Incluir / Alterar processos

                        String idProcesso = request.getParameter("idProcesso");

                        if (!idProcesso.isEmpty()) {
//                            request.setAttribute("processo", getProcesso(idProcesso));
                        }

                        getServletContext()
                                .getRequestDispatcher("/WEB-INF/view/permissao.jsp") // permissao-cadastro.jsp
                                .forward(request, response);
                        break;
                        
                    case EXC_PROCESSO: // Excluir processo

                        String id = request.getParameter("idProcesso");

                        if (!id.isEmpty()) {
//                            request.setAttribute("processo", getProcesso(id));
                        }

                        getServletContext()
                                .getRequestDispatcher("/WEB-INF/view/permissao.jsp")
                                .forward(request, response);
                        break;

                    case LOGOFF: // Sair da área interna

                        request.getSession().removeAttribute("idUsuario");
                        request.getSession().removeAttribute("usuario");

                        response.sendRedirect("/softplan/home");
                        break;
                }

            } else {

                getServletContext()
                        .getRequestDispatcher("/WEB-INF/view/index.jsp")
                        .forward(request, response);
//            	response.sendRedirect("/softplan/home");
            }
        /*} else {
            response.sendRedirect("/softplan/home");
        }*/
    }

    // Somente para demonstrar como seria feito processo ao obter chamada ajax
    /*private void processAjax(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!StringUtils.isEmpty(request.getParameter("municipios"))) {

            request.setAttribute("municipios", getMunicipios(request.getParameter("idUf")));

            getServletContext()
                    .getRequestDispatcher("/WEB-INF/view/ajx/municipios.jsp")
                    .forward(request, response);

        } 
    }*/

    // Apresento como faria a chamada ao control - dao para coletar os registros do bd
    public List<Usuario> getUsuarios() {

    	// caso de associação na busca, vinculando apresentar grupo do usuário
        //UsuarioCon usuarioCon = new UsuarioCon();
//        usuarioCon.setJoin(new Object[]{
//            new Object[]{"grupo", JoinType.LEFT_OUTER_JOIN}
//        });
//        usuarioCon.setCriterions(new Object[]{
//            new Object[]{"grupo", Restrictions.eq("id", grupo.getId())}
//        });
        //usuarioCon.setOrderByClause(new OrderBy[]{
            //new OrderBy(Order.asc("nome"))
        //});

        //return usuarioCon.getLista();
    	List<Usuario> lstUsuarios = new ArrayList<Usuario>();
    	
		for (int i = 0; i < 5; i++) {
			Usuario usuario = new Usuario();
			usuario.setId(Long.valueOf(i + 1));
			usuario.setNome("Fernando Rauber " + (i + 1));
			int idade = 32 + i;
			usuario.setIdade(idade);
//			usuario.setGrupo(new Grupo());
			lstUsuarios.add(usuario);
		}
    	
    	return lstUsuarios;
    	
    	// Tentativa rápida de imersão do H2 database ao projeto para rodar a aplicação independente de banco externo,
    	// podendo assim rodar a aplicação sem necessidade de configurações de base.
		/*try {
			entityManager.getTransaction().begin();
			
			@SuppressWarnings("unchecked")
			List<Usuario> usuarios = entityManager.createQuery("from Usuario").getResultList();
			
			for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
				Usuario usuario = (Usuario) iterator.next();
				System.out.println(usuario.getNome());
			}
			
			entityManager.getTransaction().commit();
			
			return usuarios;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		
		return null;*/
    	
    }
    
    public List<Permissao> getPermissoes() {

    	List<Permissao> lstPermissoes = new ArrayList<Permissao>();
    	
		for (int i = 0; i < 3; i++) {
			Permissao permissao = new Permissao();
			permissao.setId(Long.valueOf(i + 1));
			permissao.setNome("Permissão " + (i + 1));
			permissao.setIncluir(true);
			permissao.setAlterar(true);
			permissao.setExcluir(true);
			permissao.setVisualizar(true);
			lstPermissoes.add(permissao);
		}
    	
    	return lstPermissoes;
    }

    
    public List<Grupo> getGrupos() {

    	List<Grupo> lstGrupos = new ArrayList<Grupo>();
    	
		for (int i = 0; i < 3; i++) {
			Grupo grupo = new Grupo();
			grupo.setId(Long.valueOf(i + 1));
			grupo.setNome("Grupo " + (i + 1));
			grupo.setPermissao(new Permissao());
			lstGrupos.add(grupo);
		}
    	
    	return lstGrupos;
    }
    
    public List<Processo> getProcessos() {

    	List<Processo> lstProcessos = new ArrayList<Processo>();
    	
		for (int i = 0; i < 7; i++) {
			Processo processo = new Processo();
			processo.setId(Long.valueOf(i + 1));
			processo.setNome("Processo " + (i + 1));
			lstProcessos.add(processo);
		}
    	
    	return lstProcessos;
    }
    
	/*public Processo getProcesso(String idProcesso) {

		permissaoCon.localizar(Integer.parseInt(idProcesso));

		return (Permissao) permissaoCon.getObjeto();
	}*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}