package co.edu.uniandes.dse.parcialejemplo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelService {
	@Autowired
    HotelRepository hotelRepository;
	
	
	/**
	 * Guardar un nuevo hotel
	 *
	 * @param hotelEntity La entidad de tipo hotel del nuevo hotel a persistir.
	 * @return La entidad luego de persistirla
	 * @throws IllegalOperationException Si el Nombre o el Número de estrellas es inválido
	 */
    @Transactional
    public HotelEntity createHoteles(HotelEntity hotelEntity) throws IllegalOperationException {
            log.info("Inicia proceso de creación del hotel");
            
            if (!validateInteger(hotelEntity.getNumeroEstrellas()))
                throw new IllegalOperationException("NumeroEstrellas no es valido");
            
            if (!validateString(hotelEntity.getNombre()))
                    throw new IllegalOperationException("Nombre no es valido");
            
            if (!hotelRepository.findByNombre(hotelEntity.getNombre()).isEmpty())
                throw new IllegalOperationException("Ya existe un hotel con el Nombre indicado.");
            
            if (!(hotelEntity.getNumeroEstrellas() >= 1 && hotelEntity.getNumeroEstrellas() <= 5))
                throw new IllegalOperationException("NumeroEstrellas debe ser un valor entre 1 y 5.");
            
            log.info("Termina proceso de creación del hotel");

            return hotelRepository.save(hotelEntity);     
    }
    
    
    /**
	 * Verifica que un String no sea inválido.
	 *
	 * @param String a verificar
	 * @return true si el String es válido.
	 */
	private boolean validateString(String string){
        return !(string==null || string.isEmpty());
    }
	
	
	/**
	 * Verifica que un Integer no sea inválido.
	 *
	 * @param Valor Integer a verificar
	 * @return true si el Integer es válido.
	 */
	private boolean validateInteger(Integer num){
		if (!(num==null))
		{return !(num < 0);}
		else {return !(num==null);}
    }
}
