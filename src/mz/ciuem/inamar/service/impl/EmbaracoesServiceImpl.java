package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.EmbarcacoesDao;
import mz.ciuem.inamar.entity.Embarcacoes;
import mz.ciuem.inamar.service.EmbarcacoesService;

@Service("embarcacoesService")
public class EmbaracoesServiceImpl extends GenericServiceImpl<Embarcacoes> implements EmbarcacoesService{

	@Autowired
	private EmbarcacoesDao _eDao;
	
	@Override
	public List<Embarcacoes> findByNome(String nome) {
		return _eDao.findByNome(nome);
	}

	@Override
	public List<Embarcacoes> findByNrExpediente(String nrExpediente) {
		return _eDao.findByNrExpediente(nrExpediente);
	}

}
