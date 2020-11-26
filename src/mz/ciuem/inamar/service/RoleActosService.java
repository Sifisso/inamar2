package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.RoleActos;
import mz.ciuem.inamar.entity.UserRole;

public interface RoleActosService extends GenericService<RoleActos>{

	public List<RoleActos> findPerfilByArea(UserRole perfil);

}