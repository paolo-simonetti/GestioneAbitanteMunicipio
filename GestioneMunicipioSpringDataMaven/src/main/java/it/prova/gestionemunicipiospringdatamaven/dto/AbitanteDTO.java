package it.prova.gestionemunicipiospringdatamaven.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import it.prova.gestionemunicipiospringdatamaven.model.Abitante;


public class AbitanteDTO implements AbstractDTO<Abitante,AbitanteDTO> {

	private Long id;
	private String nome;
	private String cognome;
	private String eta;
	private String residenza;
	private String idMunicipio;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getEta() {
		return eta;
	}


	public void setEta(String eta) {
		this.eta = eta;
	}


	public String getResidenza() {
		return residenza;
	}


	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}


	public String getIdMunicipio() {
		return idMunicipio;
	}


	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public AbitanteDTO(String nome, String cognome, String eta, String residenza, String idMunicipio) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.residenza = residenza;
		this.idMunicipio = idMunicipio;
	}
	
	public AbitanteDTO() {
		super();
	}
	
	@Override
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

	@Override
	public List<String> errors () {
		List<String> result = new ArrayList<String>();
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo nome non può essere vuoto");
		if(StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non può essere vuoto");
		if(StringUtils.isBlank(this.eta)) {
			result.add("Il campo età non può essere vuoto");			
		} else {
			try {
				Integer.parseInt(this.eta);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Età inserita non valida");
			}
		}
		if(StringUtils.isBlank(this.residenza))
			result.add("Il campo residenza non può essere vuoto");
		if(StringUtils.isBlank(this.idMunicipio)) {
			result.add("Il campo municipio non può essere vuoto");			
		} else {
			try {
				Long.parseLong(this.idMunicipio);
				
			} catch(NumberFormatException e) {
				e.printStackTrace();
				result.add("Municipio inserito non valido");
			}
		}
		return result;
	}
	
	@Override
	public Abitante buildModelFromDTO(AbitanteDTO abitanteDTO) {
		Abitante result = new Abitante();
		result.setId(abitanteDTO.getId());
		result.setNome(abitanteDTO.getNome());
		result.setCognome(abitanteDTO.getCognome());
		result.setEta(Integer.parseInt(abitanteDTO.getEta()));
		result.setResidenza(abitanteDTO.getResidenza());		
		return result;
	}
	
	
	@Override
	public AbitanteDTO buildDTOFromModel(Abitante abitante) {
		AbitanteDTO result=new AbitanteDTO();
		result.setId(abitante.getId());
		result.setNome(abitante.getNome());
		result.setCognome(abitante.getCognome());
		result.setEta(abitante.getEta().toString());
		result.setResidenza(abitante.getResidenza());
		result.setIdMunicipio(abitante.getMunicipio().getId().toString());
		return result;
	}
	
}
