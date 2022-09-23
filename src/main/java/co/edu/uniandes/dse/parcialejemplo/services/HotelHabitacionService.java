package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelHabitacionService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private HabitacionRepository habitacionRepository;
	
	/**
	 * Asocia una Habitacion existente a un Hotel
	 *
	 * @param hotelId   Identificador de la instancia de Hotel
	 * @param habitacionId Identificador de la instancia de Habitacion
	 * @return Instancia de HabitacionEntity que fue asociada a Hotel
	 */
	@Transactional
	public HabitacionEntity addHabitacion(Long habitacionId, Long hotelId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle una parte a la caracteristica con id = {0}", hotelId);
		Optional<HabitacionEntity> habitacionEntity = habitacionRepository.findById(habitacionId);
		if (habitacionEntity.isEmpty())
			throw new EntityNotFoundException("Habitacion not found");

		Optional<HotelEntity> hotelEntity = hotelRepository.findById(hotelId);
		if (hotelEntity.isEmpty())
			throw new EntityNotFoundException("Hotel not found");

		hotelEntity.get().getPartes().add(parteEntity.get());
		log.info("Termina proceso de asociarle una parte a la caracteristica con id = {0}", caracteristicaId);
		return parteEntity.get();
	}
}
