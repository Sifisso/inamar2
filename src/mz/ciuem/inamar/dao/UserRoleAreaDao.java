package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;

public interface UserRoleAreaDao extends GenericDao<UserRoleArea>{


	public List<UserRoleArea> findPerfilByUserRole(UserRole userRole);

	public List<UserRoleArea> findPerfilByArea();

	public List<UserRoleArea> findPerfilByAreaArea(Area area);
	
	public List<UserRoleArea> findByUserRoleArea(Area area);
	
	



}
