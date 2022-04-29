package it.epicode.musei.dipinti;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//okokokosd

/**
 * Servizi rest relativi alla classe Dipinto
 * @author Georgiana Pacurar
 * 
 */
@RestController
@RequestMapping ("/dipinti")
@Tag(name = "dipinto rest servicies", description = "implementazioni delle api rest dei dipinti")
public class DipintoController {

	@Autowired
	DipintoRepository dr;
	
	/**
	 * Inserimento a database di un dipinto
	 * Associato al metodo Post
	 * @param d
	 * @return
	 */
	@Operation (summary = "Inserisce un dipinto nel db", description = "Inserisce un dipinto nel db con nome autore e anno dipinto")
	@ApiResponse (responseCode = "200" , description = "Dipinto inserito con successo nel db")
	@ApiResponse (responseCode = "500" , description = "Errore")
	
	@PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity inserisciDipinto (@Valid @RequestBody Dipinto d, BindingResult errori) { 
		if(errori.hasErrors()) {
			//errori.getAllErrors().stream().map(e-> e.getDefaultMessage()); LAMBDA
			List<String> descrizioneDiErrore = new ArrayList<String>();
			for(ObjectError e : errori.getAllErrors()) {
			    descrizioneDiErrore.add(e.getDefaultMessage());
				}
			return new ResponseEntity(descrizioneDiErrore , HttpStatus.BAD_REQUEST);
			
			
		}
		dr.save(d);
		return ResponseEntity.ok("Dipinto Inserito"); 
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity mostraTuttiDipinti ()  {
		return ResponseEntity.ok(dr.findAll());
	}
	
	
	@DeleteMapping("/{id}")
    public ResponseEntity cancellaCalciatore(@PathVariable ("id") int id ) {
		if(dr.existsById(id)) {
		dr.deleteById(id);
		return ResponseEntity.ok("Dipinto eliminato");}
		else {
			return  new ResponseEntity("Dipinto non trovato ", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity caricaDipinto (@PathVariable int id) {
		return ResponseEntity.ok(dr.findById(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity cercDipintoPerId (@PathVariable int id) {
		try {
			return ResponseEntity.ok(dr.findById(id).orElseThrow());
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return new ResponseEntity("dipindo non trovato", HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping(produces = MediaType.TEXT_PLAIN_VALUE, path = "/modificadipinto/{id}")
	public ResponseEntity modificaDipinto (@Valid @PathVariable("id") int id, @RequestBody Dipinto d, BindingResult errori) { //con RequestBody serve per convertire il Json e restituirlo ogni volta che c'è un oggetto in ingresso
		if (errori.hasErrors()) {
			List<String> descrizioneDiErrore = new ArrayList<String>();
			for(ObjectError e : errori.getAllErrors()) {
				descrizioneDiErrore.add(e.getDefaultMessage());
			}
			return new ResponseEntity(descrizioneDiErrore , HttpStatus.BAD_REQUEST);
		}
		if (dr.existsById(id)) {
			dr.save(d);
			return ResponseEntity.ok("Dipinto modificato");
		}
		else {
	
		return   new ResponseEntity("dipindo non esistente", HttpStatus.NOT_FOUND);
	}
}
}