package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.ActosAdmin;
import mz.ciuem.inamar.entity.Area;

public interface ActosAdminDao extends GenericDao<ActosAdmin>{

	public List<ActosAdmin> findActosByArea(Area area);



}
