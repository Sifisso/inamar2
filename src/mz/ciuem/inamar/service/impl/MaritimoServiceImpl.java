package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.MaritimoDao;
import mz.ciuem.inamar.entity.Maritimo;
import mz.ciuem.inamar.service.MaritimoService;

@Service("maritimoService")
public class MaritimoServiceImpl extends GenericServiceImpl<Maritimo> implements MaritimoService{

	@Autowired
	private MaritimoDao _mDao;
	
	@Override
	public List<Maritimo> findByNome(String nome) {
		return _mDao.findByNome(nome);
	}

	@Override
	public List<Maritimo> findByNrInscricao(String nrInscricaoMaritima) {
		return _mDao.findByNrInscricao(nrInscricaoMaritima);
	}

}
