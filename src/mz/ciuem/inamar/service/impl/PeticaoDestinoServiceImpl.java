package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoDestinoDao;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoDestino;
import mz.ciuem.inamar.service.PeticaoDestinoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("peticaoDestinoService")
public class PeticaoDestinoServiceImpl extends GenericServiceImpl<PeticaoDestino> implements PeticaoDestinoService {

	@Autowired
	private PeticaoDestinoDao dao;
	@Override
	public List<PeticaoDestino> buscarPeticoesPorArea() {
		// TODO Auto-generated method stub
		return dao.buscarPeticoesPorArea();
	}

}
