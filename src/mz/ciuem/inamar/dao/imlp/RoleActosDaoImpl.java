package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.RoleActosDao;
import mz.ciuem.inamar.entity.RoleActos;
import mz.ciuem.inamar.entity.UserRole;

@Repository
public class RoleActosDaoImpl extends GenericDaoImpl<RoleActos> implements RoleActosDao{

	private RoleActosDao _raDao;
	@Override
	public List<RoleActos> findPerfilByArea(UserRole perfil) {
		// TODO Auto-generated method stub
		return _raDao.findPerfilByArea(perfil);
	}

}
