package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.RoleActosDao;
import mz.ciuem.inamar.dao.UserRoleAreaDao;
import mz.ciuem.inamar.entity.RoleActos;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.service.RoleActosService;
import mz.ciuem.inamar.service.UserRoleAreaService;
@Service("roleActosService")
public class RoleActosServicerImpl extends GenericServiceImpl<RoleActos> implements RoleActosService{

	@Autowired
	public RoleActosDao _uraDao;
	@Override
	public List<RoleActos> findPerfilByArea(UserRole perfil) {
		// TODO Auto-generated method stub
		return  _uraDao.findPerfilByArea(perfil);
	}

}
