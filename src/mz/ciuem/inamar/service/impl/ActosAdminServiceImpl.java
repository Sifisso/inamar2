package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.ActosAdminDao;
import mz.ciuem.inamar.entity.ActosAdmin;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.service.ActosAdminService;

@Service("actosAdminService")
public class ActosAdminServiceImpl extends GenericServiceImpl<ActosAdmin> implements ActosAdminService{

	@Autowired
	private ActosAdminDao _dao;
	
	@Override
	public List<ActosAdmin> findActosByArea(Area area) {
		return _dao.findActosByArea(area);
	}
	
	@Override
	public List<AreaPerfilActo> findAreaByUserRoleArea(UserRoleArea userRoleArea) {
		return _dao.findAreaByUserRoleArea(userRoleArea);
	}

	@Override
	public List<UserRoleArea> findArePerfilByArea(Area area) {
		// TODO Auto-generated method stub
		return _dao.findArePerfilByArea(area);
	}

	@Override
	public List<AreaPerfilActo> findActoByUserRoleArea(List<UserRoleArea> listUserRoleAreas) {
		
		return _dao.findActoByUserRoleArea(listUserRoleAreas);
	}
}
