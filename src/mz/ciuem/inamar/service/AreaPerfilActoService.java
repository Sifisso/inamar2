package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.UserRole;

public interface AreaPerfilActoService extends GenericService<AreaPerfilActo> {

	public List<AreaPerfilActo> findByUserRole(UserRole userRole);
}
