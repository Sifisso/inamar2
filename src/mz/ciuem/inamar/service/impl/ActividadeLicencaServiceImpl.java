package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.ActividadeLicencaDao;
import mz.ciuem.inamar.entity.ActividadeLicenca;
import mz.ciuem.inamar.service.ActividadeLicencaService;

@Service("actividadeLicencaService")
public class ActividadeLicencaServiceImpl extends GenericServiceImpl<ActividadeLicenca> implements ActividadeLicencaService{

	@Autowired
	private ActividadeLicencaDao _actLicDao;
	@Override
	public List<ActividadeLicenca> findActividadesByActivo() {
		return _actLicDao.findActividadesByActivo();
	}

}
