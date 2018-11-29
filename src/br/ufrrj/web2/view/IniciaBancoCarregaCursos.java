package br.ufrrj.web2.view;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.ufrrj.web2.control.UtilitiesFacade;
import br.ufrrj.web2.model.dao.CursoDAO;
import br.ufrrj.web2.model.dao.DAO;
import br.ufrrj.web2.model.entities.CursoEntity;

/**
 * Application Lifecycle Listener implementation class IniciaBancoCarregaCursos
 *
 */
@WebListener
public class IniciaBancoCarregaCursos implements ServletContextListener {
	
	private UtilitiesFacade utilities;
    /**
     * Default constructor. 
     */
    public IniciaBancoCarregaCursos() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    
    	//iniciando conexão com banco
    	this.utilities = new UtilitiesFacade();
    	this.utilities.abrirConexaoBanco();
    	List<CursoEntity> cursos = this.utilities.carregarListaCursos();    	
    	sce.getServletContext().setAttribute("cursos", cursos);
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	this.utilities.fecharConexaoBanco();
    }
	
}
