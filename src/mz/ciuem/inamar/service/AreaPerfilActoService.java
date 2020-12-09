package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Actos;
import mz.ciuem.inamar.entity.AreaPerfilActo;

public interface AreaPerfilActoService extends GenericService<AreaPerfilActo> {

	public List<AreaPerfilActo> findByUserRole(Actos _actos);
}
