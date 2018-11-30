package br.ufrrj.web2.view;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufrrj.web2.control.AlunoFacade;
import br.ufrrj.web2.model.entities.AlunoEntity;

/**
 * Servlet implementation class Matricula
 */
@WebServlet("/matricula.do")
public class Matricula extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Matricula() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessao = request.getSession();
		AlunoFacade alunoFacade = new AlunoFacade();
		String nomeAluno = request.getParameter("nome_aluno");
		String dataNascAux = request.getParameter("data_nasc");
	
		//usuário bonzinho
		String[] data = dataNascAux.split("-");
				
		Calendar dataNasc = Calendar.getInstance();
		dataNasc.set(Calendar.DAY_OF_MONTH,Integer.parseInt(data[2]));
		dataNasc.set(Calendar.MONTH,Integer.parseInt(data[1])-1);//Calendar.MONTH is a number from 0 to 11.
		dataNasc.set(Calendar.YEAR,Integer.parseInt(data[0]));

		AlunoEntity alunoEntity = alunoFacade.procurarAlunoPorNomeDataNasc(nomeAluno, dataNasc);
			
		RequestDispatcher dispatcher;
		
		if (alunoEntity != null) {			
			sessao.setAttribute("aluno",alunoEntity);
			dispatcher = request.getRequestDispatcher("./matricular.jsp");
		}else {
			String msg;
			msg = "Aluno não encontrado.";
			request.setAttribute("msg", msg);
			dispatcher = request.getRequestDispatcher("./matricular.jsp");
		}
		
		dispatcher.forward(request, response);
				
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AlunoFacade alunoFacade = new AlunoFacade();
		HttpSession sessao = request.getSession();
		
		AlunoEntity alunoEntity = (AlunoEntity)sessao.getAttribute("aluno");
		
		String[] nomes_disciplinas_selecionadas = request.getParameterValues("disciplina_matricula");
		
		RequestDispatcher dispatcher;
		String msg;
		
		if (alunoEntity == null) {
			msg = "Por favor, informe os dados de um aluno já cadastrado.";
			dispatcher = request.getRequestDispatcher("./matricular.jsp");			
			
		}else {
						
			alunoFacade.matricularAlunoDisciplinasEscolhidas(alunoEntity, nomes_disciplinas_selecionadas);
			
			msg = "O aluno foi matriculado com sucesso.";
			dispatcher = request.getRequestDispatcher("./index.jsp");
		}
		
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);

	}

}
