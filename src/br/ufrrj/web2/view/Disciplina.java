package br.ufrrj.web2.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufrrj.web2.control.CursoFacade;
import br.ufrrj.web2.model.entities.CursoEntity;
import br.ufrrj.web2.model.entities.DisciplinaEntity;

/**
 * Servlet implementation class Disciplina
 */
@WebServlet("/disciplina.do")
public class Disciplina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Disciplina() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * Lembrando que trouxe as disciplinas junto com os cursos (no mapeamento em EntityCurso, ver FetchType.EAGER) 
			e os coloquei (os cursos) no ServletContext. Se tivesse usado FetchType.LAZY eu economizaria a memória RAM, mas precisaria
			acessar o banco agora para fazer uma consulta pelas disciplinas associadas aos cursos.
			Como nossa aplicação é pequena, e por agilidade na programação, fui pragmático e deixei "eager".
			
			MAS PARA EXEMPLIFICAR, VOU FAZER A CONSULTA! COMO SE TIVESSE DEIXADO LAZY!!!
		 */
		
		//Apesar de estar em Disciplina, meu interesse é listar os cursos
		CursoFacade cursoFacade = new CursoFacade();
		
		String cursoBusca = request.getParameter("curso_disciplina");
		System.out.println(cursoBusca);
		List<CursoEntity> cursos = null;
		if (cursoBusca.equals("todos")) {
			
			cursos = cursoFacade.pegarTodosCursosComDisciplinas();
			
		}else {
			cursos = new ArrayList<CursoEntity>();
			cursos.add(cursoFacade.pegarCursoPorNomeComDisciplinas(cursoBusca));
		}
		
		request.setAttribute("listar_cursos", cursos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./listar_disciplinas.jsp");
		dispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		//vou salvar o curso e as disciplinas serão salvas junto
		CursoFacade cursoFacade = new CursoFacade();
		
		//pegando todas as disciplinas
		String[] nomes_disciplinas = request.getParameterValues("nome_disciplina");
		
		//Não vou verificar se a sessão é nova. Considerare que usuário não vai fugir da navergação convencional. Ou seja, não vai digitar o nome da página na URL.
		HttpSession sessao = request.getSession();
		CursoEntity curso = (CursoEntity)sessao.getAttribute("cursoCadastro");
			
		//criando as disciplinas e relacionando com o curso
		for (String nome_disciplina : nomes_disciplinas) {
			
			DisciplinaEntity disciplina = new DisciplinaEntity();
			disciplina.setNomeDisciplina(nome_disciplina);
			curso.addDisciplina(disciplina);
			disciplina.setCurso(curso);
			
		}
		
		String msg = cursoFacade.salvarCurso(curso);

		sessao.removeAttribute("cursoCadastro");
		request.setAttribute("msg", msg);

		//disponibilizando curso no contexto da aplicação
		if (cursoFacade.getCurso() != null) {
		
			List<CursoEntity> cursos = (List<CursoEntity>)request.getServletContext().getAttribute("cursos");
			
			if (cursos == null) {
				System.out.println("sem cursos");
				cursos = new ArrayList<CursoEntity>();
				request.getServletContext().setAttribute("cursos", cursos);
			}
			
			cursos.add(cursoFacade.getCurso());			
		}else {
			System.out.println("cursoEntity vazio");
		}
		System.out.println(cursoFacade.getCurso().getNomeCurso());
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
				
	}

}
