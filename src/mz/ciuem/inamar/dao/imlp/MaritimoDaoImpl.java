package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.MaritimoDao;
import mz.ciuem.inamar.entity.Maritimo;

@Repository
public class MaritimoDaoImpl extends GenericDaoImpl<Maritimo> implements MaritimoDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Maritimo> findByNome(String nome){
		org.hibernate.Query query = getCurrentSession().createQuery("select m from Maritimo m where m.nome like :nome");
		query.setParameter("nome", "%"+nome+"%");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Maritimo> findByNrInscricao(String nrInscricaoMaritima){
		org.hibernate.Query query = getCurrentSession().createQuery("select m from Maritimo m where m.nrInscricaoMaritima like :nrInscricaoMaritima");
		query.setParameter("nrInscricaoMaritima", "%"+nrInscricaoMaritima+"%");
		return query.list();
	}
	
}
