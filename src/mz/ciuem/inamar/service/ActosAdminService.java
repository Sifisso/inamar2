package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.ActosAdmin;
import mz.ciuem.inamar.entity.Area;

public interface ActosAdminService extends GenericService<ActosAdmin> {

	public List<ActosAdmin> findActosByArea(Area area);
}
