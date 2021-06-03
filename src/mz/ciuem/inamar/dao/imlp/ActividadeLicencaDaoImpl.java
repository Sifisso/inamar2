package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.ActividadeLicencaDao;
import mz.ciuem.inamar.entity.ActividadeLicenca;

@Repository
public class ActividadeLicencaDaoImpl extends GenericDaoImpl<ActividadeLicenca> implements ActividadeLicencaDao{

	@Override
	public List<ActividadeLicenca> findActividadesByActivo() {
		Query query = getCurrentSession().createQuery("SELECT al FROM ActividadeLicenca al where al.isActivo=true");
		 return query.list();
	}

}
