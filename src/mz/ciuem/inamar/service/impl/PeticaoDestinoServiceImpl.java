package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoDestinoDao;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoDestino;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;
import mz.ciuem.inamar.service.PeticaoDestinoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("peticaoDestinoService")
public class PeticaoDestinoServiceImpl extends GenericServiceImpl<PeticaoDestino> implements PeticaoDestinoService {

	@Autowired
	private PeticaoDestinoDao dao;
	@Override
	public List<PeticaoDestino> buscarPeticoesPorArea(UserRole userRole) {
		// TODO Auto-generated method stub
		return dao.buscarPeticoesPorArea(userRole);
	}
	public List<PeticaoDestino> buscarPeticoesPorAreaTeste(UserRole userRole,UserRoleArea userRoleArea,UserRoleAreaDestino userRoleAreaDestino){
		return dao.buscarPeticoesPorAreaTeste(userRole,userRoleArea,userRoleAreaDestino);	
	}
	@Override
	public List<PeticaoDestino> findDestinoByPeticao(Peticao peticao) {
		return dao.findDestinoByPeticao(peticao);
	}

}
