package mz.ciuem.inamar.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="area_perfil_acto")
public class AreaPerfilActo extends IdEntity{
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userRoleArea_id", insertable = true, updatable = true, nullable=true)
	private UserRoleArea userRoleArea;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actos_id", insertable = true, updatable = true, nullable=true)
	private Actos actos;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public UserRoleArea getUserRoleArea() {
		return userRoleArea;
	}

	public void setUserRoleArea(UserRoleArea userRoleArea) {
		this.userRoleArea = userRoleArea;
	}

	public Actos getActos() {
		return actos;
	}

	public void setActos(Actos actos) {
		this.actos = actos;
	}

}
