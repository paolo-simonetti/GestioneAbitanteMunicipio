package it.prova.gestionemunicipiospringdatamaven.repository.municipio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionemunicipiospringdatamaven.model.Municipio;

public interface MunicipioRepository extends CrudRepository<Municipio, Long>,QueryByExampleExecutor <Municipio> {
	
	@Query("from Municipio m where m.descrizione like %?1%")
	List<Municipio> findAllByDescrizioneContaining(String term);
	
	@Query("from Municipio m where m.ubicazione like ?1%")
	List<Municipio> findAllByUbicazioneStartingWith(String term);
	
	List<Municipio> findAllByCodiceAndUbicazione(String codice, String ubicazione);
	List<Municipio> findAllByCodiceOrderByUbicazioneDesc(String codice);
	
	List<Municipio> findAllByAbitanti_CognomeLike(String cognome);
	
}
