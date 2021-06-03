package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Categoria;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.MotivoRegisto;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.CategoriaService;
import mz.ciuem.inamar.service.MotivoRegistoService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class MotivoCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_motivos;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regMotivo;
	
	Execution ex = Executions.getCurrent();
	
	private MotivoRegisto _motivoRegisto;
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	@WireVariable
	private MotivoRegistoService _motivoRegistoService;
	
    private List<MotivoRegisto> listMotivoRegisto = new ArrayList<MotivoRegisto>();
    
    

    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_motivoRegistoService = (MotivoRegistoService) SpringUtil.getBean("motivoRegistoService");
   		
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
   		}
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
   		_motivoRegisto.setActivo(rbx_actSim.isChecked() ? true : false);
   		_motivoRegisto.setNome(txb_nome.getValue());
        
        _motivoRegistoService.update(_motivoRegisto);
	   		showNotifications("Motivo actualizado com sucesso!", "info");
	   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		MotivoRegisto _mr = new MotivoRegisto();
   	    
   		_mr.setActivo(rbx_actSim.isChecked() ? true : false);
   		_mr.setNome(txb_nome.getValue());

   		boolean existe = false;
   		
   		for (MotivoRegisto motivoRegisto: listMotivoRegisto ){
   			
   			if (motivoRegisto.getNome().equals(_mr.getNome())){
   				existe=true;
   			}
   		}
   		
   		
   		if (existe==false){
		   		_motivoRegistoService.create(_mr);
		   		showNotifications("Motivo registado com sucesso!", "info");
		   		limparCampos();
   		}else{
   				showNotifications("Motivo de registo existente", "error");
   		}
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_areas(Event e){
   		_motivoRegisto = lbx_motivos.getSelectedItem().getValue();
   		txb_nome.setValue(_motivoRegisto.getNome());
   	    rbx_actNao.setChecked(!_motivoRegisto.isActivo());
   	    rbx_actSim.setChecked(_motivoRegisto.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", "Lista de �reas");
   		MasterRep.imprimir("/reportParam/reportArea.jrxml", listMotivoRegisto, mapaParam, win_regMotivo);
   	}
   	
//  	public void findByNomeIsAdmar(String nome, boolean isActivo, boolean isAdmar){
//  		listMotivoRegisto = _areaService.findByNomeIsActivoIsAdmar(nome, isActivo, isAdmar);
//   		lbx_areas.setModel(new ListModelList<Area>(listArea));
//   	}
   	
   	private void limparCampos() {
   		txb_nome.setRawValue(null);
   		rbx_actSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
   	private void listar() {
   		listMotivoRegisto = _motivoRegistoService.getAll();
   		lbx_motivos.setModel(new ListModelList<MotivoRegisto>(listMotivoRegisto));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_motivos,"before_center", 4000, true);
   	}

}
