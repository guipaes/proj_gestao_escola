package br.ufrrj.web2.model.dao;

import java.util.Calendar;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import br.ufrrj.web2.model.entities.AlunoEntity;

public class AlunoDAO extends DAO<AlunoEntity> {

	public AlunoEntity findByNameAndDateOfBirth(String nomeAluno, Calendar dataNasc) {
		
		Query q = getManager().createQuery("select a from AlunoEntity a where a.nome = :nome AND a.dataNasc = :nascimento");
		q.setParameter("nome", nomeAluno);		
		q.setParameter("nascimento", dataNasc,TemporalType.DATE);
		
		//Considerando que só existe um com mesmo nome e dataNasc.
		AlunoEntity a = null;
		
		try{
			a = (AlunoEntity)q.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return a;
	}
	
}
