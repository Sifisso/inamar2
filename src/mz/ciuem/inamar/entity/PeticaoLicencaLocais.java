package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="peticao_licenca_locais")
public class PeticaoLicencaLocais extends IdEntity{
	
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "localPratica_id", insertable = true, updatable = true)
	private LocalPratica localPratica;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "peticaoLicenca_id", insertable = true, updatable = true)
	private PeticaoLicenca peticaoLicenca;
	
	@Column(name="isActivo")
	private boolean isActivo;

	public LocalPratica getLocalPratica() {
		return localPratica;
	}

	public void setLocalPratica(LocalPratica localPratica) {
		this.localPratica = localPratica;
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
