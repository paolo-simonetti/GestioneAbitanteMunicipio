package it.prova.gestionemunicipiospringdatamaven.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Municipio  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descrizione;
	private String codice;
	private String ubicazione;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "municipio", orphanRemoval = true)
	private Set<Abitante> abitanti = new HashSet<>();

	public Municipio() {
	}

	public Municipio(String descrizione, String codice, String ubicazione) {
		super();
		this.descrizione = descrizione;
		this.codice = codice;
		this.ubicazione = ubicazione;
	}

	public Set<Abitante> getAbitanti() {
		return abitanti;
	}

	public void setAbitanti(Set<Abitante> abitanti) {
		this.abitanti = abitanti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getUbicazione() {
		return ubicazione;
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Municipio other = (Municipio) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (ubicazione == null) {
			if (other.ubicazione != null)
				return false;
		} else if (!ubicazione.equals(other.ubicazione))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Municipio [id=" + id + ", descrizione=" + descrizione + ", codice=" + codice + ", ubicazione="
				+ ubicazione + "]";
	}

	
	
}
