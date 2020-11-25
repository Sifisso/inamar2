package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.UserRoleArea;

public interface UserRoleAreaDao extends GenericDao<UserRoleArea>{

	public List<UserRoleArea> findPerfilByArea(Area area);

}
