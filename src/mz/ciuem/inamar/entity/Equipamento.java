package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="param_equipamento")
public class Equipamento extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipamento")
	private List<EquipamentoPedido> equipamentosPedidos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public List<EquipamentoPedido> getEquipamentosPedidos() {
		return equipamentosPedidos;
	}

	public void setEquipamentosPedidos(List<EquipamentoPedido> equipamentosPedidos) {
		this.equipamentosPedidos = equipamentosPedidos;
	}
	
}
