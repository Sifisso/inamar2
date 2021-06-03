package mz.ciuem.inamar.seccaoTecnica.controller;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.ClasseMaritimo;
import mz.ciuem.inamar.entity.GrupoMaritimo;
import mz.ciuem.inamar.entity.Maritimo;
import mz.ciuem.inamar.service.CategoriaMaritimoService;
import mz.ciuem.inamar.service.ClasseMaritimoService;
import mz.ciuem.inamar.service.Grupo_MaritimoService;
import mz.ciuem.inamar.service.MaritimoService;

public class MaritimoGCCCtrl extends GenericForwardComposer{
	
	private Combobox cbx_tipoUtente;
	private Combobox cbx_grupo, cbx_classe, cbx_categoria, cbx_olhos;
	private Include inc_main;
	private Div div_content_out;
	private Window win_regMaritimos;
	private Textbox tbx_nrRegisto, tbx_nrExpediente, tbx_nrLivro, tbx_nrFolhas;
	private Datebox dtb_dataInicioCategoria;
	private Doublebox dbx_altura;
	
	@WireVariable
	private Grupo_MaritimoService _grupoMaritimoService;
	@WireVariable
	private ClasseMaritimoService _classeMaritimoService;
	@WireVariable
	private CategoriaMaritimoService _categoriaMaritimoService;
	@WireVariable
	private MaritimoService _maritimoService;
	
	Maritimo _maritimo;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_grupoMaritimoService= (Grupo_MaritimoService) SpringUtil.getBean("grupo_MaritimoService");
		_classeMaritimoService = (ClasseMaritimoService) SpringUtil.getBean("classeMaritimoService");
		_categoriaMaritimoService = (CategoriaMaritimoService) SpringUtil.getBean("categoriaMaritimoService");
		_maritimoService = (MaritimoService) SpringUtil.getBean("maritimoService");
		
		_maritimo = (Maritimo) Executions.getCurrent().getSession().getAttribute("ss_maritimo");
		
		if(_maritimo==null) {
			_maritimo = new Maritimo();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherGrupoMaritimo();
		preenchercampos(_maritimo);
	}
	
	 private void preenchercampos(Maritimo _maritimo2) {
		cbx_grupo.setValue(_maritimo.getGrupo_maritimo());
		cbx_classe.setValue(_maritimo.getClasse_maritimo());
		cbx_olhos.setValue(_maritimo.getCorOlhos());
		cbx_categoria.setValue(_maritimo.getCategoria_maritimo().getNome());
		tbx_nrFolhas.setValue(_maritimo.getNrFolhas());
		tbx_nrExpediente.setValue(_maritimo.getNrExpediente());
		tbx_nrLivro.setValue(_maritimo.getNrLivro());
		tbx_nrRegisto.setValue(_maritimo.getNrInscricaoMaritima());
		dtb_dataInicioCategoria.setValue(_maritimo.getDataInicioCategoria());
		dbx_altura.setValue(_maritimo.getAltura());
		Comboitem ci = new Comboitem();
		ci.setValue(_maritimo.getCategoria_maritimo());
		cbx_categoria.appendChild(ci);
		cbx_categoria.setSelectedIndex(0);
	}
	 
    public void onClick$btn_proximo(Event e){
    	if(_maritimo==null)_maritimo = new Maritimo();
    	gravar();
    }

	private void gravar() {
		try {
			_maritimo.setNrFolhas(tbx_nrFolhas.getValue());
			_maritimo.setAltura(dbx_altura.getValue());
			_maritimo.setNrExpediente(tbx_nrExpediente.getValue());
			_maritimo.setNrLivro(tbx_nrLivro.getValue());
			_maritimo.setNrInscricaoMaritima(tbx_nrRegisto.getValue());
			_maritimo.setDataInicioCategoria(dtb_dataInicioCategoria.getValue());
			_maritimo.setGrupo_maritimo(cbx_grupo.getValue());
			_maritimo.setClasse_maritimo(cbx_classe.getValue());
			_maritimo.setCorOlhos(cbx_olhos.getValue());
			_maritimo.setCategoria_maritimo((CategoriaMaritimo)cbx_categoria.getSelectedItem().getValue());
			_maritimoService.saveOrUpdate(_maritimo);
			redirecionar(_maritimo);
		} catch (NullPointerException e) {
      System.out.println("Erro ");
		}
	}

	private void redirecionar(Maritimo _maritimo2) {
		Executions.getCurrent().getSession().setAttribute("ss_maritimo", _maritimo2);
		div_content_out.detach();
		inc_main.setSrc("/views/SeccaoTecnica/registar_maritimoUm.zul");
	}

	private void preencherGrupoMaritimo() {
	      cbx_grupo.setModel(new ListModelList<GrupoMaritimo>(_grupoMaritimoService.getAll()));		
		}
		
	   public void onSelect$cbx_grupo(){
			cbx_classe.getItems().clear();
			cbx_categoria.getItems().clear();
			GrupoMaritimo gm = (GrupoMaritimo) cbx_grupo.getSelectedItem().getValue();
			cbx_classe.setModel(new ListModelList<ClasseMaritimo>(_classeMaritimoService.findByGrupoMaritimo(gm)));
		}

		public void onSelect$cbx_classe(){
			cbx_categoria.getItems().clear();
			ClasseMaritimo cm = (ClasseMaritimo) cbx_classe.getSelectedItem().getValue();
			cbx_categoria.setModel(new ListModelList<CategoriaMaritimo>(_categoriaMaritimoService.findByClasseMaritimo(cm)));
		}
	
	   
	

}
