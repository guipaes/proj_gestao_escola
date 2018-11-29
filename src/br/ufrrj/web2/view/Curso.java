package br.ufrrj.web2.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufrrj.web2.control.CursoFacade;

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

		System.out.println("curso.do doGet");
		
		
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
