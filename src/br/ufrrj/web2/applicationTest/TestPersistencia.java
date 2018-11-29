package br.ufrrj.web2.applicationTest;

import br.ufrrj.web2.model.dao.AlunoDAO;
import br.ufrrj.web2.model.dao.CursoDAO;
import br.ufrrj.web2.model.dao.DAO;
import br.ufrrj.web2.model.dao.DisciplinaDAO;
import br.ufrrj.web2.model.entities.AlunoEntity;
import br.ufrrj.web2.model.entities.CursoEntity;
import br.ufrrj.web2.model.entities.DisciplinaEntity;

public class TestPersistencia {

	/**
	 * Rode este código para popular o banco, caso deseje.
	 */
	public static void main(String[] args) {
		
		TestPersistencia tUtilitarios = new TestPersistencia();
		
				
		//Instanciando CursoDAO e DisciplinaDAO
		CursoDAO cursoDAO = new CursoDAO();
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();	
		//OBSERVEM! Iniciei o DAO ao iniciar o processamento. Não tem porque fica abrindo e fechando.
		//Pode permanecer aberto até o fim das operações.
		
		cursoDAO.open();//como o manager é um só pra aplicação, poderia abrir apenas aqui
		DisciplinaDAO.open();
						
		/**
		 * Primeiro curso e suas disciplinas
		 */
		CursoEntity c1 = new CursoEntity();
		c1.setNomeCurso("cursoWebBasico");
				
		DisciplinaEntity d1 = new DisciplinaEntity();
		d1.setNomeDisciplina("web0");
		d1.setCurso(c1);
				
		DisciplinaEntity d2 = new DisciplinaEntity();
		d2.setNomeDisciplina("web1");
		d2.setCurso(c1);
		
		DisciplinaEntity d3 = new DisciplinaEntity();
		d3.setNomeDisciplina("web2");
		d3.setCurso(c1);
		
		c1.addDisciplina(d1);
		c1.addDisciplina(d2);
		c1.addDisciplina(d3);
		
		//salvando no banco
		cursoDAO.begin();
		cursoDAO.persist(c1);
		cursoDAO.commit();
		
		//salvando no banco
//		disciplinaDAO.begin();
//		disciplinaDAO.persist(d1);
//		disciplinaDAO.persist(d2);
//		disciplinaDAO.persist(d3);
//		disciplinaDAO.commit();
		
		/**
		 * Segundo curso e suas disciplinas
		 */
		CursoEntity c2 = new CursoEntity();
		c2.setNomeCurso("cursoWebIntermediario");
				
		DisciplinaEntity d4 = new DisciplinaEntity();
		d4.setNomeDisciplina("web semantica");
		d4.setCurso(c2);
		
		DisciplinaEntity d5 = new DisciplinaEntity();
		d5.setNomeDisciplina("topicos web");
		d5.setCurso(c2);
		
		DisciplinaEntity d6 = new DisciplinaEntity();
		d6.setNomeDisciplina("web3");
		d6.setCurso(c2);
		
		c2.addDisciplina(d4);
		c2.addDisciplina(d5);
		c2.addDisciplina(d6);
		
		//Considere que o curso foi criado e várias disciplinas foram criadas junto.
		//Ou seja, uma requisição com várias disciplinas foi enviada na etapa de cadastro.
		
		//Observem que dessa vez não vou persistir as disciplinas.
		//Mesmo assim, elas serão salvas. O Cascade garante isso.
		cursoDAO.begin();
		cursoDAO.persist(c2);
		cursoDAO.commit();
		
		
		/**
		 * Terceiro curso e suas disciplinas
		 */
		CursoEntity c3 = new CursoEntity();
		c3.setNomeCurso("cursoWebAvançado");
		
		DisciplinaEntity d7 = new DisciplinaEntity();
		d7.setNomeDisciplina("padrões de projeto OO");
		d7.setCurso(c3);
		
		DisciplinaEntity d8 = new DisciplinaEntity();
		d8.setNomeDisciplina("analise de dados");
		d8.setCurso(c3);
		
		DisciplinaEntity d9 = new DisciplinaEntity();
		d9.setNomeDisciplina("IoT, SIoT e industria 4.0");
		d9.setCurso(c3);
		
		c3.addDisciplina(d7);
		c3.addDisciplina(d8);
		c3.addDisciplina(d9);
				
		DAO.begin();//perceba a diferença. Por que funciona?
		cursoDAO.persist(c3);
		DAO.commit();
		
		/**
		 * Criando alunos
		 */
		
		AlunoDAO alunoDAO = new AlunoDAO();
		
		//aluno 1
		//posso usar o construtor vazio. Um aluno será instanciado, mas observe que não tem nenhuma transação com o BD.
		AlunoEntity endrew = new AlunoEntity();
		endrew.setNome("Endrew");
		endrew.configAlunoDataNasc(11, 11, 1999);
		endrew.setCurso(c1);//agora ele pode ter transação, o campo obrigatório tem um valor.
		c1.addAluno(endrew);
		
		tUtilitarios.adicionarDisciplina(endrew,d1);
		tUtilitarios.adicionarDisciplina(endrew,d4);//não faz parte das disciplinas do curso. Meu método é meu controlador.
		tUtilitarios.adicionarDisciplina(endrew,d2);

		//salvando. considere que cada um aluno, seria um form web. Então vou salvar um por um.
		alunoDAO.begin();
		alunoDAO.persist(endrew);
		alunoDAO.commit();
		
		
		//aluno 2
		AlunoEntity caio = new AlunoEntity(c1);//usando construtor para passar o curso		
		caio.setNome("Caio");
		caio.configAlunoDataNasc(11, 11, 1997);
		c1.addAluno(caio);
		
		tUtilitarios.adicionarDisciplina(caio,d1);
		tUtilitarios.adicionarDisciplina(caio,d2);
		tUtilitarios.adicionarDisciplina(caio,d3);
		
		alunoDAO.begin();
		alunoDAO.persist(caio);
		alunoDAO.commit();
		
		//aluno 3
		AlunoEntity garrido = new AlunoEntity(c2);		
		garrido.setNome("Garrido");
		garrido.configAlunoDataNasc(11, 11, 1980);
		c2.addAluno(garrido);
		
		tUtilitarios.adicionarDisciplina(garrido, d4);
		tUtilitarios.adicionarDisciplina(garrido, d5);
		tUtilitarios.adicionarDisciplina(garrido, d6);

		DAO.begin();
		alunoDAO.persist(garrido);
		DAO.commit();
		
		
		//aluno 4
		AlunoEntity arthur = new AlunoEntity(c2);		
		arthur.setNome("Arthur");
		arthur.configAlunoDataNasc(11, 11, 1909);
		c2.addAluno(arthur);
		
		tUtilitarios.adicionarDisciplina(arthur, d4);
		tUtilitarios.adicionarDisciplina(arthur, d5);
		tUtilitarios.adicionarDisciplina(arthur, d6);
		
		DAO.begin();
		alunoDAO.persist(arthur);
		DAO.commit();

		
		
		//aluno 5
		AlunoEntity filippo = new AlunoEntity(c3);		
		filippo.setNome("Filippo");
		filippo.configAlunoDataNasc(11, 11, 1969);
		c3.addAluno(filippo);
		
		tUtilitarios.adicionarDisciplina(filippo, d7);
		tUtilitarios.adicionarDisciplina(filippo, d8);
		tUtilitarios.adicionarDisciplina(filippo, d9);
		
		DAO.begin();
		alunoDAO.persist(filippo);
		DAO.commit();

		
		
		//aluno 6
		AlunoEntity lucas = new AlunoEntity(c3);		
		lucas.setNome("Lucas");
		lucas.configAlunoDataNasc(11, 11, 1990);
		c3.addAluno(lucas);
		
		tUtilitarios.adicionarDisciplina(lucas, d7);
		tUtilitarios.adicionarDisciplina(lucas, d8);
		
		DAO.begin();
		alunoDAO.persist(lucas);
		DAO.commit();

		
		
		//aluno 7
		AlunoEntity beatriz = new AlunoEntity(c3);		
		beatriz.setNome("Beatriz");
		beatriz.configAlunoDataNasc(11, 11, 1990);		
		c3.addAluno(beatriz);
		
		tUtilitarios.adicionarDisciplina(beatriz, d7);
		DAO.begin();
		alunoDAO.persist(beatriz);
		DAO.commit();
		
		//fechando o DAO
		DAO.close();
		
	}
			
	//verifica se disciplina foi adicionada
	public void adicionarDisciplina(AlunoEntity a, DisciplinaEntity d) {
		if (!a.addDisciplina(d)) {
			d.addAluno(a);
			System.out.println("Disciplina não adicionada. Ela não faz parte da grade do curso do aluno.");
		}
	}

}
