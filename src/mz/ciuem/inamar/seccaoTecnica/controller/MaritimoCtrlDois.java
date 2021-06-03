package mz.ciuem.inamar.seccaoTecnica.controller;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.entity.Maritimo;
import mz.ciuem.inamar.service.MaritimoService;

public class MaritimoCtrlDois extends GenericForwardComposer{
	
	private Button btn_proximo,btn_anterior;
	private Window win_regDetalhes;
	private Include inc_main;
	private Div div_content_out;
	
	private Textbox tbx_nomePai;
	private Textbox tbx_nomeMae;
	private Textbox tbx_nrDocumento;
	private Textbox tbx_localEmissao;
	private Textbox tbx_ocupacao;
	private Datebox dtb_dataEmissao;
	private Combobox cbx_estadoCivil;
	private Combobox cbx_tipoDocumento;
	private Combobox cbx_nivelAcademico;
	
	@WireVariable
	private MaritimoService _maritimoService;
	
	Maritimo _maritimo;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		_maritimoService = (MaritimoService) SpringUtil.getBean("maritimoService");
		_maritimo = (Maritimo) Executions.getCurrent().getSession().getAttribute("ss_maritimo");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos(_maritimo);
	}
	
	
	public void onClick$btn_proximo(){
		proximo();
	}
	
	public void onClick$btn_anterior(){
		anterior();
	}
	
	public void proximo(){
	//Include inc  = (Include) win_regDados.getParent();
	saveOrUpdade();
	Executions.getCurrent().getSession().setAttribute("ss_maritimo", _maritimo);
	div_content_out.detach();
	inc_main.setSrc("/views/SeccaoTecnica/confirmarRegistoMaritimo.zul");
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
	}
	
	public void gravar(Maritimo m){
		m.setNomePai(tbx_nomePai.getValue());
		m.setNomeMae(tbx_nomeMae.getValue());
		m.setEstadoCivil(cbx_estadoCivil.getValue());
		m.setTipoDocumento(cbx_tipoDocumento.getValue());
		m.setNumeroDocumento(tbx_nrDocumento.getValue());
		m.setLocalEmissao(tbx_localEmissao.getValue());
		m.setValidade(dtb_dataEmissao.getValue());
		m.setHabilitacaoLiteraria(cbx_nivelAcademico.getValue());
		m.setOcupacao(tbx_ocupacao.getValue());
		_maritimoService.saveOrUpdate(m);
	}
	
	public void preencherCampos(Maritimo m){
		if(m!=null){
			tbx_nomePai.setValue(m.getNomePai());
			tbx_nomeMae.setValue(m.getNomeMae());
			cbx_estadoCivil.setValue(m.getEstadoCivil());
			cbx_tipoDocumento.setValue(m.getTipoDocumento());
			tbx_nrDocumento.setValue(m.getNumeroDocumento());
			tbx_localEmissao.setValue(m.getLocalEmissao());
			dtb_dataEmissao.setValue(m.getValidade());
			cbx_nivelAcademico.setValue(m.getHabilitacaoLiteraria());
			tbx_ocupacao.setValue(m.getOcupacao());
		}
	}


}
