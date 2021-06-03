package mz.ciuem.inamar.seccaoTecnica.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Embarcacoes;
import mz.ciuem.inamar.entity.Maritimo;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.ActividadeZonaMaritimaService;
import mz.ciuem.inamar.service.AparelhoGovernoService;
import mz.ciuem.inamar.service.ClasseEmbarcacaoService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.EmbarcacoesService;
import mz.ciuem.inamar.service.MeioEsgotoService;
import mz.ciuem.inamar.service.MotivoRegistoService;
import mz.ciuem.inamar.service.PaisService;
import mz.ciuem.inamar.service.ServicoDestinoService;
import mz.ciuem.inamar.service.TipoCombustivelService;
import net.sf.jasperreports.engine.JRException;

public class GerirEmbarcacaoCtrl extends GenericForwardComposer{
	
	private Window win_gerirEmbaracao;
	private Include inc_main;
	private Div div_content_out;
	
	private Listbox lbx_embarcacoes;
	private Textbox txb_nomefind, txb_nrfind;
	private Radio rbx_Simfin, rbx_Naofin;
	
	@WireVariable
	private EmbarcacoesService _embarcacoesService;

	@WireVariable
	private DelegacaoService _delegacaoService;
	
	@WireVariable
	private MotivoRegistoService _motivoRegistoService;
	
	@WireVariable
	private ClasseEmbarcacaoService _classeEmbarcacaoService;
	
	@WireVariable
	private ServicoDestinoService _destinoService;
	
	@WireVariable
	private PaisService _paisService;
	
	@WireVariable
	private ActividadeZonaMaritimaService _AactividadeZonaMaritimaService;
	
	@WireVariable
	private TipoCombustivelService _tipoCombustivelService;
	
	@WireVariable
	private AparelhoGovernoService _aparelhoGovernoService;
	
	@WireVariable
	private MeioEsgotoService _esgotoService;
	
	private List<Embarcacoes> listEmbarcacoes = new ArrayList<Embarcacoes>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_embarcacoesService = (EmbarcacoesService) SpringUtil.getBean("embarcacoesService");
		
		_delegacaoService = (DelegacaoService) SpringUtil.getBean("delegacaoService");
		_motivoRegistoService = (MotivoRegistoService) SpringUtil.getBean("motivoRegistoService");
		_classeEmbarcacaoService = (ClasseEmbarcacaoService) SpringUtil.getBean("classeEmbarcacaoService");
		_destinoService = (ServicoDestinoService) SpringUtil.getBean("cervicoDestinoService");
		_paisService = (PaisService) SpringUtil.getBean("paisService");
		_AactividadeZonaMaritimaService = (ActividadeZonaMaritimaService) SpringUtil.getBean("actividadeZonaMaritimaService");
		_tipoCombustivelService = (TipoCombustivelService) SpringUtil.getBean("tipoCombustivelService");
		_aparelhoGovernoService = (AparelhoGovernoService) SpringUtil.getBean("aparelhoGovernoService");
		_esgotoService = (MeioEsgotoService) SpringUtil.getBean("meioEsgotoService");
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		listEmbarcaoes();
		session.removeAttribute("ss_embarcacoes");
	}

	private void listEmbarcaoes() {
		listEmbarcacoes = _embarcacoesService.getAll();
		lbx_embarcacoes.setModel(new ListModelList<Embarcacoes>(listEmbarcacoes));
	}
	
	public void onClickprcurarNome(ForwardEvent e){
		String nome = txb_nomefind.getValue();
		findByNome(nome);
	}
	
	public void onClickprcurarNrExpediente(ForwardEvent e){
		String nrExpediente = txb_nrfind.getValue();
		findByNrExpediente(nrExpediente);
	}
	
	public void findByNome(String nome){
		listEmbarcacoes = _embarcacoesService.findByNome(nome);
		lbx_embarcacoes.setModel(new ListModelList<Embarcacoes>(listEmbarcacoes));
	}
	
	public void findByNrExpediente(String nrExpediente){
		listEmbarcacoes = _embarcacoesService.findByNrExpediente(nrExpediente);
		lbx_embarcacoes.setModel(new ListModelList<Embarcacoes>(listEmbarcacoes));
	}
	
	public void onClickSubmeter(ForwardEvent e){
		Utente u = (Utente) e.getData();
		Executions.getCurrent().getSession().setAttribute("ss_embarcacoes", u);
		div_content_out.detach();
		inc_main.setSrc("/views/expediente/registar_pedido.zul");
	}
	
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "MARITIMOS");
   		MasterRep.imprimir("/reportParam/reportUtente.jrxml", listEmbarcacoes, mapaParam, win_gerirEmbaracao);
   	}
	
	@SuppressWarnings("unchecked")
	public void onClickEditar(final ForwardEvent e){
		Messagebox.show("Editar Maritimo?", "Maritimo", Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					Maritimo u = (Maritimo) e.getData();
					
					if(u!=null){
						Executions.getCurrent().getSession().setAttribute("ss_embarcacoes", u);
						div_content_out.detach();
						inc_main.setSrc("/views/expediente/registar_maritimo.zul");
					}
				}
			}
		});
		
	}

}
