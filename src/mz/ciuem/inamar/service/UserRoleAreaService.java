package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.UserRoleArea;

public interface UserRoleAreaService extends GenericService<UserRoleArea>{

	public List<UserRoleArea> findPerfilByArea();

	public List<UserRoleArea> findPerfilByAreaArea(Area area);
}
