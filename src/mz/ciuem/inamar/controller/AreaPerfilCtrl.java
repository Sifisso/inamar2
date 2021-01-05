package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.InstrumentoLegal;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoEtapa;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.entity.Taxa;
import mz.ciuem.inamar.entity.TaxaPedido;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.InstrumentoLegalService;
import mz.ciuem.inamar.service.PedidoEtapaService;
import mz.ciuem.inamar.service.SubAreaService;
import mz.ciuem.inamar.service.TaxaPedidoService;
import mz.ciuem.inamar.service.TaxaService;
import mz.ciuem.inamar.service.UserRoleAreaService;
import mz.ciuem.inamar.service.UserRoleService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class AreaPerfilCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_regArePerfil;
	private Combobox cbx_taxas, cbx_subArea, cbx_area , cbx_perfil;
	private Listbox lbx_taxas;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	private Button btn_imprimir;
	
	//Inferior
	private Listbox lbx_taxasPedido, lbx_areaPerfil, lbx_perfis;
	
	private Execution ex = Executions.getCurrent();

	private UserRole _selectedUserRole;
	private Area _selectedArea;
	
	private Pedido _pedido;
	
	@WireVariable
	private TaxaService _taxaService;
	@WireVariable
	private SubAreaService _subAreaService;
	
	@WireVariable
	private TaxaPedidoService _taxaPedidoService;
	
	@WireVariable
	private UserRoleAreaService _userRoleAreaService;
	
	@WireVariable
	private UserRoleService _userRoleService;
	
	@WireVariable
	private AreaService _areaService;
	
	private List<UserRole> listUserRole, listUserRoleAdd = new ArrayList<UserRole>();
	private ListModelList<UserRole> listModelUserRoleAdd, listModelUserRole;
	private List<Area> listArea, listAreaAdd = new ArrayList<Area>();
	private ListModelList<Area> listModelAreaAdd, listModelArea;
	
	
	private List<UserRoleArea> _listUserRoleArea = new ArrayList<UserRoleArea>();
	

	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_taxaService =   (TaxaService) SpringUtil.getBean("taxaService");
		_subAreaService = (SubAreaService) SpringUtil.getBean("subAreaService");
		_taxaPedidoService =  (TaxaPedidoService) SpringUtil.getBean("taxaPedidoService");
		
		_userRoleAreaService =  (UserRoleAreaService) SpringUtil.getBean("userRoleAreaService");
		_userRoleService =  (UserRoleService) SpringUtil.getBean("userRoleService");
		_areaService =  (AreaService) SpringUtil.getBean("areaService");
		
		_pedido = (Pedido) ex.getArg().get("_pedido");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		//preencherTaxas(_pedido);
		listarPerfisDasAreas();
		preencherAreas();
		preencherPerfis();
		//listaLocalTaxasPedido(_pedido);
		//preencherSubAreas();
	}
	
	private void listarPerfisDasAreas() {
		_listUserRoleArea = _userRoleAreaService.getAll();
		lbx_areaPerfil.setModel(new ListModelList<UserRoleArea>(_listUserRoleArea));
	}
	
	private void preencherAreas() {
		List<Area> _listArea = _areaService.getAll();
		cbx_area.setModel(new ListModelList<Area>(_listArea));
	}
	
	
	private void preencherPerfis() {
		List<UserRole> _listUserRoles = _userRoleService.getAll();
		cbx_perfil.setModel(new ListModelList<UserRole>(_listUserRoles));
	}
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", _pedido.getDescricao());
   		MasterRep.imprimir("/reportParam/reportLocalPraticaPedido.jrxml", _listUserRoleArea, mapaParam, win_regArePerfil);
   	}
	
	public void onSelect$cbx_cbx_perfil() {
		_selectedUserRole = (UserRole) cbx_perfil.getSelectedItem().getValue();
		_selectedArea = (Area) cbx_area.getSelectedItem().getValue();	
    }

	public void onClick$btn_adicionar() {
		listUserRoleAdd.add((UserRole)cbx_perfil.getSelectedItem().getValue());
		listAreaAdd.add((Area)cbx_area.getSelectedItem().getValue());
		
		listModelUserRoleAdd = new ListModelList<UserRole>(listUserRoleAdd);
		listModelAreaAdd = new ListModelList<Area>(listAreaAdd);
		
		//lbx_perfis.setModel(listModelUserRoleAdd, listModelAreaAdd);
		lbx_perfis.setModel(listModelAreaAdd);
		lbx_perfis.setModel(listModelUserRoleAdd);
	
		//listUserRole.remove((UserRole)cbx_perfil.getSelectedItem().getValue());
		//cbx_perfil.removeChild(cbx_perfil.getSelectedItem());
		//cbx_area.removeChild(cbx_area.getSelectedItem());
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);		
		_selectedUserRole = null;
		_selectedArea = null;
		cbx_perfil.setRawValue(null);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(final ForwardEvent e){
		
		Messagebox.show("Deseja remover o perfil da área selecionada?", "Remoção do perfil",Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
		
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					UserRoleArea ip = (UserRoleArea) e.getData();
					
					if(_listUserRoleArea.contains(ip)){
						
						_userRoleAreaService.delete(ip);
						
						listarPerfisDasAreas();
					}
					showNotifications("Perfil Removido", "warning");
				}
			}
		});
	}	
	
	
public void onClick$btn_gravar() {
		
		UserRoleArea ura = new UserRoleArea();
		
		ura.setArea((Area)cbx_area.getSelectedItem().getValue());
		ura.setUserRole((UserRole)cbx_perfil.getSelectedItem().getValue());
		
		boolean existe = false;
		
		for(UserRoleArea urArea: _listUserRoleArea) {
			if((urArea.getArea().getId()==ura.getArea().getId()) && (urArea.getUserRole().getId()==ura.getUserRole().getId())) {
				existe=true;
			}
		}
		
		if(existe==false) {
			_userRoleAreaService.create(ura);
			
			_selectedArea = null;
			_selectedUserRole = null;
			cbx_area.setRawValue(null);
			cbx_perfil.setRawValue(null);
			
			limparCampos();
			listarPerfisDasAreas();
			showNotifications("Perfil Adicionado com Sucesso", "info");
		}else {
			showNotifications("Configuração existente", "error");
		}
		
			
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_taxas, "before_center",
				4000, true);
	}
	
	private void limparCampos() {
		cbx_perfil.setRawValue(null);
		cbx_area.setRawValue(null);
   }

}



