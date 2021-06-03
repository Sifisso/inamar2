package mz.ciuem.inamar.licenca.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.ActividadeLicenca;
import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.Contagem;
import mz.ciuem.inamar.entity.Departamento;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Exame;
import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoRequisito;
import mz.ciuem.inamar.entity.Permission;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoCategoriaMaritimo;
import mz.ciuem.inamar.entity.PeticaoLicenca;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.ActividadeLicencaService;
import mz.ciuem.inamar.service.CategoriaMaritimoService;
import mz.ciuem.inamar.service.DepartamentoService;
import mz.ciuem.inamar.service.EtapaFluxoService;
import mz.ciuem.inamar.service.ExameService;
import mz.ciuem.inamar.service.LocalPraticaService;
import mz.ciuem.inamar.service.PeticaoCategoriaMaritimoService;
import mz.ciuem.inamar.service.PeticaoLicencaService;
import mz.ciuem.inamar.service.PeticaoMaritimoService;
import mz.ciuem.inamar.service.PeticaoMaritimoTaxaPedidoService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.UserService;
import mz.ciuem.inamar.service.UtenteService;
import mz.ciuem.inamar.vm.MainVM;
import net.sf.jasperreports.engine.JRException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class EmissaoLicencaTransporteComercialCtrl extends GenericForwardComposer{
	
	//private Window win_emissaoLincAng;
	private Div div_content_out, div_terminar, div_utente, div_secretario, myModal, div_dados;
	private Include inc_main;
	private Combobox cbx_actividadeLicenca, cbx_localPratica;
	private Button btn_proximo, btn_imprimir, btn_terminar, btn_voltarUtente, btn_voltar, btn_validar, btn_recusar, btn_prevalidar;
	private Textbox tbx_user, tbx_pass, tbx_nomeEmpresa, tbx_endereco, tbx_contacto, tbx_email, tbx_nuit, tbx_embarcacoesMeiosDispoivies, tbx_caracteristicas;
	private Listbox lbx_actividades, lbx_locais;
	private Label lbl_nome, lbl_contacto, lbl_provincia, lbl_distrito, lbl_tipoDoc, lbl_nrDoc, lbl_bairro, lbl_localEmissao;
	private Radio rdx_proprios, rdx_afrotados;
	
	
	@WireVariable
	private ActividadeLicencaService _actividadeLicencaService;
	@WireVariable
	private LocalPraticaService _localPraticaService;
	@WireVariable
	private PeticaoLicencaService _peticaoLicencaService;
	@WireVariable
	private UserService _userService;
	@WireVariable
	private UtenteService _utenteService;
	@WireVariable
	private PeticaoMaritimoTaxaPedidoService _peticaoMaritimoTaxaPedidoService;
	@WireVariable
	private EtapaFluxoService _etapaFluxoService;
	@WireVariable
	private PeticaoService _peticaoService;
	
	private PeticaoLicenca _peticaoLicenca;
	
	private ActividadeLicenca _actividadeLicenca;
	
	private LocalPratica _localPratica;
	
	private List<Contagem> listContagem = new ArrayList<Contagem>();
	
	protected User loggeduser;
	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	private Execution ex = Executions.getCurrent();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		
		_actividadeLicencaService = (ActividadeLicencaService) SpringUtil.getBean("actividadeLicencaService");
		_localPraticaService = (LocalPraticaService) SpringUtil.getBean("localPraticaService");
		_peticaoLicencaService = (PeticaoLicencaService) SpringUtil.getBean("peticaoLicencaService");
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_peticaoMaritimoTaxaPedidoService = (PeticaoMaritimoTaxaPedidoService) SpringUtil.getBean("peticaoMaritimoTaxaPedidoService");
		_peticaoService = (PeticaoService) SpringUtil.getBean("peticaoService");
		//Teste
		_etapaFluxoService = (EtapaFluxoService) SpringUtil.getBean("etapaFluxoService");
		
		_peticaoLicenca = (PeticaoLicenca) Executions.getCurrent().getSession().getAttribute("ss_peticaoLicenca");
		//_peticaoMaritimo = _peticaoMaritimoService.find((long) 6);
		_actividadeLicenca = (ActividadeLicenca) ex.getArg().get("_actividadeLicenca");
		_localPratica = (LocalPratica) ex.getArg().get("_localPratica");
		
		loggeduser = (User) Executions.getCurrent().getSession().getAttribute("ss_utilizador");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos();
	}

	
	private void preencherCampos() {
		if(_peticaoLicenca!=null && _peticaoLicenca.getPeticao()!=null){
			tbx_contacto.setValue(_peticaoLicenca.getContacto());
			tbx_email.setValue(_peticaoLicenca.getEmail());
			tbx_endereco.setValue(_peticaoLicenca.getEndereco());
			tbx_nomeEmpresa.setValue(_peticaoLicenca.getNomeEmpresa());
			tbx_nuit.setValue(_peticaoLicenca.getNuit());
			tbx_embarcacoesMeiosDispoivies.setValue(_peticaoLicenca.getEmbarcacaoesMeiosDisponiveis());
			tbx_caracteristicas.setValue(_peticaoLicenca.getCaracteristicas());
			rdx_proprios.setChecked(_peticaoLicenca.isEhProprios());
			
			
		}
	}
	
    private void ocultarCampos() {
		if(loggeduser!=null){
			Set<Permission> list = loggeduser.getPermissions();
			for (Permission permission : list) {
				if(permission.getPermissionname().equals("SECRETARIA")){
					btn_prevalidar.setVisible(true);
					btn_validar.setVisible(false);
				}
			}
		}
		
	}
    
    public void onClick$btn_proximo(){
		if(_peticaoLicenca!=null){
			gravar();	
		}
	}
    
    public void onClick$btn_voltar(){
		if(_peticaoLicenca!=null){
			gravar();	
		}else{
			alert("Ocorreu um erro "+null);
		}
	}
    
	public void onClick$btn_verFactura() throws JRException{
		verFactura();
	}
	
	//REfazer
	public void onClick$btn_terminar() throws JRException{
		verFactura();
	}
	
  	public void onClick$btn_proximo(Event e) throws JRException{
  		if(_peticaoLicenca!=null){
			gravar();
  		}
   	}
	
	private void gravar() {
		_peticaoLicenca.setNomeEmpresa(tbx_nomeEmpresa.getValue());
		_peticaoLicenca.setEndereco(tbx_endereco.getValue());
		_peticaoLicenca.setContacto(tbx_contacto.getValue());
		_peticaoLicenca.setEmail(tbx_email.getValue());
		_peticaoLicenca.setNuit(tbx_nuit.getValue());
		_peticaoLicenca.setCaracteristicas(tbx_caracteristicas.getValue());
		_peticaoLicenca.setEmbarcacaoesMeiosDisponiveis(tbx_embarcacoesMeiosDispoivies.getValue());
		_peticaoLicenca.setEhProprios(rdx_proprios.isChecked() ? true : false);
		_peticaoLicencaService.saveOrUpdate(_peticaoLicenca);
       //visibilidades();
	}
	
	
	
	
	   private void visibilidades() {
		//   div_terminar.setVisible(true);
		   div_dados.setVisible(false);
		   //btn_proximo.setVisible(false);
		   myModal.setVisible(true);
		   
		   //Utente
		   if(loggeduser.getUtente()!=null){
			   div_secretario.setVisible(false);
			   div_utente.setVisible(true);
			   btn_voltarUtente.setVisible(true);
			   btn_voltar.setVisible(false);
			   btn_validar.setVisible(false);
			   btn_recusar.setVisible(false);
			   tbx_user.setValue(loggeduser.getUsername());
			   tbx_pass.setValue("**************");
		   }
		   //Secretario
		   else{
			   div_secretario.setVisible(true);
			   div_utente.setVisible(false);
			   btn_voltarUtente.setVisible(false);
			   btn_voltar.setVisible(true);
			   btn_validar.setVisible(true);
			   btn_recusar.setVisible(true);
		   }
		   ocultarCampos();
	}

	private void verFactura() throws JRException {
		   Map<String, Object> mapaParam = new HashMap<String, Object>();	
	   		final Execution ex = Executions.getCurrent();
	   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
	        mapaParam.put("imagemLogo", inputV);
	        Utente u = _utenteService.buscarUtenteByUser(_peticaoLicenca.getUser());
	        //Parametros
	        //Fazer query
	        mapaParam.put("pedido", ""+_peticaoLicenca.getPedido().getDescricao());
	        mapaParam.put("nome", ""+u.getNome()+" "+u.getApelido());
	        mapaParam.put("nomePai", ""+u.getNomePai());
	        mapaParam.put("nomeMae", ""+u.getNomeMae());
	        
	        int idade = new Date().getYear()-u.getDataNascimento().getYear();
	        mapaParam.put("idade", ""+idade);
	        mapaParam.put("tipoDocumento", ""+u.getTipoDocumento());
	        mapaParam.put("nrDocumento", ""+u.getNumeroDocumento());
	        //Nao existe
	        mapaParam.put("dataEmissaoDocumento", "22/10/2014");
	        mapaParam.put("bairro", ""+u.getBairro());
	        mapaParam.put("quarteirao", ""+u.getQuarteirao());
	        mapaParam.put("nrCasa", ""+u.getNrCasa());
	        mapaParam.put("nrTelefone", ""+u.getCelular());
	        //Nao existe
	        mapaParam.put("tipoUtente", "Empresa");
	        //Nao existe
	        Peticao p =_peticaoLicenca.getPeticao();
	        mapaParam.put("paticionarioNr", p.getNrExpediente());
	        //Nso existe
	        mapaParam.put("codigoArea", "02");
	        //Nao Existe
	        mapaParam.put("codigoSubArea", "04");
	        //Nao Existe
	        mapaParam.put("nrFactura", p.getNrFactura());
	        mapaParam.put("referencia", p.getReferencia());
	        mapaParam.put("subToal",p.getValor());
	        double iva = p.getValor()+0.17;
	        double valorTotal = p.getValor()+iva;
	        mapaParam.put("valor_pagar",valorTotal);
	        mapaParam.put("iva_valor", iva);
	        mapaParam.put("entidade",""+ p.getEntidade());
	        
	        mapaParam.put("nrExpediente", p.getNrExpediente());
	        mapaParam.put("hora", ""+_peticaoLicenca.getCreated().getHours());
	        
	        
	       //Factura Utente
	      // List<PeticaoMaritimoTaxaPedido> _list = _peticaoMaritimoTaxaPedidoService.findByPeticaoMaritimo(_peticaoLicenca);
	   	   //MasterRep.imprimir("/reportParam/facturaPedidoUtente.jrxml", _list, mapaParam, win_emissaoLincAng);
	   	   
	   	// Factura Secretario 
	        /*Pedido pe = _peticaoMaritimo.getPedido();
	       List<EtapaFluxo> estF = _etapaFluxoService.findByFluxo(pe.getFluxo());
	   	   MasterRep.imprimir("/reportParam/facturaPedido.jrxml", estF, mapaParam, win_emissaoCedula);*/
		
	}
	
	public void onClick$btn_prevalidar(){
		if(_peticaoLicenca!=null && _peticaoLicenca.getPeticao()!=null){
			Peticao pet= _peticaoLicenca.getPeticao();
			pet.setPreValidado(true);
			pet.setLocalizacao("Chefe da Secretaria");
			_peticaoService.update(pet);
			btn_validar.setVisible(false);
			btn_prevalidar.setVisible(false);
			btn_recusar.setVisible(true);
			showNotifications("Peticao pre-validada com sucesso.", "info");
		}
	}
	
	public void onClick$btn_validar(){
		if(_peticaoLicenca!=null && _peticaoLicenca.getPeticao()!=null){
			Peticao pet= _peticaoLicenca.getPeticao();
			pet.setValidado(true);
			pet.setAdmMaritima(true);
			pet.setRecusado(false);
			_peticaoService.update(pet);
			btn_validar.setVisible(false);
			btn_recusar.setVisible(true);
			showNotifications("Peticao validada com sucesso.", "info");
		}
	}
	
	public void onClick$btn_recusar(){
		if(_peticaoLicenca!=null && _peticaoLicenca.getPeticao()!=null){
			Peticao pet= _peticaoLicenca.getPeticao();
			pet.setValidado(false);
			pet.setRecusado(true);
			_peticaoService.update(pet);
			btn_validar.setVisible(true);
			btn_recusar.setVisible(false);
			showNotifications("Peticao Recusada com sucesso.", "error");
			ocultarCampos();
		}
	}
	
	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, btn_terminar,"before_center", 4000, true);
   	}
	

}
