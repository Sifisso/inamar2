package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoDestinoDao;
import mz.ciuem.inamar.entity.PeticaoDestino;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
@Repository
public class PeticaoDestinoDaoImpl  extends GenericDaoImpl<PeticaoDestino> implements PeticaoDestinoDao{
	/*
	@SuppressWarnings("unchecked")
	public List<PeticaoDestino> buscarPeticoesPorArea(UserRole userRole) {
		org.hibernate.Query query = getCurrentSession().createQuery(" select pd from PeticaoDestino pd "
				+ " join fetch pd.userRoleAreaDestino urad "
				+ " join fetch urad.userRoleArea ura "
				+ " join fetch ura.area a  "
				+ " join fetch ura.userRole ur  "
				+ "where ura.userRole.id=:ur.id");
		query.setParameter("userRole", userRole);                                                              
		return query.list();
	}*/

	@SuppressWarnings("unchecked")
	public List<PeticaoDestino> buscarPeticoesPorArea(UserRole userRole) {
		Query query = getCurrentSession().createQuery("select  pd from PeticaoDestino pd "
				+ " join fetch pd.userRoleAreaDestino urad "
				+ " join fetch urad.userRoleArea ura "
				+ " join fetch ura.userRole ur  ");
		//+ " where ur.id=ura.UserRole.id ");
		//query.setParameter("userRole", userRole);                                                              
		return query.list();

	}
	
	@SuppressWarnings("unchecked")
	public List<PeticaoDestino> buscarPeticoesPorAreaTeste(UserRole userRole,UserRoleArea userRoleArea,UserRoleAreaDestino userRoleAreaDestino  ) {
		//Query query = getCurrentSession().createSQLQuery("select   r.rolename from peticao_destino pd join user_role_area_destino urad join user_role_area ura join roles r  where ura.userRole_id=r.id and urad.userRoleArea_id=urad.id and pd.userRoleAreaDestino_id=pd.id").addEntity(PeticaoDestino.class);   
		//List<PeticaoDestino> lista=  query.list();
		return null;

	}

}
