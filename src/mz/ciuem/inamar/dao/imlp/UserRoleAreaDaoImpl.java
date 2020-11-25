package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.UserRoleAreaDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.UserRoleArea;

@Repository
public class UserRoleAreaDaoImpl extends GenericDaoImpl<UserRoleArea> implements UserRoleAreaDao{

	@SuppressWarnings("unchecked")
	@Override
	
	public List<UserRoleArea> findPerfilByArea(Area area){
		org.hibernate.Query query = getCurrentSession().createQuery("select ura from UserRoleArea ura "
				+ "join fetch ura.userRole ur "
				+ "join fetch ura.area a "
				+ "where a=:area");
		query.setParameter("area", area);
		return query.list();
	}
}
