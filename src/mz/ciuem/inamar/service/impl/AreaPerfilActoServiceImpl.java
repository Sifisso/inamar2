package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.AreaPerfilActoDao;
import mz.ciuem.inamar.entity.Actos;
import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.service.AreaPerfilActoService;

@Service("areaPerfilActoService")
public class AreaPerfilActoServiceImpl extends GenericServiceImpl<AreaPerfilActo> implements AreaPerfilActoService{

	@Autowired
	private AreaPerfilActoDao _apaDao;

	@Override
	public List<AreaPerfilActo> findByUserRole(Actos _actos) {
		// TODO Auto-generated method stub
		return _apaDao.findByUserRole(_actos);
	}
	
	
	
}
