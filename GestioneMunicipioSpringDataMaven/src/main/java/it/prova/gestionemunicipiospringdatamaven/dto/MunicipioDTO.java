package it.prova.gestionemunicipiospringdatamaven.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionemunicipiospringdatamaven.model.Municipio;


public class MunicipioDTO {

	private Long id;
	private String descrizione;
	private String codice;
	private String ubicazione;

	public MunicipioDTO() {
	}

	public MunicipioDTO(String descrizione, String codice, String ubicazione) {
		this.descrizione = descrizione;
		this.codice = codice;
		this.ubicazione = ubicazione;
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
	
	public String errorId(String id) {
		String result=null;
		if(StringUtils.isBlank(id)) {
			result="Il campo id non può essere vuoto";
		}  else {
			try {
				this.id=Long.parseLong(id);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result ="Id inserito non valido";
			}
		}
		return result;
	}

	
	public List<String> errors(){
		List<String> result = new ArrayList<String>();
		if(StringUtils.isBlank(this.descrizione))
			result.add("Il campo descrizione non può essere vuoto");
		if(StringUtils.isBlank(this.codice))
			result.add("Il campo codice non può essere vuoto");
		if(StringUtils.isBlank(this.ubicazione))
			result.add("Il campo ubicazione non può essere vuoto");
		
		return result;
	}

	public static Municipio buildModelFromDto(MunicipioDTO municipioDTO) {
		Municipio result = new Municipio();
		
		result.setId(municipioDTO.getId());
		result.setDescrizione(municipioDTO.getDescrizione());
		result.setCodice(municipioDTO.getCodice());
		result.setUbicazione(municipioDTO.getUbicazione());
		return result;
	}
	
	public static MunicipioDTO buildDTOFromModel(Municipio municipio) {
		MunicipioDTO result=new MunicipioDTO();
		result.setId(municipio.getId());
		result.setDescrizione(municipio.getDescrizione());
		result.setCodice(municipio.getCodice());
		result.setUbicazione(municipio.getUbicazione());
		return result;
	}

}
