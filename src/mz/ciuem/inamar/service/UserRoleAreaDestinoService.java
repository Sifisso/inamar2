package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;

public interface UserRoleAreaDestinoService extends GenericService<UserRoleAreaDestino>{


	public List<UserRoleAreaDestino> findPerfilByUserRole(UserRole userRole);
	
	public List<UserRoleArea> findAreaByUserRole(UserRole userRole);
	
	public List<UserRoleAreaDestino> findDestinoByUserRoleArea(List<UserRoleArea> listUserRoleAreas);
	
}
