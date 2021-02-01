package mz.ciuem.inamar.expediente.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.dao.imlp.UserRoleAreaDaoImpl;
import mz.ciuem.inamar.entity.Actos;
import mz.ciuem.inamar.entity.ActosAdmin;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.AreaPerfilActo;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoDestino;
import mz.ciuem.inamar.entity.PeticaoEtapa;
import mz.ciuem.inamar.entity.PeticaoPedidoEtapaInstrumentoLegal;
import mz.ciuem.inamar.entity.PeticaoTarefasNaEtapa;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.UserRoleArea;
import mz.ciuem.inamar.entity.UserRoleAreaDestino;
import mz.ciuem.inamar.service.ActosAdminService;
import mz.ciuem.inamar.service.ActosService;
import mz.ciuem.inamar.service.AreaPerfilActoService;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.PeticaoDestinoService;
import mz.ciuem.inamar.service.PeticaoEtapaService;
import mz.ciuem.inamar.service.PeticaoPedidoEtapaInstrumentoLegalService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.PeticaoTarefasNaEtapaService;
import mz.ciuem.inamar.service.RoleActosService;
import mz.ciuem.inamar.service.UserRoleAreaDestinoService;
import mz.ciuem.inamar.service.UserRoleAreaService;
import mz.ciuem.inamar.service.UserRoleService;
import mz.ciuem.inamar.service.UserService;
import net.sf.jasperreports.engine.JRException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class TratarPeticaoGeralCtrl extends GenericForwardComposer{
	
	private Window win_tratarPeticao;
	private Div div_content_out;
	private Include inc_main;

	private Label lbl_nome, lbl_pedido, lbl_dataentrada,lbl_datasaida;
	
	private Listbox lbx_peticaoTarefasEtapa,lbx_eventos, lbx_insLegal,lbx_peticaoDestino, lbx_actosAdmin;
	
	private Textbox tbx_peticaoEtapa;
	
	PeticaoEtapa _peticaoEtapa;
	
	private Button btn_gravar, btn_actualizar, btn_terminar, btn_adicionarActos, btn_adicionarRoles;
	private Button btn_validar, btn_recusar, btn_requerimento;
	private Combobox cbx_roles, cbx_Actos;
	
	@WireVariable
	private PeticaoService _peticaoService;
	@WireVariable
	private AreaPerfilActo _areaPerfilActo;
	
	@WireVariable
	private AreaPerfilActoService _areaPerfilActoService;
	@WireVariable
	private UserRoleAreaDestinoService _userRoleAreaDestinoService;
	@WireVariable
	private ActosService _actosService;
	@WireVariable
	private AreaService _areaService;
	@WireVariable
	private PeticaoEtapaService _peticaoEtapaService;
	@WireVariable
	private UserRoleAreaService _userRoleAreaService;
	@WireVariable
	private UserRoleService _userRoleService;
	@WireVariable
	private RoleActosService roleActosService;
	@WireVariable
	private PeticaoDestinoService _peticaoDestinoService;
	@WireVariable
	private ActosAdminService _actosAdminService;
	@WireVariable
	private PeticaoTarefasNaEtapaService _peticaoTarefasNaEtapaService;
	@WireVariable
	private PeticaoPedidoEtapaInstrumentoLegalService _peticaoPedidoEtapaInstrumentoLegalService;
	
	private List<UserRoleArea> list_Role= new ArrayList();
	@WireVariable
    private UserService _userService;	
	protected User loggeduser;
	private List<PeticaoDestino> _listPeticaoDestino = new ArrayList<PeticaoDestino>();
	private List<ActosAdmin> listActosAdmin = new ArrayList<ActosAdmin>(); 
	private List<PeticaoDestino> listPeticaoDestino = new ArrayList<PeticaoDestino>(); 
	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	private UserRole _selectedUserRole;
	private Peticao _peticao;
	private ActosAdmin _actosAdmin;
	private UserRoleArea userRoleArea;
	private UserRoleAreaDestino userRoleAreaDestino;
	private Area _area;
	private UserRoleArea _userRoleArea;
	private UserRole userRole;
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_userService = (UserService) SpringUtil.getBean("userService");
		_areaService = (AreaService) SpringUtil.getBean("areaService");
		_peticaoService =(PeticaoService) SpringUtil.getBean("peticaoService");
		_actosAdminService = (ActosAdminService) SpringUtil.getBean("actosAdminService");
		_areaPerfilActoService = (AreaPerfilActoService) SpringUtil.getBean("areaPerfilActoService");
		_userRoleAreaService =(UserRoleAreaService) SpringUtil.getBean("userRoleAreaService");
		_userRoleAreaDestinoService =(UserRoleAreaDestinoService) SpringUtil.getBean("userRoleAreaDestinoService");
		_userRoleService =(UserRoleService) SpringUtil.getBean("userRoleService");
		_userRoleArea = (UserRoleArea) Executions.getCurrent().getArg().get("_userRoleArea");
		_areaPerfilActo = (AreaPerfilActo) Executions.getCurrent().getArg().get("_areaPerfilActo");
		
		_peticaoDestinoService =(PeticaoDestinoService) SpringUtil.getBean("peticaoDestinoService");
		
		_actosAdmin = (ActosAdmin) Executions.getCurrent().getArg().get("actosAdmin");
		_peticao = (Peticao) Executions.getCurrent().getArg().get("peticao");
		_area = (Area) Executions.getCurrent().getArg().get("area");
		userRole=(UserRole) Executions.getCurrent().getArg().get("userRole");
		//userRole1=(UserRole) Executions.getCurrent().getArg().get("userRole");
		_peticaoEtapaService = (PeticaoEtapaService) SpringUtil.getBean("peticaoEtapaService");
		_peticaoTarefasNaEtapaService = (PeticaoTarefasNaEtapaService) SpringUtil.getBean("peticaoTarefasNaEtapaService");
		_peticaoPedidoEtapaInstrumentoLegalService = (PeticaoPedidoEtapaInstrumentoLegalService) SpringUtil.getBean("peticaoPedidoEtapaInstrumentoLegalService");
		
		roleActosService=(RoleActosService) SpringUtil.getBean("roleActosService");
		
		_actosService=(ActosService) SpringUtil.getBean("actosService");
		
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos();
		listarPerfil();
		listarActo();
		listarPerfisLBX();
		listarActosAdmin();
	
	}
	private void listarPerfil(){
		//List<PeticaoDestino> listDestinos = _peticaoDestinoService.buscarPeticoesPorArea(userRole);
		//cbx_roles.setModel(new ListModelList<PeticaoDestino>(listDestinos));
		
		List<UserRoleAreaDestino> listUserRoleAreaDestinos = _userRoleAreaDestinoService.getAll();
		cbx_roles.setModel(new ListModelList<UserRoleAreaDestino>(listUserRoleAreaDestinos));
	}
	
	private void listarActosAdmin(){
		listActosAdmin = _actosAdminService.getAll();
		lbx_actosAdmin.setModel(new ListModelList<ActosAdmin>(listActosAdmin));
	}
	
	private void listarPerfisLBX(){
		_listPeticaoDestino = _peticaoDestinoService.getAll();
		lbx_peticaoDestino.setModel(new ListModelList<PeticaoDestino>(_listPeticaoDestino));
		//btn_gravar.setVisible(true);
	}
	

	private void limparCampos() {
		cbx_roles.setRawValue(null);
		//cbx_area.setRawValue(null);
   }

	public void onClick$btn_adicionarRoles(Event e) throws InterruptedException{
	
	PeticaoDestino pd = new PeticaoDestino();
	pd.setPeticao(_peticao);
	pd.setUserRoleAreaDestino((UserRoleAreaDestino)cbx_roles.getSelectedItem().getValue());
	
		boolean existe = false;
		
		for(PeticaoDestino pdRole: _listPeticaoDestino) {
			if((pdRole.getUserRoleAreaDestino().getUserRoleArea().getUserRole().getId()==pd.getUserRoleAreaDestino().getUserRoleArea().getUserRole().getId())) {
				existe=true;
			}
		}
		
		if(existe==false) {
			_peticaoDestinoService.create(pd);
			_selectedUserRole = null;
			cbx_roles.setRawValue(null);
			
			limparCampos();
			listarPerfisLBX();
			showNotifications("Perfil Adicionado com Sucesso", "info");
		}else {
			showNotifications("Configuração existente", "error");
		}
		
		
	}
	
	public void onClick$btn_adicionarActos(Event e) throws InterruptedException{
		
		ActosAdmin ad = new ActosAdmin();
		ad.setPeticao(_peticao);
		ad.setAreaPerfilActo((AreaPerfilActo)cbx_Actos.getSelectedItem().getValue());
		
		
		
		
			boolean existe = false;
			
			for(ActosAdmin actosAd: listActosAdmin) {
				if((actosAd.getAreaPerfilActo().getActos().getId()==ad.getAreaPerfilActo().getActos().getId())) {
					existe=true;
				}
			}
			
			if(existe==false) {
				_actosAdminService.create(ad);
				_selectedUserRole = null;
				cbx_Actos.setRawValue(null);
				
				limparCampos();
				listarActosAdmin();
				showNotifications("Acto Adicionado com Sucesso", "info");
			}else {
				showNotifications("Acto existente", "error");
			}
			
			
		}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(final ForwardEvent e){
		
		Messagebox.show("Deseja remover o acto da Área selecionada?", "Remoção do acto",Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
		
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					
					ActosAdmin aa = (ActosAdmin) e.getData();
					
					if(listActosAdmin.contains(aa)){
						
						_actosAdminService.delete(aa);
						
						listarActosAdmin();
					}
					showNotifications("Acto Removido", "warning");
				}
			}
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemoverDestino(final ForwardEvent e){
		
		Messagebox.show("Deseja remover o Destino da Área selecionada?", "Remoção do destino",Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
		
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					
					PeticaoDestino pd = (PeticaoDestino) e.getData();
					
					if(_listPeticaoDestino.contains(pd)){
						
						_peticaoDestinoService.delete(pd);
						
						listarPerfisLBX();
					}
					showNotifications("Destino Removido", "warning");
				}
			}
		});
	}
	
	
	
	private void listarActo(){
		List<AreaPerfilActo> listActosAdmin = _areaPerfilActoService.getAll();
		cbx_Actos.setModel(new ListModelList<AreaPerfilActo>(listActosAdmin));
		
//		List<ActosAdmin> listActosAdmin = _actosAdminService.findActosByArea(_area);
//		cbx_Actos.setModel(new ListModelList<ActosAdmin>(listActosAdmin));
	}
	
	public void onClick$btn_gravar(){
		if(_peticao!=null){
			executarTarefas(lbx_peticaoTarefasEtapa.getItems());
			preencherCampos();
		}
	}
	
	private void executarTarefas(List<Listitem> items) {
		boolean todas=true;
		for (Listitem listitem : items) {
			
			Listcell lcell = (Listcell) listitem.getChildren().get(1);
			Checkbox cbx = (Checkbox) lcell.getFirstChild();
			if(cbx.isChecked()){
				PeticaoTarefasNaEtapa pte = (PeticaoTarefasNaEtapa)listitem.getValue();
				pte.setData(new Date());
				pte.setRealizada(true);
				
				loggeduser = _userService.getUser(authentication.getName());
				pte.setUser(loggeduser);
				_peticaoTarefasNaEtapaService.saveOrUpdate(pte);
			}else{
				todas=false;
			}
		
		}
		
		if(todas){
			_peticaoEtapa.setRealizada(true);
			_peticaoEtapaService.saveOrUpdate(_peticaoEtapa);
		}
		
		
	}

	public void onClick$btn_terminar(){
		if(_peticao!=null){
			saveOrUpdate(_peticao);
			Clients.showNotification("Pedido Tratado com Sucesso", "info", lbl_dataentrada, "before_center", 4000, true);
			win_tratarPeticao.detach();
		}
	}

	private void saveOrUpdate(Peticao pet) {
			saveOrUpdateP( pet);
	}

	//pegar as contas
	/*private void listarConta() {
       cbx_conta.setModel(new ListModelList<Conta>(_contaService.getAll()));	
	}*/

	private void saveOrUpdateP(Peticao _petic) {
		/*_petic.setParecerChefeSecretaria(txb_parecer.getValue());
		_petic.setObservacaoChefeSecretaria(txb_observacoes.getValue());
		_petic.setLocalizacao("Admin. Maritimo2");
		_petic.setTratada(true);
		_petic.setChefeSecretaria(false);
		_petic.setAdmMaritima2(true);
		_peticaoService.saveOrUpdate(_petic);*/
	}

	private void preencherCampos() {
		if(_peticao!=null){
			lbl_nome.setValue(_peticao.getUtente());
			lbl_pedido.setValue(_peticao.getDescricao());
			lbl_dataentrada.setValue(""+_peticao.getCreated());
//			lbl_datasaida.setValue(""+_peticao.getCreated()+10);
			preencherInstrumentoLegal(_peticao);
			preencherPermissoes();
			
			if(!_peticao.isChefeSecretariaParecer()){
				//preencherPeticaoEtapa(_peticao);
			}
		
		}
	}
	
	

	private void preencherPermissoes() {
		if(_peticao.isValidado() || _peticao.isRecusado()){
			btn_validar.setVisible(false);
			btn_recusar.setVisible(false);
		}
		
	}

	private void preencherInstrumentoLegal(Peticao _peticao2) {
		List<PeticaoPedidoEtapaInstrumentoLegal> list = _peticaoPedidoEtapaInstrumentoLegalService.findByPeticao(_peticao2);
      //  lbx_insLegal.setModel(new ListModelList<PeticaoPedidoEtapaInstrumentoLegal>(list));		
	}

	public void onClickClose(ForwardEvent e){
		win_tratarPeticao.detach();
	}
	
	public void onClickVerTramitacao(ForwardEvent e) throws JRException{
		Peticao p = _peticao;
		_peticaoService.onClickVerTramitacao(p, win_tratarPeticao);
	}
	public void onClickVerRecibo(ForwardEvent e) throws JRException{
    	Peticao p = (Peticao) _peticao;
    	_peticaoService.onClickVerRecibo(p, win_tratarPeticao);
	}
	
	public void onClickDarParecer(ForwardEvent e){
		
		Peticao _pet = (Peticao) _peticao;
		Map<String, Object> mapContaReceber = new HashMap<String, Object>();
		mapContaReceber.put("peticao", _pet);
		Executions.createComponents("/views/ChefeSecretaria/tratar_peticao.zul", win_tratarPeticao, mapContaReceber);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void onClickEncaminhar(final ForwardEvent e) {
		Messagebox.show("Deseja confirmar o levantamento?", "Levantamento",Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					Peticao _pet = (Peticao) _peticao;
					_pet.setRealizada(true);
					_pet.setLocalizacao("Finalizado");
					_peticaoService.saveOrUpdate(_pet);
					Clients.showNotification("Confirmado", "info", lbl_dataentrada, "before_center", 4000, true);
				}
			}
		});
		win_tratarPeticao.detach();
		
	}
	
	@SuppressWarnings({ "unchecked" })
	public void onClickValidar(final ForwardEvent e) {
		
		
		Peticao _pet = (Peticao) _peticao;
		Map<String, Object> mapContaReceber = new HashMap<String, Object>();
		mapContaReceber.put("peticao", _pet);
		Executions.createComponents("/views/ChefeSecretaria/telinha.zul", win_tratarPeticao, mapContaReceber);
		
		
	}
	
	private boolean executouTodasTarefas() {
		boolean todas=true;
		List<Listitem> list = lbx_peticaoTarefasEtapa.getItems();
		for (Listitem listitem : list) {
			if(!listitem.isDisabled())todas=false;
		}
		return todas;
	}

	@SuppressWarnings("unchecked")
	public void onClickRecusar(final ForwardEvent e) {
	
		Peticao _pet = (Peticao) _peticao;
		Map<String, Object> mapContaReceber = new HashMap<String, Object>();
		mapContaReceber.put("peticao", _pet);
		Executions.createComponents("/views/ChefeSecretaria/recusar_pedido.zul", win_tratarPeticao, mapContaReceber);
		
		
		/*Messagebox.show("Deseja  Recusar esta Peticao?", "Recusar Peticao",Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					Peticao p = _peticao;
					p.setSecretaria2(true);
					p.setRecusado(true);
					p.setLocalizacao("Secretaria2");
					_peticaoService.update(p);
					//Abrir uma nova aba para indicar os motivos ou observacao
					showNotifications("Peticao Recusada com sucesso.", "error");
					onClickClose(e);
				}
			}
		});*/
		
	}
	
	
	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbl_dataentrada,"before_center", 4000, true);
   	}

	public Peticao get_peticao() {
		return _peticao;
	}

	public void set_peticao(Peticao _peticao) {
		this._peticao = _peticao;
	}

	

}
