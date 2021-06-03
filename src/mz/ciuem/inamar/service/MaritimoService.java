package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Maritimo;

public interface MaritimoService extends GenericService<Maritimo>{

	public List<Maritimo> findByNome(String nome);

	public List<Maritimo> findByNrInscricao(String nrInscricaoMaritima);
}
