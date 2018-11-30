package br.ufrrj.web2.view;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufrrj.web2.control.CursoFacade;
import br.ufrrj.web2.control.DisciplinaFacade;
import br.ufrrj.web2.model.entities.CursoEntity;
import br.ufrrj.web2.model.entities.DisciplinaEntity;

/**
 * Servlet implementation class Curso
 */
@WebServlet("/curso.do")
public class Curso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Curso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CursoFacade cursoFacade = new CursoFacade();
		DisciplinaFacade disciplinaFacade = new DisciplinaFacade();
		
		List<String> ids_disciplinas_selecionadas = Arrays.asList(request.getParameterValues("excluir_disciplinas"));
				
		HttpSession sessao = request.getSession();
		//transferir para o Facade
		CursoEntity cursoEntity = (CursoEntity)sessao.getAttribute("cursoCadastro");
		
		//pode lançar exceção
		List<DisciplinaEntity> disciplinasCurso = cursoEntity.getDisciplinas(); 
		
		for(String d : ids_disciplinas_selecionadas) {
			
			int aux = disciplinasCurso.size();
			
			for(int i = 0; i < aux; i++) {
				if(disciplinasCurso.get(i).getId() == Integer.parseInt(d)) {
					
//					disciplinaFacade.removerDisciplina(disciplinasCurso.get(i));
					disciplinasCurso.remove(disciplinasCurso.get(i));
										
				}
			}
			
		}
				
//		cursoEntity = cursoFacade.atualizarCurso(cursoEntity);
		sessao.setAttribute("cursoCadastro", cursoEntity);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./add_disciplina.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CursoFacade cursoFacade= new CursoFacade();
				
		HttpSession sessao = request.getSession();
		sessao.setAttribute("cursoCadastro", cursoFacade.getNovoCurso(request.getParameter("nome_curso")));
		
		//O curso é salvo depois de adicionar as disciplinas 
		RequestDispatcher dispatcher = request.getRequestDispatcher("./add_disciplina.jsp");
		dispatcher.forward(request, response);
		
	}

}
