package br.ufrrj.web2.view;

import java.io.IOException;
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

import com.mysql.fabric.xmlrpc.base.Array;

import br.ufrrj.web2.control.CursoFacade;
import br.ufrrj.web2.model.entities.CursoEntity;
import br.ufrrj.web2.model.entities.DisciplinaEntity;

/**
 * Servlet implementation class GerenciarCurso
 */
@WebServlet(name = "gerenciar_curso.do", urlPatterns = { "/gerenciar_curso.do" })
public class GerenciarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenciarCurso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doDelete(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CursoFacade cursoFacade = new CursoFacade();
		
		HttpSession sessao = request.getSession();
		String nomeCurso = request.getParameter("curso_disciplina");
		String novoNomeCurso = request.getParameter("novo_nome_curso");
		CursoEntity cursoEntity = null;
		
		List<CursoEntity> cursosNoContexto = (List<CursoEntity>)getServletContext().getAttribute("cursos");
				
		for (CursoEntity c : cursosNoContexto) {
			
			if (c.getNomeCurso().equals(nomeCurso)) {
				cursoEntity = c;
				cursoEntity.setNomeCurso(novoNomeCurso);
				break;
			}
			
		}
		
		cursoEntity = cursoFacade.atualizarCurso(cursoEntity);
		
		//no jsp eu coloquei um atributo como flag para saber se estou incluindog ou alterando um curso
		
		//pendurando o curso a ser alterado na sessao
		sessao.setAttribute("cursoCadastro", cursoEntity);
	
		//O curso é salvo depois de adicionar as disciplinas 
		RequestDispatcher dispatcher = request.getRequestDispatcher("./add_disciplina.jsp");
		dispatcher.forward(request, response);
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CursoFacade cursoFacade = new CursoFacade();
		
		String nomeCurso = request.getParameter("curso_disciplina");
		
		List<CursoEntity> cursos = (List<CursoEntity>) getServletContext().getAttribute("cursos");
		
		String msg;
		CursoEntity cursoExcluir = null;
		
		for (CursoEntity c : cursos) {
			
			if(c.getNomeCurso().equals(nomeCurso)) {
				cursoExcluir = c;
				break;
			}
		}
		
		if (cursoExcluir == null) {
			msg = "Curso não encontrado.";
		}else {
			
			cursos.remove(cursoExcluir);
			if (cursoFacade.excluirCurso(cursoExcluir))
				msg = "Curso "+cursoExcluir.getNomeCurso()+" excluído.";
			else
				msg = "Erro ao excluir o curso.";
		}
		
		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./gerenciar_cursos.jsp");
		dispatcher.forward(request, response);
	}

}
