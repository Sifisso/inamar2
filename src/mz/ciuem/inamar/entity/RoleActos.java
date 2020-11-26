package mz.ciuem.inamar.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="role_actos")
public class RoleActos extends IdEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "actos_id", insertable = true, updatable = true)
	private Actos actos;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_role_area_id", insertable = true, updatable = true)
	private UserRoleArea userRoleArea;

	public Actos getActos() {
		return actos;
	}

	public void setActos(Actos actos) {
		this.actos = actos;
	}

	public UserRoleArea getUserRoleArea() {
		return userRoleArea;
	}

	public void setUserRoleArea(UserRoleArea userRoleArea) {
		this.userRoleArea = userRoleArea;
	}
	
	
}
