package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="actos")

public class Actos extends IdEntity  {
	
	
	private static final long serialVersionUID = -580835934094969154L;

	@Column(name="descricao_actos")
	private String descricaoActos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userRole_id", insertable = true, updatable = true)
	private UserRole userRole;
	
	private boolean activo;

	public String getDescricaoActos() {
		return descricaoActos;
	}

	public void setDescricaoActos(String descricaoActos) {
		this.descricaoActos = descricaoActos;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	

}
