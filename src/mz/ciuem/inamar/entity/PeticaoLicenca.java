package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="peticao_licenca")
public class PeticaoLicenca extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="nome_empresa")
	private String nomeEmpresa;
	
	@Column(name="endereco")
	private String endereco;
	
	@Column(name="contacto")
	private String contacto;
	
	@Column(name="email")
	private String email;
	
	@Column(name="nuit")
	private String nuit;
	
	@Column(name="valor")
	private double valor;
	
	@Column(name="entidade")
	private String entidade;
	
	@Column (name = "referencia")
	private String referencia;
	
	@Column (name = "embarcacoes_meios_disponiveis")
	private String embarcacaoesMeiosDisponiveis;
	
	@Column (name = "caracteristicas")
	private String caracteristicas;
	
	private boolean ehProprios;
	
	@Column (name = "descricao")
	private String descricao;
	
	@Column (name = "observacao")
	private String observacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "localPratica_id", insertable = true, updatable = true)
	private LocalPratica localPratica; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "actividadeLicenca_id", insertable = true, updatable = true)
	private ActividadeLicenca actividadeLicenca;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id", insertable = true, updatable = true)
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "peticao_id")
	private Peticao peticao;

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNuit() {
		return nuit;
	}

	public void setNuit(String nuit) {
		this.nuit = nuit;
	}

	public LocalPratica getLocalPratica() {
		return localPratica;
	}

	public void setLocalPratica(LocalPratica localPratica) {
		this.localPratica = localPratica;
	}

	public ActividadeLicenca getActividadeLicenca() {
		return actividadeLicenca;
	}

	public void setActividadeLicenca(ActividadeLicenca actividadeLicenca) {
		this.actividadeLicenca = actividadeLicenca;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getEmbarcacaoesMeiosDisponiveis() {
		return embarcacaoesMeiosDisponiveis;
	}

	public void setEmbarcacaoesMeiosDisponiveis(String embarcacaoesMeiosDisponiveis) {
		this.embarcacaoesMeiosDisponiveis = embarcacaoesMeiosDisponiveis;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public boolean isEhProprios() {
		return ehProprios;
	}

	public void setEhProprios(boolean ehProprios) {
		this.ehProprios = ehProprios;
	}
	
}
