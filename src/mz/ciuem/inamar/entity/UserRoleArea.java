package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_role_area")
public class UserRoleArea extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userRole_id", insertable = true, updatable = true)
	private UserRole userRole;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "area_id", insertable = true, updatable = true)
	private Area area;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRoleArea")
	private List<AreaPerfilActo> areaPerfilActos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRoleArea")
	private List<UserRoleAreaDestino> userRoleAreaDestino;
	
	private String descricao;


	public UserRole getUserRole() {
		return userRole;
	}


	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}


	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
