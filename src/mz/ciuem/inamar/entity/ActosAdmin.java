package mz.ciuem.inamar.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="roleActos_Admin")
public class ActosAdmin  extends IdEntity{
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleActos_id", insertable = true, updatable = true)
	private RoleActos role_actos;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "peticao_id", insertable = true, updatable = true)
	private Peticao peticao;

	public RoleActos getRole_actos() {
		return role_actos;
	}

	public void setRole_actos(RoleActos role_actos) {
		this.role_actos = role_actos;
	}

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}
	
	
}
