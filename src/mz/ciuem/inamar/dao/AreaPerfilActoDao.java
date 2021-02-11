package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;

public interface AreaPerfilActoDao extends GenericDao<AreaPerfilActo>{

	public List<AreaPerfilActo> findByUserRole(UserRole userRole);

	public List<UserRoleArea> findAreaByUserRole(UserRole userRole);

	public List<AreaPerfilActo> findActoByUserRoleArea(List<UserRoleArea> listUserRoleAreas);

	public List<AreaPerfilActo> findActosByArea(Area area);

	public List<UserRoleArea> findArePerfilByArea(Area area,UserRole userRoleParametro);

}
