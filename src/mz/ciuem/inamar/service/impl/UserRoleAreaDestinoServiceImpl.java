package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.UserRoleAreDestinoDao;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;
import mz.ciuem.inamar.service.UserRoleAreaDestinoService;

@Service("userRoleAreaDestinoService")


public class UserRoleAreaDestinoServiceImpl extends GenericServiceImpl<UserRoleAreaDestino> implements UserRoleAreaDestinoService{

	@Autowired
	private UserRoleAreDestinoDao dao;
	
	@Override
	public List<UserRoleAreaDestino> findPerfilByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return dao.findPerfilByUserRole(userRole);
	}

}
