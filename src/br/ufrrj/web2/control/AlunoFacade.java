package br.ufrrj.web2.control;

import java.util.Calendar;

import br.ufrrj.web2.model.dao.AlunoDAO;
import br.ufrrj.web2.model.dao.CursoDAO;
import br.ufrrj.web2.model.entities.AlunoEntity;
import br.ufrrj.web2.model.entities.CursoEntity;

public class AlunoFacade {
	
	
	public AlunoFacade() {
		
	}
	
	public AlunoEntity procurarAlunoPorNomeDataNasc(String nomeAluno, Calendar dataNasc) {
		
		AlunoDAO alunoDAO = new AlunoDAO();
		
		AlunoEntity alunoEntity;
		alunoEntity = alunoDAO.findByNameAndDateOfBirth(nomeAluno, dataNasc);
				
		return alunoEntity;
	}
			
	public AlunoEntity cadastrarAluno(String nomeAluno,Calendar dataNasc,String nomeCurso) {
	
		try {
			
			CursoDAO cursoDAO = new CursoDAO();
			AlunoDAO alunoDAO = new AlunoDAO();
						
			AlunoEntity alunoEntity = new AlunoEntity();
			alunoEntity.setNome(nomeAluno);
			alunoEntity.setDataNasc(dataNasc);
	
			cursoDAO.begin();
			CursoEntity cursoEntity = cursoDAO.findByName(nomeCurso);
			
			if (cursoEntity == null) {
				System.out.println("cursoEntity Nulo!");
			}
			
			cursoEntity.addAluno(alunoEntity);
			
			alunoEntity.setCurso(cursoEntity);
			
			alunoDAO.begin();
			alunoDAO.persist(alunoEntity);			
			alunoDAO.commit();
			
			//Vejam o cascade do relacionamento de aluno. Ao salvar aluno, curso não será atualizado. Tenho que cuidar disso.
			cursoDAO.merge(cursoEntity);
			
			cursoDAO.commit();
			
			return alunoEntity;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return null;
		
	}
	
	
}
