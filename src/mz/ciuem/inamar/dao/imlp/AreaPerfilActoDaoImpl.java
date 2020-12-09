package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.AreaPerfilActoDao;
import mz.ciuem.inamar.entity.Actos;
import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.UserRole;

@Repository
public class AreaPerfilActoDaoImpl extends GenericDaoImpl<AreaPerfilActo> implements AreaPerfilActoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<AreaPerfilActo> findByUserRole(Actos _actos) {
		Query query = getCurrentSession().createQuery("select distinct apa from AreaPerfilActo apa "
				+ " join fetch apa.userRoleArea ura "
				+ " join fetch ura.userRole ur "
				+ " left join fetch ura.area "
				+ " join fetch apa.actos a"
				+ " where apa.actos=:_actos");
		query.setParameter("_actos", _actos);
		return query.list();
	}
	
}
