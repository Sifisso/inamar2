package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.AreaPerfilActoDao;
import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.service.AreaPerfilActoService;

@Service("areaPerfilActoService")
public class AreaPerfilActoServiceImpl extends GenericServiceImpl<AreaPerfilActo> implements AreaPerfilActoService{

	@Autowired
	private AreaPerfilActoDao _apaDao;

	@Override
	public List<AreaPerfilActo> findByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return _apaDao.findByUserRole(userRole);
	}
	
	
	
	@Override
	public List<UserRoleArea> findAreaByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return _apaDao.findAreaByUserRole(userRole);
	}



	@Override
	public List<AreaPerfilActo> findActoByUserRoleArea(List<UserRoleArea> listUserRoleAreas) {
		// TODO Auto-generated method stub
		return _apaDao.findActoByUserRoleArea(listUserRoleAreas);
	}

	
	
	
}
