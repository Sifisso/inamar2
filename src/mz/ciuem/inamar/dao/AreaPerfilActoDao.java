package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Actos;
import mz.ciuem.inamar.entity.AreaPerfilActo;

public interface AreaPerfilActoDao extends GenericDao<AreaPerfilActo>{

	public List<AreaPerfilActo> findByUserRole(Actos _actos);

}
