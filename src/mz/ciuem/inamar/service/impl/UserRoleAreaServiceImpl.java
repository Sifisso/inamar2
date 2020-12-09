package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.UserRoleAreaDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.service.UserRoleAreaService;

@Service("userRoleAreaService")



public class UserRoleAreaServiceImpl extends GenericServiceImpl<UserRoleArea> implements UserRoleAreaService{

	
	@Autowired
	public UserRoleAreaDao _uraDao;
	
	@Override
	public List<UserRoleArea> findPerfilByArea() {
		// TODO Auto-generated method stub
		return _uraDao.findPerfilByArea();
	}
	
	@Override
	public List<UserRoleArea> findPerfilByAreaArea(Area area) {
		// TODO Auto-generated method stub
		return _uraDao.findPerfilByAreaArea(area);
	}
	
	@Override
	public List<UserRoleArea> findPerfilByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return _uraDao.findPerfilByUserRole(userRole);
	}

}
