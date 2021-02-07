package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;


import mz.ciuem.inamar.dao.ActosAdminDao;
import mz.ciuem.inamar.entity.ActosAdmin;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;

@Repository
public class ActosAdminDaoImpl extends GenericDaoImpl<ActosAdmin> implements ActosAdminDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ActosAdmin> findActosByArea(Area area) {
		Query query = getCurrentSession().createQuery("select actdm from ActosAdmin actdm "
				+ " join fetch actdm.areaPerfilActo apa "
				+ " join fetch apa.actos act "
				+ " join fetch apa.userRoleArea ura "
				+ " join fetch ura.area ar "
				+ " join fetch ura.userRole ur "
				+ " where ar=:area");
		
		query.setParameter("area", area);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AreaPerfilActo> findAreaByUserRoleArea(UserRoleArea userRoleArea) {
		
		Query query = getCurrentSession().createQuery("select distinct apa from AreaPerfilActo apa "
				+ " join fetch apa.userRoleArea ura "
				+ " join fetch ura.area a "
				+ " join fetch ura.userRole ur "
				+ " where ura=:userRoleArea");
		query.setParameter("userRoleArea", userRoleArea);
		
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleArea> findArePerfilByArea(Area area){
		org.hibernate.Query query = getCurrentSession().createQuery("select distinct ura from UserRoleArea ura "
				+ " join fetch ura.area a "
				+ " join fetch ura.userRole ur "
				+ "where a=:area");
		query.setParameter("area", area);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AreaPerfilActo> findActoByUserRoleArea(List<UserRoleArea> listUserRoleAreas) {
		
		
		Query query = getCurrentSession().createQuery("select distinct apa from AreaPerfilActo apa "
				+ " join fetch apa.actos acto "
				+ " join fetch apa.userRoleArea ura "
				+ " where ura in (:userRoleArea)");
		query.setParameterList("userRoleArea", listUserRoleAreas);
		
		return query.list();
		
	}
	
}
