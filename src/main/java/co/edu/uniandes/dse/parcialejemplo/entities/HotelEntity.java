package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class HotelEntity extends BaseEntity {
	
	private String nombre; 
	private String direccion; 
	private Integer numeroEstrellas;
	
	@PodamExclude
	@OneToMany(mappedBy="hotel", fetch = FetchType.LAZY)
	private List<HabitacionEntity> habitaciones = new ArrayList<HabitacionEntity>(); 

}
