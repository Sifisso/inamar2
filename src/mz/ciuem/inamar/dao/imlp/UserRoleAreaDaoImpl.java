package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.UserRoleAreaDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;

@Repository
public class UserRoleAreaDaoImpl extends GenericDaoImpl<UserRoleArea> implements UserRoleAreaDao{

	@SuppressWarnings("unchecked")
	@Override
	
	public List<UserRoleArea> findPerfilByArea(){
		org.hibernate.Query query = getCurrentSession().createQuery("select ura from UserRoleArea ura "
				+ "join fetch ura.userRole ur "
				+ "join fetch ura.area a "
				+ "");
		//query.setParameter("area", area);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	
	public List<UserRoleArea> findPerfilByAreaArea(Area area){
		org.hibernate.Query query = getCurrentSession().createQuery("select ura from UserRoleArea ura "
				+ "join fetch ura.userRole ur "
				+ "join fetch ura.area a "
				+ "where ura.area=:area");
		query.setParameter("area", area);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleArea> findPerfilByUserRole(UserRole userRole) {
		org.hibernate.Query query = getCurrentSession().createQuery("select ura from UserRoleArea ura "
				+ "join fetch ura.userRole ur "
				+ "join fetch ura.area a "
				+ "where ur=:userRole");
		query.setParameter("userRole", userRole);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleArea> findByUserRoleArea(String userRole, Area area) {
		org.hibernate.Query query = getCurrentSession().createQuery("select ura from UserRoleArea ura "
				+ " join fetch ura.userRole ur "
				+ " join fetch ura.area a "
				+ " where ur.rolename like:userRole and a=:area");
		query.setParameter("userRole", "%"+userRole+"%");
		query.setParameter("area", area);
		
		return query.list();
	}
}
