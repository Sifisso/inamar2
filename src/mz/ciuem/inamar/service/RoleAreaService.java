package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.RoleActos;
import mz.ciuem.inamar.entity.UserRoleArea;


public interface RoleAreaService extends GenericService<RoleActos>{

	public List<RoleActos> findPerfilByArea(RoleActos actos);

}



