package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.UserRoleAreDestinoDao;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;

@Repository
public class UserRoleAreaDestinoDaoImpl extends GenericDaoImpl<UserRoleAreaDestino> implements UserRoleAreDestinoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleAreaDestino> findPerfilByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		org.hibernate.Query query = getCurrentSession().createQuery("select urad from UserRoleAreaDestino urad "
				+ "join fetch urad.userRoleArea ura "
				+ "join fetch ura.area a "
				+ " join fetch ura.userRole ur"
				+ "");
		//query.setParameter("userRole", userRole);
		return query.list();
	}

}
