package it.prova.gestionemunicipiospringdatamaven.dto;

import java.util.List;

public interface AbstractDTO<S,T> {
	public List<String> errors();
	public String errorId(String id);
	public S buildModelFromDTO (T TInstance);
	public T buildDTOFromModel (S SInstance);
}
