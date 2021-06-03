package mz.ciuem.inamar.seccaoTecnica.controller;

import java.util.List;

import mz.ciuem.inamar.entity.ActividadeZonaMaritima;
import mz.ciuem.inamar.entity.AparelhoGoverno;
import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Embarcacoes;
import mz.ciuem.inamar.entity.Maritimo;
import mz.ciuem.inamar.entity.MeioEsgoto;
import mz.ciuem.inamar.entity.MotivoRegisto;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.entity.TipoCombustivel;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.ActividadeZonaMaritimaService;
import mz.ciuem.inamar.service.AparelhoGovernoService;
import mz.ciuem.inamar.service.DistritoService;
import mz.ciuem.inamar.service.EmbarcacoesService;
import mz.ciuem.inamar.service.MaritimoService;
import mz.ciuem.inamar.service.MeioEsgotoService;
import mz.ciuem.inamar.service.MotivoRegistoService;
import mz.ciuem.inamar.service.PaisService;
import mz.ciuem.inamar.service.ProvinciaService;
import mz.ciuem.inamar.service.TipoCombustivelService;
import mz.ciuem.inamar.service.UtenteService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class EmbarcacoesUmCtrl extends GenericForwardComposer{
	
	private Button btn_proximo;
	private Window win_regDados;
	private Include inc_main;
	private Div div_content_out;
	
	private Textbox tbx_antigoProprietario, tbx_marcaMotor;
	private Combobox cbx_pais, cbx_motivo, cbx_tipoCombustivel, cbx_zonaActividade, cbx_aparelhoGoverno, cbx_meioEsgoto;
	private Doublebox dbx_comprimento, dbx_boca, dbx_pontal, dbx_custoAquisicao;
	private Radio rdx_temporaria, rdx_definitiva;
	
	@WireVariable
	private PaisService _paisService;
	@WireVariable
	private EmbarcacoesService _embarcacoesService;
	@WireVariable
	private MotivoRegistoService _motivoRegistoService;
	@WireVariable
	private TipoCombustivelService _tipoCombustivelService;
	@WireVariable
	private ActividadeZonaMaritimaService _actividadeZonaMaritimaService;
	@WireVariable
	private AparelhoGovernoService _aparelhoGovernoService;
	@WireVariable
	private MeioEsgotoService _meioEsgotoService;
	private Session _session;
	
	Embarcacoes _embarcacoes;
	private User _loggedUser;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_embarcacoesService = (EmbarcacoesService) SpringUtil.getBean("embarcacoesService");
		_paisService = (PaisService) SpringUtil.getBean("paisService");
		_motivoRegistoService = (MotivoRegistoService) SpringUtil.getBean("motivoRegistoService");
		_tipoCombustivelService = (TipoCombustivelService) SpringUtil.getBean("tipoCombustivelService");
		_actividadeZonaMaritimaService = (ActividadeZonaMaritimaService) SpringUtil.getBean("actividadeZonaMaritimaService");
		_aparelhoGovernoService = (AparelhoGovernoService) SpringUtil.getBean("aparelhoGovernoService");
		_meioEsgotoService = (MeioEsgotoService) SpringUtil.getBean("meioEsgotoService");
		
		_embarcacoes = (Embarcacoes) Executions.getCurrent().getSession().getAttribute("ss_embarcacoes");
		//verificarLogado();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		preencherPaises();
		preencherMotivo();
		preencherActividadeZonaMaritima();
		preencherTipoCombustivel();
		preecherAparelhoGoverno();
		preecherMeioEsgoto();
		preencherCampos(_embarcacoes);
	}
	
	private void preecherMeioEsgoto(){
		List<MeioEsgoto> listMeioEsgoto = _meioEsgotoService.getAll();
		cbx_meioEsgoto.setModel(new ListModelList<MeioEsgoto>(listMeioEsgoto));
	}
	
	private void preecherAparelhoGoverno(){
		List<AparelhoGoverno> listAparelhoGoverno = _aparelhoGovernoService.getAll();
		cbx_aparelhoGoverno.setModel(new ListModelList<AparelhoGoverno>(listAparelhoGoverno));
	}

	private void preencherActividadeZonaMaritima(){
		List<ActividadeZonaMaritima> listActividadeZonaMaritima = _actividadeZonaMaritimaService.getAll();
		cbx_zonaActividade.setModel(new ListModelList<ActividadeZonaMaritima>(listActividadeZonaMaritima));
	}
	
	
	private void preencherPaises() {
		List<Pais> listP = _paisService.getAll();
		cbx_pais.setModel(new ListModelList<Pais>(listP));
	}
	
	private void preencherMotivo(){
		List<MotivoRegisto> listMotivo = _motivoRegistoService.getAll();
		cbx_motivo.setModel(new ListModelList<MotivoRegisto>(listMotivo));
	}
	
	private void preencherTipoCombustivel(){
		List<TipoCombustivel> listTipoCombustivel = _tipoCombustivelService.getAll();
		cbx_tipoCombustivel.setModel(new ListModelList<TipoCombustivel>(listTipoCombustivel));
	}

	public void onClick$btn_proximo(){
		proximo();
	}
	
	public void onClick$btn_anterior(){
		anterior();
	}
	
	public void proximo(){
	saveOrUpdade();
	showNotifications("Embarcação registado com sucesso", "info");
	}
	
	public void anterior(){
		//Include inc  = (Include) win_regDados.getParent();
		saveOrUpdade();
		Executions.getCurrent().getSession().setAttribute("ss_embarcacoes", _embarcacoes);
		div_content_out.detach();
		inc_main.setSrc("/views/SeccaoTecnica/registar_embarcacao.zul");
	}
	
	public void saveOrUpdade(){
		if(_embarcacoes==null){
			_embarcacoes = new Embarcacoes();
			gravar(_embarcacoes);
		}else{
			gravar(_embarcacoes);
		}
//		_utentePass = new Utente();
//		_utentePass = _utente;
		
//		_utente = new Utente();
		
	}
	
	public void gravar(Embarcacoes e){
		e.setComprimento(dbx_comprimento.getValue());
		e.setBoca(dbx_boca.getValue());
		e.setPontal(dbx_pontal.getValue());
		e.setPaisProveniencia((Pais)cbx_pais.getSelectedItem().getValue());
		e.setMotivoRegisto((MotivoRegisto)cbx_motivo.getSelectedItem().getValue());
		e.setTipoCombustivel((TipoCombustivel)cbx_tipoCombustivel.getSelectedItem().getValue());
		e.setZonaActividade((ActividadeZonaMaritima)cbx_zonaActividade.getSelectedItem().getValue());
		e.setAparelhoGoverno((AparelhoGoverno)cbx_aparelhoGoverno.getSelectedItem().getValue());
		e.setMeioEsgoto((MeioEsgoto)cbx_meioEsgoto.getSelectedItem().getValue());
		e.setAntigoProprietario(tbx_antigoProprietario.getValue());
		e.setMarcaMotor(tbx_marcaMotor.getValue());
		e.setCustoAquisicao(dbx_custoAquisicao.getValue());
		e.setEhTemporario(rdx_temporaria.isChecked() ? true : false);
		
		_embarcacoesService.saveOrUpdate(e);
	}
	
	public void preencherCampos(Embarcacoes e){
		if(e!=null){
			dbx_comprimento.setValue(e.getComprimento());
			dbx_boca.setValue(e.getBoca());
			dbx_pontal.setValue(e.getPontal());
			cbx_pais.setValue(e.getPaisProveniencia().getDesignacao());
			cbx_motivo.setValue(e.getMotivoRegisto().getNome());
			cbx_tipoCombustivel.setValue(e.getTipoCombustivel().getNome());
			cbx_zonaActividade.setValue(e.getZonaActividade().getNome());
			cbx_aparelhoGoverno.setValue(e.getAparelhoGoverno().getNome());
			cbx_meioEsgoto.setValue(e.getMeioEsgoto().getNome());
			tbx_antigoProprietario.setValue(e.getAntigoProprietario());
			tbx_marcaMotor.setValue(e.getMarcaMotor());
			dbx_custoAquisicao.setValue(e.getCustoAquisicao());
			rdx_temporaria.setChecked(e.isEhTemporario());
		}
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, tbx_marcaMotor,"before_center", 4000, true);
	}

}
