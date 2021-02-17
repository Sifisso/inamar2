package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;

public interface UserRoleAreDestinoDao extends GenericDao<UserRoleAreaDestino>{


	public List<UserRoleAreaDestino> findPerfilByUserRole(UserRole userRole);

	public List<UserRoleArea> findAreaByUserRole(UserRole userRole);

	public List<UserRoleAreaDestino> findDestinoByUserRoleArea(List<UserRoleArea> listUserRoleAreas);
	public List<UserRoleArea> findArePerfilByArea(Area area, UserRole userRole);
	public List<UserRoleAreaDestino> findDestinoByUserRoleAreaDestino();
}
