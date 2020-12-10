package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.UserRole;

public interface AreaPerfilActoDao extends GenericDao<AreaPerfilActo>{

	public List<AreaPerfilActo> findByUserRole(UserRole userRole);

}
