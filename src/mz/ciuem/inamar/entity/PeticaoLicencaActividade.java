package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="peticao_licenca_actividade")
public class PeticaoLicencaActividade extends IdEntity{
	
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actividadeLicenca_id", insertable = true, updatable = true)
	private ActividadeLicenca actividadeLicenca;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "peticaoLicenca_id", insertable = true, updatable = true)
	private PeticaoLicenca peticaoLicenca;
	
	@Column(name="isActivo")
	private boolean isActivo;

	public ActividadeLicenca getActividadeLicenca() {
		return actividadeLicenca;
	}

	public void setActividadeLicenca(ActividadeLicenca actividadeLicenca) {
		this.actividadeLicenca = actividadeLicenca;
	}

	public PeticaoLicenca getPeticaoLicenca() {
		return peticaoLicenca;
	}

	public void setPeticaoLicenca(PeticaoLicenca peticaoLicenca) {
		this.peticaoLicenca = peticaoLicenca;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}
}
