package it.epicode.musei.dipinti;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Dipinto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	@NotBlank(message = "Il nome del calciatore è obbligatorio") //non deve essere vuoto
	@Size(min = 5, max = 30, message = "deve essere di min 5 carattere e max 30 caratteri")
	private String nome;
	@Min(value = 1000 , message = "Il dipinto deve essere min dell'anno 1000")
	@Max(value = 2022, message = "Il dipinto deve essere max dell'anno 2022")
	private int anno;
 
    

	
}
