package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoDestino;

public interface PeticaoDestinoService extends GenericService<PeticaoDestino> {
	public List<PeticaoDestino> buscarPeticoesPorArea();
}
