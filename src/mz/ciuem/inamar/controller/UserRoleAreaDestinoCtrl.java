package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Actos;
import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;
import mz.ciuem.inamar.service.ActosService;
import mz.ciuem.inamar.service.AreaPerfilActoService;
import mz.ciuem.inamar.service.UserRoleAreaDestinoService;
import mz.ciuem.inamar.service.UserRoleAreaService;
import mz.ciuem.inamar.service.UserRoleService;
import net.sf.jasperreports.engine.JRException;

@SuppressWarnings({ "serial", "rawtypes" })
public class UserRoleAreaDestinoCtrl extends GenericForwardComposer{
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	//Main Div
	private Label lbl_descricaoDestino, lbl_descricaoDestino2, lbl_descricaoPerfil, lbl_descricaoPerfil2;
	private Textbox txb_nomefind;
	private Button btn_procurar;
	private Listbox lbx_perfilActo,lbx_perfilDestino;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome, txb_codigo;
	private Combobox cbx_acto, cbx_perfil;
	
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Window win_regUserRoleAreaActo;
	
	Execution ex = Executions.getCurrent();
	
	
	private AreaPerfilActo _areaPerfilActo;
	
	private UserRoleAreaDestino _UserRoleAreaDestino;
	
	private Actos _actos;
	
	private UserRoleArea _userRoleArea;
	
	@WireVariable
	private AreaPerfilActoService _areaPerfilActoService;
	
	@WireVariable
	private UserRoleAreaDestinoService _userRoleAreaDestinoService;
	
	@WireVariable
	private ActosService _actosService;
	
	@WireVariable
	private UserRoleAreaService _userRoleAreaService;
	
	@WireVariable
	private UserRoleService _userRoleService;
	
	
	private UserRole _userRole;
	
	private UserRoleAreaDestino _userRoleAreaDestino;
	
	private List<Actos> listActos = new ArrayList<Actos>();
	
	private List<UserRole> listRoles = new ArrayList<UserRole>();
	
	private List <UserRoleArea> listURArea= new ArrayList<UserRoleArea>();
	
	private List<AreaPerfilActo> listAPActo = new ArrayList<AreaPerfilActo>();
	
	private List<UserRoleAreaDestino> listURADestino = new ArrayList<UserRoleAreaDestino>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_areaPerfilActoService = (AreaPerfilActoService) SpringUtil.getBean("areaPerfilActoService");
		_userRoleAreaService = (UserRoleAreaService) SpringUtil.getBean("userRoleAreaService");
		_actosService = (ActosService) SpringUtil.getBean("actosService");
		_userRoleService = (UserRoleService) SpringUtil.getBean("userRoleService");
		_userRoleAreaDestinoService = (UserRoleAreaDestinoService) SpringUtil.getBean("userRoleAreaDestinoService");
		
		_userRoleArea =  (UserRoleArea) ex.getArg().get("_userRoleArea");
		_userRoleAreaDestino =  (UserRoleAreaDestino) ex.getArg().get("userRoleAreaDestino");
		_userRole =  (UserRole) ex.getArg().get("_userRole");
		_areaPerfilActo =  (AreaPerfilActo) Executions.getCurrent().getArg().get("areaPerfilActo");
		_actos =  (Actos) Executions.getCurrent().getArg().get("_actos");
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		listar();
		peencherDestino();
		preencherCabecalho();
	}
	
	public void onClick$btn_actualizar() throws InterruptedException {
		_UserRoleAreaDestino.setCodigo(txb_codigo.getValue());
		//_UserRoleAreaDestino.setUserRole(cbx_perfil.getSelectedItem().getValue());
		_userRoleAreaDestino.setUserRoleArea(cbx_perfil.getSelectedItem().getValue());
		
		_userRoleAreaDestinoService.update(_UserRoleAreaDestino);
		listar();
		showNotifications("Destino Actualizado com sucesso!", "info");
		limparCampos();

		}
	
	private void preencherCabecalho() {
		
		lbl_descricaoDestino.setValue(_userRoleArea.getUserRole().getRolename());
		lbl_descricaoPerfil.setValue(_userRoleArea.getArea().getNome());
		lbl_descricaoDestino2.setValue(_userRoleArea.getArea().getNome());
		lbl_descricaoPerfil2.setValue(_userRoleArea.getUserRole().getRolename());
		
	}

	public void onClick$btn_gravar(Event e) throws InterruptedException{
		
		UserRoleAreaDestino urad = new UserRoleAreaDestino();
		
		urad.setCodigo(txb_codigo.getValue());
		urad.setUserRoleArea(_userRoleArea);
		//urad.setUserRole((UserRole)cbx_perfil.getSelectedItem().getValue());
		urad.setUserRoleArea(cbx_perfil.getSelectedItem().getValue());
		
		boolean existe = false;
		
		for(UserRoleAreaDestino uraDestino: listURADestino) {
			if(uraDestino.getUserRoleArea().getUserRole().getId()==urad.getUserRoleArea().getUserRole().getId() && uraDestino.getUserRoleArea().getUserRole().getId()==urad.getUserRoleArea().getUserRole().getId()) {
				existe=true;
			}
		}
		
		if(existe==false) {
			_userRoleAreaDestinoService.create(urad);
			listar();
			showNotifications("Destino Registado com sucesso!", "info");
			limparCampos();
		}else {
			showNotifications("Configuração existente", "error");
		}
		//urad.setUserRoleArea((UserRoleArea)cbx_perfil.getSelectedItem().getValue());
		
		
		
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_perfilDestino(Event e){
		_userRoleAreaDestino = lbx_perfilDestino.getSelectedItem().getValue();
		txb_codigo.setValue(_userRoleAreaDestino.getCodigo());
	    cbx_perfil.setValue(_userRoleAreaDestino.getUserRoleArea().getUserRole().getRolename());
	    
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
		
	}
	

	public void onClick$btn_imprimir(Event e) throws JRException{
	
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", _areaPerfilActo.getActos().getDescricaoActos());
		MasterRep.imprimir("/reportParam/reportAreaPerfilActo.jrxml", listAPActo, mapaParam, win_regUserRoleAreaActo);
	}
	
	
	private void listar() {
		
//		List<UserRoleArea> listUserRoleAreas=_areaPerfilActoService.findAreaByUserRole(_userRole);
//		listAPActo = _areaPerfilActoService.findActoByUserRoleArea(listUserRoleAreas);
//		lbx_perfilActo.setModel(new ListModelList<AreaPerfilActo>(listAPActo));
		
		listURADestino = _userRoleAreaDestinoService.findPerfilByUserRole(_userRoleArea);
		lbx_perfilDestino.setModel(new ListModelList<UserRoleAreaDestino>(listURADestino));
	}
	
	private void peencherDestino() {
//		listURADestino = _userRoleAreaDestinoService.findPerfilByUserRole(_userRole);
//		cbx_perfil.setModel(new ListModelList<UserRoleAreaDestino>(listURADestino));
		
		List<UserRoleArea> listURoleAres = _userRoleAreaService.getAll();
		cbx_perfil.setModel(new ListModelList<UserRoleArea>(listURoleAres));
		
		//List<UserRole> listRoles = _userRoleService.getAll();
		//cbx_perfil.setModel(new ListModelList<UserRole>(listRoles));
	}
	
	
	
	private void limparCampos() {
		txb_codigo.setRawValue(null);
   		cbx_perfil.setRawValue(null);
        habilitarCampos();
   		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
		
	}
	

	private void desabilitarCampos() {
		txb_codigo.setDisabled(true);
		cbx_perfil.setDisabled(true);
	}
	
	private void habilitarCampos() {
		txb_codigo.setDisabled(false);
		cbx_perfil.setDisabled(false);
	}

	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_perfilActo,"before_center", 4000, true);
	}

}
