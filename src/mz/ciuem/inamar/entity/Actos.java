package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="actos")

public class Actos extends IdEntity  {
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actos")
	private List<AreaPerfilActo> areaPerfilActos;
	
	private static final long serialVersionUID = -580835934094969154L;

	@Column(name="descricao_actos")
	private String descricaoActos;
	
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

}
