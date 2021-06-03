package mz.ciuem.inamar.seccaoTecnica.controller;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.Maritimo;
import mz.ciuem.inamar.service.CategoriaMaritimoService;
import mz.ciuem.inamar.service.MaritimoService;
import mz.ciuem.inamar.service.UserRoleService;
import mz.ciuem.inamar.service.UserService;

@SuppressWarnings({ "serial", "rawtypes" })
public class ConfirmarRegistoMaritimoCtrl extends GenericForwardComposer{
	
	private Button btn_proximo, btn_actualizar, btn_peticao,btn_confirmar,btn_anterior,btn_continuar;
	private Window win_confirmarRegisto;
	private Include inc_main;
	private Div div_content_out,div_modal;
	
	private Textbox tbx_nome;
	private Textbox tbx_nrRegisto;
	private Textbox tbx_contacto;
	private Intbox ibx_idade;
	private Textbox tbx_nrDocumento;
	private Textbox tbx_localEmissao;
	private Textbox tbx_nuit, tbx_bairro;
	private Datebox dtb_dataEmissao;
	private Combobox cbx_tipoDocumento, cbx_grupo, cbx_classe;
	//email senha senha2
	
	@WireVariable
	private MaritimoService _maritimoService;
	@WireVariable
	private UserService _userService;
	@WireVariable
	private UserRoleService _userRoleService;
	@WireVariable
	private CategoriaMaritimoService _categoriaMaritimoService;
	
	Maritimo _maritimo;
	Maritimo _maritimoPass;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_userService = (UserService) SpringUtil.getBean("userService");
		_maritimoService = (MaritimoService) SpringUtil.getBean("maritimoService");
		_userRoleService = (UserRoleService) SpringUtil.getBean("userRoleService");
		_categoriaMaritimoService = (CategoriaMaritimoService) SpringUtil.getBean("categoriaMaritimoService");
		_maritimo = (Maritimo) Executions.getCurrent().getSession().getAttribute("ss_maritimo");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos(_maritimo);
	}
	

	public void onClick$btn_actualizar(){
		habilitarCampos();
		btn_actualizar.setVisible(false);
	}
	
	public void onClick$btn_confirmar(){
		gravar();
		div_modal.setVisible(true);
		btn_confirmar.setVisible(false);
		btn_actualizar.setVisible(false);
		btn_anterior.setVisible(false);
		showNotifications("Marítimo registado com sucesso", "info");
		_maritimo = new Maritimo();
		session.removeAttribute("ss_maritimo");
		 
	}
	

	public void onClicPeticao(final ForwardEvent e) {
		Executions.getCurrent().getSession().setAttribute("ss_maritimo", _maritimo);
		div_content_out.detach();
		
		inc_main.setSrc("/views/SeccaoTecnica/gerir_maritimo.zul");
		
	}
	
	public void onClick$btn_anterior(){
		anterior();
	}
	
	
	public void preencherCampos(Maritimo m){
		if(m!=null){
			tbx_nome.setValue(m.getNome()+" "+ m.getApelido());
			tbx_nrDocumento.setValue(m.getNumeroDocumento());
			tbx_localEmissao.setValue(m.getLocalEmissao());
			tbx_nuit.setValue(m.getNuit());
			tbx_nrRegisto.setValue(m.getNrInscricaoMaritima());
			tbx_contacto.setValue(m.getCelular());
			tbx_bairro.setValue(m.getBairro());
			ibx_idade.setValue(m.getIdade());
			dtb_dataEmissao.setValue(m.getValidade());
			cbx_tipoDocumento.setValue(m.getTipoDocumento());
			cbx_grupo.setValue(m.getGrupo_maritimo());
			cbx_classe.setValue(m.getClasse_maritimo());
		}
	}
	
	public void gravar(){
		if(_maritimo.getUserLogin()==null){
				pegarValores();
		}else{
			Messagebox.show("Ops! Houve algum erro!!!");
		}
		
		_maritimoService.update(_maritimo);
	}
	
	
	
	public void pegarValores(){
		_maritimo.setNome(tbx_nome.getValue());
		_maritimo.setNrInscricaoMaritima(tbx_nrRegisto.getValue());
		_maritimo.setCelular(tbx_contacto.getValue());
		_maritimo.setBairro(tbx_bairro.getValue());
		_maritimo.setIdade(ibx_idade.getValue());
		_maritimo.setNumeroDocumento(tbx_nrDocumento.getValue());
		_maritimo.setLocalEmissao(tbx_localEmissao.getValue());
		_maritimo.setNuit(tbx_nuit.getValue());
		_maritimo.setValidade(dtb_dataEmissao.getValue());
		_maritimo.setTipoDocumento(cbx_tipoDocumento.getValue());
		_maritimo.setGrupo_maritimo(cbx_grupo.getValue());
		_maritimo.setClasse_maritimo(cbx_classe.getValue());
	}
	
	public void habilitarCampos(){
		tbx_nome.setDisabled(false);
		tbx_nrRegisto.setDisabled(false);
		tbx_contacto.setDisabled(false);
		ibx_idade.setDisabled(false);
		tbx_nrDocumento.setDisabled(false);
		tbx_localEmissao.setDisabled(false);
		tbx_nuit.setDisabled(false);
		dtb_dataEmissao.setDisabled(false);
		cbx_tipoDocumento.setDisabled(false);
		cbx_grupo.setDisabled(false);
		cbx_classe.setDisabled(false);
		tbx_bairro.setDisabled(false);
	}
	
	public void desabilitarCampos(){
		tbx_nome.setDisabled(true);
		tbx_nrRegisto.setDisabled(true);
		tbx_contacto.setDisabled(true);
		ibx_idade.setDisabled(true);
		tbx_nrDocumento.setDisabled(true);
		tbx_localEmissao.setDisabled(true);
		tbx_nuit.setDisabled(true);
		dtb_dataEmissao.setDisabled(true);
		cbx_tipoDocumento.setDisabled(true);
		cbx_grupo.setDisabled(true);
		cbx_classe.setDisabled(true);
		tbx_bairro.setDisabled(true);
	}
	
	public void anterior(){
		gravar();
		Executions.getCurrent().getSession().setAttribute("ss_maritimo", _maritimo);
		div_content_out.detach();
		inc_main.setSrc("/views/SeccaoTecnica/registar_maritimoDois.zul");
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, tbx_nome,"before_center", 4000, true);
	}

}
