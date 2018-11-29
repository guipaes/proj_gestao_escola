package br.ufrrj.web2.view;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

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
 * Servlet implementation class Aluno
 */
@WebServlet("/aluno.do")
public class Aluno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aluno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AlunoFacade alunoFacade = new AlunoFacade();
		HttpSession sessao = request.getSession();
		
		String nomeAluno = request.getParameter("nome_aluno");
		String dataNascAux = request.getParameter("data_nasc");
		
		//usuário bonzinho
		String[] data = dataNascAux.split("-");

		Calendar dataNasc = Calendar.getInstance();
		dataNasc.set(Calendar.DAY_OF_MONTH,Integer.parseInt(data[2]));
		dataNasc.set(Calendar.MONTH,Integer.parseInt(data[1])-1);//Calendar.MONTH is a number from 0 to 11.
		dataNasc.set(Calendar.YEAR,Integer.parseInt(data[0]));

		//Eu poderia procurar o curso entre a lista de cursos penduradas em ServletContext
		//vou considerar que o curso existe (usuário bonzinho)
		String nomeCurso = request.getParameter("curso");
				
		AlunoEntity alunoEntity = alunoFacade.cadastrarAluno(nomeAluno,dataNasc,nomeCurso);
		
		RequestDispatcher dispatcher;
		String msg;
		if (alunoEntity == null) {
			
			msg = "Erro ao cadastrar aluno.";
			
			request.setAttribute("msg", msg);
			
			dispatcher = request.getRequestDispatcher("./index.jsp");
			
		}else {
		
			msg = "Aluno cadstrado com sucesso.";			
			request.setAttribute("msg", msg);
			
			dispatcher = request.getRequestDispatcher("./matricular.jsp");
						
		}		
		//matricular em disciplinas (pode mastricular depois tb)
		sessao.setAttribute("aluno", alunoEntity);
		dispatcher.forward(request, response);
		
	}

}
