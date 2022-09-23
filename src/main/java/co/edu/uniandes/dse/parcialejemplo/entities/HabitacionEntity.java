package co.edu.uniandes.dse.parcialejemplo.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class HabitacionEntity extends BaseEntity{
	
	private Integer numeroHabitacion; 
	private Integer numeroPersonas; 
	private Integer numeroCamas;
	private Integer numeroBanios;
	
	@PodamExclude
    @ManyToOne()
    private HotelEntity hotel;

}
