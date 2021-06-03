package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.EmbarcacoesDao;
import mz.ciuem.inamar.entity.Embarcacoes;

@Repository
public class EmbarcacoesDaoImpl extends GenericDaoImpl<Embarcacoes> implements EmbarcacoesDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Embarcacoes> findByNome(String nome){
		org.hibernate.Query query = getCurrentSession().createQuery("select e from Embarcacoes e where e.nomeEmbaracao like :nome");
		
		query.setParameter("nome", "%"+nome+"%");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Embarcacoes> findByNrExpediente(String nrExpediente){
		org.hibernate.Query query = getCurrentSession().createQuery("select e from Embarcacoes e where e.nrRegisto like :nrExpediente");
		
		query.setParameter("nrExpediente", "%"+nrExpediente+"%");
		return query.list();
	}
	
	
}
