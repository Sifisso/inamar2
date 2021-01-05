package mz.ciuem.inamar.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="peticao_destino")
public class PeticaoDestino extends IdEntity {

	

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "peticao_id", insertable = true, updatable = true)
	private Peticao peticao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userRoleAreaDestino_id", insertable = true, updatable = true, nullable=true)
	private UserRoleAreaDestino userRoleAreaDestino;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userRole_id", insertable = true, updatable = true)
	private UserRole userRole;

	


	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserRoleAreaDestino getUserRoleAreaDestino() {
		return userRoleAreaDestino;
	}

	public void setUserRoleAreaDestino(UserRoleAreaDestino userRoleAreaDestino) {
		this.userRoleAreaDestino = userRoleAreaDestino;
	}

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}


	
	


}
