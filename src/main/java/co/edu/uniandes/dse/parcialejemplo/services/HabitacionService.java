package co.edu.uniandes.dse.parcialejemplo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HabitacionService {
	@Autowired
    HabitacionRepository habitacionRepository;
	
	
	/**
	 * Guardar una nueva habitacion
	 *
	 * @param habitacionEntity La entidad de tipo habitacion de la nueva habitacion a persistir.
	 * @return La entidad luego de persistirla
	 * @throws IllegalOperationException Si el Nombre o el Número de estrellas es inválido
	 */
    @Transactional
    public HabitacionEntity createHabitacion(HabitacionEntity habitacionEntity) throws IllegalOperationException {
            log.info("Inicia proceso de creación de la habitacion");
            
            if (!validateInteger(habitacionEntity.getNumeroHabitacion()))
                throw new IllegalOperationException("NumeroHabitacion no es valido");
            
            if (!validateInteger(habitacionEntity.getNumeroPersonas()))
                throw new IllegalOperationException("NumeroPersonas no es valido");
            
            if (!validateInteger(habitacionEntity.getNumeroCamas()))
                throw new IllegalOperationException("NumeroCamas no es valido");
            
            if (!validateInteger(habitacionEntity.getNumeroBanios()))
                throw new IllegalOperationException("NumeroBanios no es valido");
            
            if (!(habitacionEntity.getNumeroHabitacion() <= habitacionEntity.getNumeroCamas()))
                throw new IllegalOperationException("NumeroHabitacion debe ser <= a NumeroCamas.");
            
            log.info("Termina proceso de creación de la habitacion");

            return habitacionRepository.save(habitacionEntity);     
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
