package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.UserRoleAreDestinoDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;
import mz.ciuem.inamar.service.UserRoleAreaDestinoService;

@Service("userRoleAreaDestinoService")


public class UserRoleAreaDestinoServiceImpl extends GenericServiceImpl<UserRoleAreaDestino> implements UserRoleAreaDestinoService{

	@Autowired
	private UserRoleAreDestinoDao dao;
	
	@Override
	public List<UserRoleAreaDestino> findPerfilByUserRole(UserRole userRole) {
		return dao.findPerfilByUserRole(userRole);
	}
	
	public List<UserRoleArea> findAreaByUserRole(UserRole userRole){
		return dao.findAreaByUserRole(userRole);
	}

	@Override
	public List<UserRoleAreaDestino> findDestinoByUserRoleArea(List<UserRoleArea> listUserRoleAreas) {
		return dao.findDestinoByUserRoleArea(listUserRoleAreas);
	}
	public List<UserRoleArea> findArePerfilByArea(Area area, UserRole userRole){
		return dao.findArePerfilByArea(area, userRole);
	}
	public List<UserRoleAreaDestino> findDestinoByUserRoleAreaDestino() {
		return dao.findDestinoByUserRoleAreaDestino();
	}

}
