package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Actos;
import mz.ciuem.inamar.entity.TipoRequisito;
import mz.ciuem.inamar.service.ActosService;
import mz.ciuem.inamar.service.TipoRequisitoService;
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

public class ActosCtrl extends GenericForwardComposer{
	

	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_tipoRequisito, lbx_actos;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome, txb_codigo, txb_descricao;
	private Radio rbx_actSim, rbx_activoSim, rbx_activoNao;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regTipoRequisito;
	
	Execution ex = Executions.getCurrent();
	
	private TipoRequisito _tipoRequisito;
	
	private Actos _actos;
	
	@WireVariable
	private TipoRequisitoService _tipoRequisitoService;
	
	@WireVariable
	private ActosService _actosService;
	
    private List<TipoRequisito> listTipoRequisito = new ArrayList<TipoRequisito>();
    private List<Actos> listActos = new ArrayList<Actos>();
    

    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_tipoRequisitoService = (TipoRequisitoService) SpringUtil.getBean("tipoRequisitoService");
   		_actosService = (ActosService) SpringUtil.getBean("actosService");
   		
   	}

   	@SuppressWarnings("unchecked")
   	@Override
   	public void doAfterCompose(Component comp) throws Exception {
   		// TODO Auto-generated method stub
   		super.doAfterCompose(comp);

   		listar();
   	}


       public void onClickprcurar(ForwardEvent e)  {
                String nome = txb_nomefind.getValue();
                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
                findByNomeIsActivo(nome,isActivo);
   		}
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
        _actos.setActivo(rbx_activoSim.isChecked() ? true : false);
        _actos.setDescricaoActos(txb_descricao.getValue());
   		
   		_actosService.update(_actos);
   		showNotifications("Acto actualizado com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		Actos tr = new Actos();
   	    
        
    tr.setActivo(rbx_activoSim.isChecked() ? true : false);  	    
   	tr.setDescricaoActos(txb_descricao.getValue());
   		
   		
   	_actosService.create(tr);
   		showNotifications("Acto registado com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_actos(Event e){
   		_actos = lbx_actos.getSelectedItem().getValue();
   		txb_descricao.setValue(_actos.getDescricaoActos());
   	    rbx_activoNao.setChecked(!_actos.isActivo());
   	    rbx_activoSim.setChecked(_actos.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", "Lista de Instituicoes");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listTipoRequisito, mapaParam, win_regTipoRequisito);
   	}
   	
  	public void findByNomeIsActivo(String nome, boolean isActivo){
   		listTipoRequisito = _tipoRequisitoService.findByNomeActivo(nome, isActivo);
   		lbx_tipoRequisito.setModel(new ListModelList<TipoRequisito>(listTipoRequisito));
   	}
   	
   	private void limparCampos() {
   		txb_descricao.setRawValue(null);
   		rbx_activoSim.setChecked(false);
   	    rbx_activoNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
   	private void listar() {
   		listActos = _actosService.getAll();
   		lbx_actos.setModel(new ListModelList<Actos>(listActos));
   		
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_tipoRequisito,"before_center", 4000, true);
   	}
    
  

}
