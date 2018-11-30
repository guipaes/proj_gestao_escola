package br.ufrrj.web2.control;

import java.util.Calendar;
import java.util.List;

import br.ufrrj.web2.model.dao.AlunoDAO;
import br.ufrrj.web2.model.dao.CursoDAO;
import br.ufrrj.web2.model.dao.DisciplinaDAO;
import br.ufrrj.web2.model.entities.AlunoEntity;
import br.ufrrj.web2.model.entities.CursoEntity;
import br.ufrrj.web2.model.entities.DisciplinaEntity;

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
	
	public void matricularAlunoDisciplinasEscolhidas(AlunoEntity alunoEntity, String[] nomes_disciplinas_selecionadas) {
		
		AlunoDAO alunoDAO = new AlunoDAO();
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		
		List<DisciplinaEntity> disciplinas = alunoEntity.getCurso().getDisciplinas();
		for (String nome_disciplina_selecionada : nomes_disciplinas_selecionadas) {
			
			//aproveitando que trouxe tudo com Eager. Se tivesse colocado Lazy, teria que fazer consultas no BD.
			for (DisciplinaEntity disciplina: disciplinas) {
					
				if (disciplina.getNomeDisciplina().equals(nome_disciplina_selecionada)) {
					
					disciplina.addAluno(alunoEntity);
					alunoEntity.addDisciplina(disciplina);

					//o certo seria transferir para Facades
					alunoDAO.begin();
					disciplinaDAO.begin();					
					alunoDAO.merge(alunoEntity);
					disciplinaDAO.merge(disciplina);
					alunoDAO.commit();
					disciplinaDAO.commit();
					
					break;
				
				}//fim do if					
				
			}//fim do for(DisciplinaEntity...
			
		}//fim do for(String...
		
	}//fim do método
	


	//método não testado
	public void matricularAlunoDisciplinasEscolhidas(AlunoEntity alunoEntity, List<DisciplinaEntity> disciplinasSelecionadas) {
		AlunoDAO alunoDAO = new AlunoDAO();
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
				
		//aproveitando que trouxe tudo com Eager. Se tivesse colocado Lazy, teria que fazer consultas no BD.
		for (DisciplinaEntity disciplina: disciplinasSelecionadas) {
				
				disciplina.addAluno(alunoEntity);
				alunoEntity.addDisciplina(disciplina);

				//o certo seria transferir para Facades
				alunoDAO.begin();
				disciplinaDAO.begin();					
				alunoDAO.merge(alunoEntity);
				disciplinaDAO.merge(disciplina);
				alunoDAO.commit();
				disciplinaDAO.commit();
				
				break;
			
		}//fim do for(DisciplinaEntity...
		
	}
		
}
