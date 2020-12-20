package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoDestinoDao;
import mz.ciuem.inamar.entity.PeticaoDestino;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
@Repository
public class PeticaoDestinoDaoImpl  extends GenericDaoImpl<PeticaoDestino> implements PeticaoDestinoDao{

	@SuppressWarnings("unchecked")
	public List<PeticaoDestino> buscarPeticoesPorArea() {
		Query query = getCurrentSession().createQuery(" select pd from PeticaoDestino pd "
				+ " join fetch pd.userRoleAreaDestino urad "
				+ " join fetch urad.userRoleArea ura "
				+ " join fetch ura.area a  "
				+ " where ura.area.id=a.id and urad.userRoleArea.id=urad.id and pd.userRoleAreaDestino.id=pd.id");
		//query.setParameter("userRole", userRole);                                                              
		return query.list();
	}

}
