package mz.ciuem.inamar.seccaoTecnica.controller;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.ClasseEmbarcacao;
import mz.ciuem.inamar.entity.ClasseMaritimo;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Embarcacoes;
import mz.ciuem.inamar.entity.GrupoMaritimo;
import mz.ciuem.inamar.entity.Maritimo;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.entity.ServicoDestino;
import mz.ciuem.inamar.service.CategoriaMaritimoService;
import mz.ciuem.inamar.service.ClasseEmbarcacaoService;
import mz.ciuem.inamar.service.ClasseMaritimoService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.EmbarcacoesService;
import mz.ciuem.inamar.service.Grupo_MaritimoService;
import mz.ciuem.inamar.service.MaritimoService;
import mz.ciuem.inamar.service.PeticaoEmbarcacaoService;
import mz.ciuem.inamar.service.ServicoDestinoService;

public class Embarcacaotrl extends GenericForwardComposer{
	
	private Button btn_proximo;
	private Combobox cbx_tipoUtente;
	private Combobox cbx_delegacao, cbx_classe, cbx_servicoDestino, cbx_peticaoEmbarcacao;
	private Include inc_main;
	private Div div_content_out;
	private Window win_regEmbarcacoes;
	private Textbox tbx_nrRegisto, tbx_nrExpediente, tbx_nome, tbx_matricula, tbx_proprietario, tbx_tonelagem;
	private Datebox dtb_dataRegisto;
	private Doublebox dbx_altura;
	
	
	@WireVariable
	private EmbarcacoesService _embarcacoesService;
	
	@WireVariable
	private DelegacaoService _delegacaoService;
	
	@WireVariable
	private ClasseEmbarcacaoService _classeEmbarcacaoService;
	
	@WireVariable
	private ServicoDestinoService _servicoDestinoService;
	
	@WireVariable
	private PeticaoEmbarcacaoService _peticaoEmbarcacaoService;
	
	Embarcacoes _embarcacoes;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_delegacaoService= (DelegacaoService) SpringUtil.getBean("delegacaoService");
		_classeEmbarcacaoService = (ClasseEmbarcacaoService) SpringUtil.getBean("classeEmbarcacaoService");
		_servicoDestinoService = (ServicoDestinoService) SpringUtil.getBean("servicoDestinoService");
		_embarcacoesService = (EmbarcacoesService) SpringUtil.getBean("embarcacoesService");
		_peticaoEmbarcacaoService = (PeticaoEmbarcacaoService) SpringUtil.getBean("peticaoEmbarcacaoService");
		
		_embarcacoes = (Embarcacoes) Executions.getCurrent().getSession().getAttribute("ss_embarcacoes");
		
		if(_embarcacoes==null) {
			_embarcacoes = new Embarcacoes();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		 prencherDelegacoes();
		 preencherClasses();
		 preencherServicoDestinos();
		preenchercampos(_embarcacoes);
	}
	
	 private void preenchercampos(Embarcacoes _embarcacoes2) {
		//cbx_delegacao.setValue(_embarcacoes.getDelegacao().getNome());
		cbx_delegacao.setValue(_embarcacoes.getDelegacao()==null?"":_embarcacoes.getDelegacao().getNome());
		cbx_peticaoEmbarcacao.setValue(_embarcacoes.getPeticaoEmbarcacao()==null?"":_embarcacoes.getPeticaoEmbarcacao().getNrExpediente());
		//cbx_servicoDestino.setValue(_embarcacoes.getServicoDestino().getNome());
		cbx_servicoDestino.setValue(_embarcacoes.getServicoDestino()==null?"":_embarcacoes.getServicoDestino().getNome());
		//cbx_classe.setValue(_embarcacoes.getClasse().getNome());
		cbx_classe.setValue(_embarcacoes.getClasse()==null?"":_embarcacoes.getClasse().getNome());
		tbx_nrExpediente.setValue(_embarcacoes.getNrExpediente());
		tbx_nrRegisto.setValue(_embarcacoes.getNrRegisto());
		tbx_nome.setValue(_embarcacoes.getNomeEmbaracao());
		tbx_matricula.setValue(_embarcacoes.getMatricula());
		dtb_dataRegisto.setValue(_embarcacoes.getDataRegisto());
		tbx_proprietario.setValue(_embarcacoes.getProprietario());
		tbx_tonelagem.setValue(_embarcacoes.getTonelagem());
	}
	 
    public void onClick$btn_proximo(Event e){
    	if(_embarcacoes==null)_embarcacoes = new Embarcacoes();
    	gravar();
    }

	private void gravar() {
		try {
			_embarcacoes.setProprietario(tbx_proprietario.getValue());
			_embarcacoes.setDataRegisto(dtb_dataRegisto.getValue());
			_embarcacoes.setNrExpediente(tbx_nrExpediente.getValue());
			_embarcacoes.setMatricula(tbx_matricula.getValue());
			_embarcacoes.setNomeEmbaracao(tbx_nome.getValue());
			_embarcacoes.setNrRegisto(tbx_nrRegisto.getValue());
			_embarcacoes.setDelegacao(cbx_delegacao.getSelectedItem().getValue());
			_embarcacoes.setClasse(cbx_classe.getSelectedItem().getValue());
			_embarcacoes.setTonelagem(tbx_tonelagem.getValue());
			_embarcacoes.setServicoDestino(cbx_servicoDestino.getSelectedItem().getValue());
			_embarcacoes.setPeticaoEmbarcacao(cbx_peticaoEmbarcacao.getSelectedItem().getValue());
			_embarcacoesService.saveOrUpdate(_embarcacoes);
			redirecionar(_embarcacoes);
		} catch (NullPointerException e) {
      System.out.println("Erro ");
		}
	}

	private void redirecionar(Embarcacoes _embarcacoes2) {
		Executions.getCurrent().getSession().setAttribute("ss_embarcacoes", _embarcacoes2);
		div_content_out.detach();
		inc_main.setSrc("/views/SeccaoTecnica/registar_embarcacoesUm.zul");
	}

	private void prencherDelegacoes(){
		cbx_delegacao.setModel(new ListModelList<Delegacao>(_delegacaoService.getAll())); 
	}
	
	private void preencherServicoDestinos(){
		cbx_servicoDestino.setModel(new ListModelList<ServicoDestino>(_servicoDestinoService.getAll())); 
	}
	
	private void preencherClasses(){
		cbx_classe.setModel(new ListModelList<ClasseEmbarcacao>(_classeEmbarcacaoService.getAll())); 
	}
	

}
