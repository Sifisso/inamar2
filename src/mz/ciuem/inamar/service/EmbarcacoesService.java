package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Embarcacoes;

public interface EmbarcacoesService extends GenericService<Embarcacoes>{

	public List<Embarcacoes> findByNome(String nome);

	public List<Embarcacoes> findByNrExpediente(String nrExpediente);

}
