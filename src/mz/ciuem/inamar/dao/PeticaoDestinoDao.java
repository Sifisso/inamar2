package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoDestino;
import mz.ciuem.inamar.entity.UserRoleArea;

public interface PeticaoDestinoDao extends GenericDao<PeticaoDestino>{
	public List<PeticaoDestino> buscarPeticoesPorArea();


}
