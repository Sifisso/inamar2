package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.ActosAdmin;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;

public interface ActosAdminService extends GenericService<ActosAdmin> {

	public List<ActosAdmin> findActosByArea(Area area);
	
	public List<AreaPerfilActo> findAreaByUserRoleArea(UserRoleArea userRoleArea);
	
	public List<UserRoleArea> findArePerfilByArea(Area area);
	
	public List<AreaPerfilActo> findActoByUserRoleArea(List<UserRoleArea> listUserRoleAreas);
}
