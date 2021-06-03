package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.ActividadeLicenca;
import mz.ciuem.inamar.entity.ClasseEmbarcacao;
import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.service.ActividadeLicencaService;
import mz.ciuem.inamar.service.ClasseEmbarcacaoService;
import mz.ciuem.inamar.service.LocalPraticaService;
import net.sf.jasperreports.engine.JRException;

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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ActividadesLicencaCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_actividadeLicenca;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regActividade;
	
	Execution ex = Executions.getCurrent();
	
	private ActividadeLicenca actividadeLicenca;
	
	@WireVariable
	private ActividadeLicencaService _actividadeLicencaService;
	
    private List<ActividadeLicenca> listActividadeLicenca = new ArrayList<ActividadeLicenca>();
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_actividadeLicencaService = (ActividadeLicencaService) SpringUtil.getBean("actividadeLicencaService");
   		
   		
   	}

   	@SuppressWarnings("unchecked")
   	@Override
   	public void doAfterCompose(Component comp) throws Exception {
   		// TODO Auto-generated method stub
   		super.doAfterCompose(comp);

   		listar();
   	}
   	
   	public void onActivoActividade(ForwardEvent e){
		 ActividadeLicenca actividadeLicenca =(ActividadeLicenca) e.getData();
		 
		 actividadeLicenca.setActivo(false);
		 
		 _actividadeLicencaService.update(actividadeLicenca);
		 
		 listar();
		
	}
	
	
	
	public void onInactivoActividade(ForwardEvent e){
		ActividadeLicenca actividadeLicenca =(ActividadeLicenca) e.getData();
		 
		 actividadeLicenca.setActivo(true);
		 
		 _actividadeLicencaService.update(actividadeLicenca);
		 
		 listar();
	}


  /* public void onClickprcurar(ForwardEvent e)  {
                String nome = txb_nomefind.getValue();
                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
                findByNomeIsAdmar(nome,isActivo);
   	}*/
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
        actividadeLicenca.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
        actividadeLicenca.setNome(txb_nome.getValue());
   		
   		_actividadeLicencaService.update(actividadeLicenca);
   		showNotifications("Actividade Actualizada com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		ActividadeLicenca _al = new ActividadeLicenca();
   	    
   		_al.setActivo(rbx_actSim.isChecked() ? true : false);
           	    
   		_al.setNome(txb_nome.getValue());

   		_actividadeLicencaService.create(_al);
   		showNotifications("Actividade registada com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_localPratica(Event e){
   		actividadeLicenca = lbx_actividadeLicenca.getSelectedItem().getValue();
   		txb_nome.setValue(actividadeLicenca.getNome());
   	    rbx_actNao.setChecked(!actividadeLicenca.isActivo());
   	    rbx_actSim.setChecked(actividadeLicenca.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Locais de Pratica");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listActividadeLicenca, mapaParam, win_regActividade);
   	}
   	
 	/*public void findByNomeIsAdmar(String nome, boolean isActivo){
   		listFluxo = _fluxoService.findByNomeIsActivo(nome, isActivo);
   		lbx_fluxo.setModel(new ListModelList<Fluxo>(listFluxo));
   	}*/
   	
   	private void limparCampos() {
   		txb_nome.setRawValue(null);
   		rbx_actSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
   	private void listar() {
   		listActividadeLicenca = _actividadeLicencaService.getAll();
   		lbx_actividadeLicenca.setModel(new ListModelList<ActividadeLicenca>(listActividadeLicenca));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_actividadeLicenca,"before_center", 4000, true);
   	}


}
