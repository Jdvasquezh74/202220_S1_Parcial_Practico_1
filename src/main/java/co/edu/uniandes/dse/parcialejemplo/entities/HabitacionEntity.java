package co.edu.uniandes.dse.parcialejemplo.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HabitacionEntity extends BaseEntity{
	
	private Integer numeroHabitacion; 
	private Integer numeroPersonas; 
	private Integer numeroCamas;
	private Integer numeroBanios;

}
