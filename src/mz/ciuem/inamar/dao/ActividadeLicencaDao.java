package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.ActividadeLicenca;

public interface ActividadeLicencaDao extends GenericDao<ActividadeLicenca>{

	public List<ActividadeLicenca> findActividadesByActivo();

}
