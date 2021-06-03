package mz.ciuem.inamar.utente.controller;

import java.util.List;

import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Maritimo;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.DistritoService;
import mz.ciuem.inamar.service.MaritimoService;
import mz.ciuem.inamar.service.ProvinciaService;
import mz.ciuem.inamar.service.UtenteService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class MaritimoCtrlUm extends GenericForwardComposer{
	
	private Button btn_proximo;
	private Window win_regDados;
	private Include inc_main;
	private Div div_content_out;
	
	private Textbox tbx_nome;
	private Textbox tbx_apelido;
	private Textbox tbx_nuit;
	private Textbox tbx_rua;
	private Textbox tbx_quarteirao;
	private Textbox tbx_nrCasa;
	private Textbox tbx_celular;
	private Textbox tbx_celular2;
	private Textbox tbx_email;
	private Datebox dtb_dataNascimento;
	private Combobox cbx_provincia;
	private Combobox cbx_distrito;
	private Textbox tbx_bairro;
	
	@WireVariable
	private ProvinciaService _provinciaService;
	@WireVariable
	private DistritoService _distritoService;
	@WireVariable
	private MaritimoService _maritimoService;
	private Session _session;
	
	Maritimo _maritimo;
	private User _loggedUser;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");
		_distritoService = (DistritoService) SpringUtil.getBean("distritoService");
		_maritimoService = (MaritimoService) SpringUtil.getBean("maritimoService");
		_maritimo = (Maritimo) Executions.getCurrent().getSession().getAttribute("ss_maritimo");
		//verificarLogado();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		preencherProvincias();
		preencherCampos(_maritimo);
	}
	
	private void verificarLogado() {
		if(_maritimo==null){
			_loggedUser = (User) _session.getAttribute("ss_utilizador");
			_maritimo = _loggedUser.getMaritimo();
		}
	}

	public void onSelect$cbx_provincia(){
		List<Distrito> listD = _distritoService.buscarDistritosDeUmaProvincia((Provincia) cbx_provincia.getSelectedItem().getValue());
		cbx_distrito.setModel(new ListModelList<Distrito>(listD));
	}
	
	private void preencherProvincias() {
		List<Provincia> listP = _provinciaService.getAll();
		cbx_provincia.setModel(new ListModelList<Provincia>(listP));
	}

	public void onClick$btn_proximo(){
		proximo();
	}
	
	public void proximo(){
	saveOrUpdade();
	Executions.getCurrent().getSession().setAttribute("ss_maritimo", _maritimo);
	div_content_out.detach();
	inc_main.setSrc("/views/SeccaoTecnica/registar_maritimoDois.zul");
	}
	
	public void anterior(){
		//Include inc  = (Include) win_regDados.getParent();
		saveOrUpdade();
		Executions.getCurrent().getSession().setAttribute("ss_maritimo", _maritimo);
		div_content_out.detach();
		inc_main.setSrc("/views/SeccaoTecnica/registar_maritimoUm.zul");
	}
	
	public void saveOrUpdade(){
		if(_maritimo==null){
			_maritimo = new Maritimo();
			gravar(_maritimo);
		}else{
			gravar(_maritimo);
		}
//		_utentePass = new Utente();
//		_utentePass = _utente;
		
//		_utente = new Utente();
		
	}
	
	public void gravar(Maritimo m){
		m.setNome(tbx_nome.getValue());
		m.setApelido(tbx_apelido.getValue());
		m.setDataNascimento(dtb_dataNascimento.getValue());
		m.setNuit(tbx_nuit.getValue());
		m.setProvinciaResidencia(cbx_provincia.getValue());
		m.setDistritoResidencia(cbx_distrito.getValue());
		m.setBairro(tbx_bairro.getValue());
		m.setRua(tbx_rua.getValue());
		m.setNrCasa(tbx_nrCasa.getValue());
		m.setQuarteirao(tbx_quarteirao.getValue());
		m.setCelular(tbx_celular.getValue());
		m.setCelularAlternativo(tbx_celular2.getValue());
		m.setEmail(tbx_email.getValue());
		_maritimoService.saveOrUpdate(m);
	}
	
	public void preencherCampos(Maritimo m){
		if(m!=null){
			tbx_nome.setValue(m.getNome());
			tbx_apelido.setValue(m.getApelido());
			dtb_dataNascimento.setValue(m.getDataNascimento());
			tbx_nuit.setValue(m.getNuit());
			cbx_provincia.setValue(m.getProvinciaResidencia());
			cbx_distrito.setValue(m.getDistritoResidencia());
			tbx_bairro.setValue(m.getBairro());
			tbx_rua.setValue(m.getRua());
			tbx_nrCasa.setValue(m.getNrCasa());
			tbx_quarteirao.setValue(m.getQuarteirao());
			tbx_celular.setValue(m.getCelular());
			tbx_celular2.setValue(m.getCelularAlternativo());
			tbx_email.setValue(m.getEmail());
		}
	}

}
