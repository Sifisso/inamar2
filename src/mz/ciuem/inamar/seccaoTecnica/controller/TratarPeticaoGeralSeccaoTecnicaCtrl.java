package mz.ciuem.inamar.seccaoTecnica.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoEtapa;
import mz.ciuem.inamar.entity.PeticaoPedidoEtapaInstrumentoLegal;
import mz.ciuem.inamar.entity.PeticaoTarefasNaEtapa;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.service.PeticaoEtapaService;
import mz.ciuem.inamar.service.PeticaoPedidoEtapaInstrumentoLegalService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.PeticaoTarefasNaEtapaService;
import mz.ciuem.inamar.service.UserService;
import net.sf.jasperreports.engine.JRException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class TratarPeticaoGeralSeccaoTecnicaCtrl extends GenericForwardComposer{
	
	private Window win_tratarPeticaoST;
	private Div div_content_out;
	private Include inc_main;

	private Label lbl_nome, lbl_pedido, lbl_dataentrada,lbl_datasaida;
	
	private Listbox lbx_peticaoTarefasEtapa,lbx_eventos, lbx_insLegal;
	
	private Textbox tbx_peticaoEtapa;
	
	PeticaoEtapa _peticaoEtapa;
	
	private Button btn_gravar, btn_actualizar, btn_terminar;
	private Button btn_validar, btn_recusar, btn_requerimento;
	
	@WireVariable
	private PeticaoService _peticaoService;
	@WireVariable
	private PeticaoEtapaService _peticaoEtapaService;
	@WireVariable
	private PeticaoTarefasNaEtapaService _peticaoTarefasNaEtapaService;
	@WireVariable
	private PeticaoPedidoEtapaInstrumentoLegalService _peticaoPedidoEtapaInstrumentoLegalService;
	
	@WireVariable
    private UserService _userService;	
	protected User loggeduser;
	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	private Peticao _peticao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		

		_userService = (UserService) SpringUtil.getBean("userService");
		_peticaoService =(PeticaoService) SpringUtil.getBean("peticaoService");
		_peticao = (Peticao) Executions.getCurrent().getArg().get("peticao");
		_peticaoEtapaService = (PeticaoEtapaService) SpringUtil.getBean("peticaoEtapaService");
		_peticaoTarefasNaEtapaService = (PeticaoTarefasNaEtapaService) SpringUtil.getBean("peticaoTarefasNaEtapaService");
		_peticaoPedidoEtapaInstrumentoLegalService = (PeticaoPedidoEtapaInstrumentoLegalService) SpringUtil.getBean("peticaoPedidoEtapaInstrumentoLegalService");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos();
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
			//Em caso de ser a primeira tramitacao da seccao tecnica
			if(_peticao.isSeccaoTecnica() && !_peticao.isSeccaoTecnica2()){
				enviarParaAssinaturaAdministrador();
			}else
				if(_peticao.isSeccaoTecnica() && _peticao.isSeccaoTecnica2()){
				enviarParaSecretaria();
			}
		}
		Clients.showNotification("Tarefas Registadas com Sucesso", "info", lbl_dataentrada, "before_center", 4000, true);
		
	}

	private void enviarParaSecretaria() {
		_peticao.setTratada(true);
		_peticao.setTerminada(true);
		_peticao.setLocalizacao("Secretaria(Pronto)");
		_peticao.setSeccaoTecnica(false);
		_peticao.setChefeSecretaria(true);
		_peticao.setSecretaria(true);
		_peticao.setAdmMaritima(false);
		_peticaoService.saveOrUpdate(_peticao);
		
	}

	private void enviarParaAssinaturaAdministrador() {
		 //Por analizar a parte dos estados da peticao
		if(_peticao!=null){
			//_peticao.setSeccaoTecnica(false);
			_peticao.setSeccaoTecnica2(true);
			_peticao.setSeccaoTecnicaParecer(false);
			
			_peticao.setAdmMaritima(true);
			_peticao.setAdmMaritima2(true);
			
			
			_peticao.setLocalizacao("Administrador Maritimo 2");
            _peticaoService.saveOrUpdate(_peticao);			
		}
	}

	public void onClick$btn_terminar(){
		if(_peticao!=null){
			saveOrUpdate(_peticao);
			Clients.showNotification("Pedido Tratado com Sucesso", "info", lbl_dataentrada, "before_center", 4000, true);
			win_tratarPeticaoST.detach();
		}
	}

	private void saveOrUpdate(Peticao pet) {
			saveOrUpdateP( pet);
	}

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
			lbl_datasaida.setValue(""+_peticao.getCreated()+10);
			preencherInstrumentoLegal(_peticao);
			if(!_peticao.isSeccaoTecnicaParecer()){
			}
			
			
		}
	}
	

	private void preencherInstrumentoLegal(Peticao _peticao2) {
		List<PeticaoPedidoEtapaInstrumentoLegal> list = _peticaoPedidoEtapaInstrumentoLegalService.findByPeticao(_peticao2);
        lbx_insLegal.setModel(new ListModelList<PeticaoPedidoEtapaInstrumentoLegal>(list));		
	}
	
	public void onClickClose(ForwardEvent e){
		win_tratarPeticaoST.detach();
	}
	
	public void onClickVerTramitacao(ForwardEvent e) throws JRException{
		Peticao p = _peticao;
		_peticaoService.onClickVerTramitacao(p, win_tratarPeticaoST);
	}
	
	public void onClickDarParecer(ForwardEvent e){
		Peticao _pet = (Peticao) _peticao;
		Map<String, Object> mapContaReceber = new HashMap<String, Object>();
		mapContaReceber.put("peticao", _pet);
		Executions.createComponents("/views/SeccaoTecnica/tratar_peticao.zul", win_tratarPeticaoST, mapContaReceber);
		//onClickClose(e);
	}

	public void onClickVerFactura(ForwardEvent e) throws JRException{
    	Peticao p = (Peticao) _peticao;
    	_peticaoService.onClickVerFactura(p, win_tratarPeticaoST);
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
