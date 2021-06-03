package mz.ciuem.inamar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tbl_embarcacoes")
public class Embarcacoes extends IdEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(name="nr_expediente")
	private String nrExpediente;
	
	@Column(name="nome_embarcacao")
	private String nomeEmbaracao;
	
	@Column(name="matricula")
	private String matricula;
	
	@Column(name="proprietario")
	private String proprietario;
	
	@Column(name="antigo_proprietario")
	private String AntigoProprietario;
	
	@Column(name="tonelagem")
	private String tonelagem;
	
	@Column(name="nr_registo")
	private String nrRegisto;
	
	@Column(name="moeda")
	private String moeda;
	
	@Column(name="marca_motor")
	private String marcaMotor;
	
	@Column(name="nr_motor")
	private String nrMotor;
	
	@Column(name="comprimento")
	private double comprimento;
	
	@Column(name="boca")
	private double boca;
	
	@Column(name="pontal")
	private double pontal;
	
	@Column(name="custo_aquisicao")
	private double custoAquisicao;
	
	@Column(name="data_registo")
	@Temporal(TemporalType.DATE)
	private Date dataRegisto;
	
	private boolean ehTemporario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delegacao_id", insertable = true, updatable = true)
	private Delegacao delegacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "motivoRegisto_id", insertable = true, updatable = true)
	private MotivoRegisto motivoRegisto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "classe_id", insertable = true, updatable = true)
	private ClasseEmbarcacao classe;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "peticaoEmbarcacao_id", insertable = true, updatable = true)
	private PeticaoEmbarcacao peticaoEmbarcacao;;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "servicoDestino_id", insertable = true, updatable = true)
	private ServicoDestino servicoDestino;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paisProveniencia_id", insertable = true, updatable = true)
	private Pais paisProveniencia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zonaActividade_id", insertable = true, updatable = true)
	private ActividadeZonaMaritima zonaActividade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoCombustivel_id", insertable = true, updatable = true)
	private TipoCombustivel tipoCombustivel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aparelhoGoverno_id", insertable = true, updatable = true)
	private AparelhoGoverno aparelhoGoverno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "meioEsgoto_id", insertable = true, updatable = true)
	private MeioEsgoto meioEsgoto;

	public String getNrExpediente() {
		return nrExpediente;
	}

	public void setNrExpediente(String nrExpediente) {
		this.nrExpediente = nrExpediente;
	}

	public String getNomeEmbaracao() {
		return nomeEmbaracao;
	}

	public void setNomeEmbaracao(String nomeEmbaracao) {
		this.nomeEmbaracao = nomeEmbaracao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getAntigoProprietario() {
		return AntigoProprietario;
	}

	public void setAntigoProprietario(String antigoProprietario) {
		AntigoProprietario = antigoProprietario;
	}

	public String getTonelagem() {
		return tonelagem;
	}

	public void setTonelagem(String tonelagem) {
		this.tonelagem = tonelagem;
	}

	public String getNrRegisto() {
		return nrRegisto;
	}

	public void setNrRegisto(String nrRegisto) {
		this.nrRegisto = nrRegisto;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public String getMarcaMotor() {
		return marcaMotor;
	}

	public void setMarcaMotor(String marcaMotor) {
		this.marcaMotor = marcaMotor;
	}

	public String getNrMotor() {
		return nrMotor;
	}

	public void setNrMotor(String nrMotor) {
		this.nrMotor = nrMotor;
	}

	public double getComprimento() {
		return comprimento;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public double getBoca() {
		return boca;
	}

	public void setBoca(double boca) {
		this.boca = boca;
	}

	public double getPontal() {
		return pontal;
	}

	public void setPontal(double pontal) {
		this.pontal = pontal;
	}

	public double getCustoAquisicao() {
		return custoAquisicao;
	}

	public void setCustoAquisicao(double custoAquisicao) {
		this.custoAquisicao = custoAquisicao;
	}

	public Date getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(Date dataRegisto) {
		this.dataRegisto = dataRegisto;
	}


	public boolean isEhTemporario() {
		return ehTemporario;
	}

	public void setEhTemporario(boolean ehTemporario) {
		this.ehTemporario = ehTemporario;
	}

	public Delegacao getDelegacao() {
		return delegacao;
	}

	public void setDelegacao(Delegacao delegacao) {
		this.delegacao = delegacao;
	}

	public MotivoRegisto getMotivoRegisto() {
		return motivoRegisto;
	}

	public void setMotivoRegisto(MotivoRegisto motivoRegisto) {
		this.motivoRegisto = motivoRegisto;
	}

	public ClasseEmbarcacao getClasse() {
		return classe;
	}

	public void setClasse(ClasseEmbarcacao classe) {
		this.classe = classe;
	}

	public ServicoDestino getServicoDestino() {
		return servicoDestino;
	}

	public void setServicoDestino(ServicoDestino servicoDestino) {
		this.servicoDestino = servicoDestino;
	}

	public Pais getPaisProveniencia() {
		return paisProveniencia;
	}

	public void setPaisProveniencia(Pais paisProveniencia) {
		this.paisProveniencia = paisProveniencia;
	}

	public ActividadeZonaMaritima getZonaActividade() {
		return zonaActividade;
	}

	public void setZonaActividade(ActividadeZonaMaritima zonaActividade) {
		this.zonaActividade = zonaActividade;
	}

	public TipoCombustivel getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public AparelhoGoverno getAparelhoGoverno() {
		return aparelhoGoverno;
	}

	public void setAparelhoGoverno(AparelhoGoverno aparelhoGoverno) {
		this.aparelhoGoverno = aparelhoGoverno;
	}

	public MeioEsgoto getMeioEsgoto() {
		return meioEsgoto;
	}

	public void setMeioEsgoto(MeioEsgoto meioEsgoto) {
		this.meioEsgoto = meioEsgoto;
	}

	public PeticaoEmbarcacao getPeticaoEmbarcacao() {
		return peticaoEmbarcacao;
	}

	public void setPeticaoEmbarcacao(PeticaoEmbarcacao peticaoEmbarcacao) {
		this.peticaoEmbarcacao = peticaoEmbarcacao;
	}

}
