package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoDestino;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;

public interface PeticaoDestinoService extends GenericService<PeticaoDestino> {
	public List<PeticaoDestino> buscarPeticoesPorArea(UserRole userRole);
	public List<PeticaoDestino> buscarPeticoesPorAreaTeste(UserRole userRole,UserRoleArea userRoleArea,UserRoleAreaDestino userRoleAreaDestino);
}
