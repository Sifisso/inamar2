package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="actividades_licenca")
public class ActividadeLicenca extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="isActivo")
	private boolean isActivo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

}
