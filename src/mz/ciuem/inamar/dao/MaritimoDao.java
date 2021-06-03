package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Maritimo;

public interface MaritimoDao extends GenericDao<Maritimo>{

	public List<Maritimo> findByNome(String nome);

	public List<Maritimo> findByNrInscricao(String nrInscricaoMaritima);

}
