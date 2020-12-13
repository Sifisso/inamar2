package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;

public interface AreaPerfilActoService extends GenericService<AreaPerfilActo> {

	public List<AreaPerfilActo> findByUserRole(UserRole userRole);

	public List<UserRoleArea> findAreaByUserRole(UserRole userRole);

	public List<AreaPerfilActo> findActoByUserRoleArea(List<UserRoleArea> listUserRoleAreas);
}