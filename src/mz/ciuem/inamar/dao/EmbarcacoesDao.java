package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Embarcacoes;

public interface EmbarcacoesDao extends GenericDao<Embarcacoes>{

	public List<Embarcacoes> findByNome(String nome);

	public List<Embarcacoes> findByNrExpediente(String nrExpediente);

}
