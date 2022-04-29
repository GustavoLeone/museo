package it.epicode.musei.dipinti;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository classe Dipinto
 * @author Georgiana Pacurar
 * 
 * 
 */

public interface DipintoRepository extends CrudRepository<Dipinto, Integer> {   //inserire l'Entity + l'ggetto nel caso dell'id un integer 
	
	/**
	 * Ricerca dipinto per id
	 * @param id
	 * @return id
	 */

}
