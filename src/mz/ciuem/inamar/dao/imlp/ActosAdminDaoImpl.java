package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;


import mz.ciuem.inamar.dao.ActosAdminDao;
import mz.ciuem.inamar.entity.ActosAdmin;
import mz.ciuem.inamar.entity.Area;

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
				+ " join fetch actdm.peticao pet"
				+ " join fetch pet.pedido ped "
				+ " join fetch ped.tipoPedido tipPed "
				+ " join fetch tipPed.area ar "
				+ " where ar=:area");
		
		query.setParameter("area", area);
		return query.list();
	}
	
}
