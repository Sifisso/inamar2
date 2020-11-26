package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.RoleActos;
import mz.ciuem.inamar.entity.UserRole;

public interface RoleActosDao extends GenericDao<RoleActos>{
	public List<RoleActos> findPerfilByArea(UserRole perfil);



}
