package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.ActividadeLicenca;

public interface ActividadeLicencaService extends GenericService<ActividadeLicenca>{

	public List<ActividadeLicenca> findActividadesByActivo();


}
