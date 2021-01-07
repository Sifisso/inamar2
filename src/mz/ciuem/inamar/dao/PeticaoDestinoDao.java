package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoDestino;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;

public interface PeticaoDestinoDao extends GenericDao<PeticaoDestino>{
	public List<PeticaoDestino> buscarPeticoesPorArea(UserRole userRole);
	

	public List<PeticaoDestino> buscarPeticoesPorAreaTeste(UserRole userRole,UserRoleArea userRoleArea,UserRoleAreaDestino userRoleAreaDestino);
	public List<PeticaoDestino> findDestinoByPeticao(Peticao peticao);
}
