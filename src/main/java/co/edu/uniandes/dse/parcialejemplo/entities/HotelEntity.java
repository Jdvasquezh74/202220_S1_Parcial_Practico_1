package co.edu.uniandes.dse.parcialejemplo.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HotelEntity extends BaseEntity {
	
	private String nombre; 
	private String direccion; 
	private Integer numeroEstrellas;

}
