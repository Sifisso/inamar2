package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.UserRoleAreDestinoDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;

@Repository
public class UserRoleAreaDestinoDaoImpl extends GenericDaoImpl<UserRoleAreaDestino> implements UserRoleAreDestinoDao{

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleAreaDestino> findPerfilByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		org.hibernate.Query query = getCurrentSession().createQuery("select urad from UserRoleAreaDestino urad "
				+ " join fetch urad.userRoleArea ura"
				+ " join fetch ura.area a"
				+ " join fetch ura.userRole ur"
				+ " where ur=:userRole");
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
	public List<UserRoleArea> findArePerfilByArea(Area area, UserRole userRole){
		org.hibernate.Query query = getCurrentSession().createQuery("select distinct ura from UserRoleArea ura "
				+ " join fetch ura.area a "
				+ " join fetch ura.userRole ur "
				+ " where a=:area and ur=:userRole ");
		query.setParameter("area", area);
		query.setParameter("userRole", userRole);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleAreaDestino> findDestinoByUserRoleArea(List<UserRoleArea> listUserRoleAreas) {
		
		
		Query query = getCurrentSession().createQuery("select distinct urad from UserRoleAreaDestino urad "
				+ " join fetch urad.userRoleArea ura "
				+ " join fetch urad.userRole ur "
				+ " where ura in (:userRoleArea)");
		query.setParameterList("userRoleArea", listUserRoleAreas);
		
		return query.list();
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleAreaDestino> findDestinoByUserRoleAreaDestino() {
		
		
		Query query = getCurrentSession().createQuery("select distinct urad from UserRoleAreaDestino urad "
				+ " join fetch urad.userRole ur "
				);
		
		return query.list();
		
	}
	
	
	
}
