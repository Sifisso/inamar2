package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.AreaPerfilActoDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;

@Repository
public class AreaPerfilActoDaoImpl extends GenericDaoImpl<AreaPerfilActo> implements AreaPerfilActoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<AreaPerfilActo> findByUserRole(UserRole userRole) {
		Query query = getCurrentSession().createQuery("select distinct apa from AreaPerfilActo apa "
				+ " join fetch apa.userRoleArea ura "
				+ " join fetch ura.userRole ur "
				+ " left join fetch ura.area "
				+ " where ura.userRole=:userRole");
		query.setParameter("userRole", userRole);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleArea> findAreaByUserRole(UserRole userRole) {
		
		Query query = getCurrentSession().createQuery("select distinct ura from UserRoleArea ura "
				+ " join fetch ura.area a "
				+ " join fetch ura.userRole ur "
				+ " where ur=:userRole");
		query.setParameter("userRole", userRole);
		
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AreaPerfilActo> findActosByArea(Area area) {
		
		Query query = getCurrentSession().createQuery("select distinct apa from AreaPerfilActo apa "
				+ " join fetch apa.userRoleArea ura "
				+ " join fetch ura.userRole ur "
				+ " join fetch ura.area a "
				+ " where a=:area");
		query.setParameter("area", area);
		
		return query.list();
		
	}
	
}
