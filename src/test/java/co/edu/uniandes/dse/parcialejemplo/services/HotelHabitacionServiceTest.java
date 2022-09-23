package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HotelHabitacionService.class)
class HotelHabitacionServiceTest {

    @Autowired
    private HotelHabitacionService hotelHabitacionService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    
    private List<HotelEntity> hotelList = new ArrayList<>();
    private List<HabitacionEntity> habitacionList = new ArrayList<>();
    
            /**
         * Configuración inicial de la prueba.
         */
    @BeforeEach
    void setUp() {
            clearData();
            insertData();
    }

    /**
         * Limpia las tablas que están implicadas en la prueba.
         */
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from HotelEntity");
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
            for (int i = 0; i < 3; i++) {
                HotelEntity hotelEntity = factory.manufacturePojo(HotelEntity.class);
                entityManager.persist(hotelEntity);
                hotelList.add(hotelEntity);
            	}
            
            for (int i = 0; i < 3; i++) {
                HabitacionEntity habitacionEntity = factory.manufacturePojo(HabitacionEntity.class);
                entityManager.persist(habitacionEntity);
                habitacionList.add(habitacionEntity);
            	}
    }

    /**
	 * Prueba para añadir una habitacion a un hotel
	 */
	@Test
	void testAddHabitacion() throws EntityNotFoundException, IllegalOperationException {
		HotelEntity hotel = hotelList.get(0);
		HabitacionEntity habitacion = habitacionList.get(0);
		HabitacionEntity result = hotelHabitacionService.addHabitacion(hotel.getId(), habitacion.getId());
		assertNotNull(result);
		assertEquals(habitacion.getNumeroHabitacion(),result.getNumeroHabitacion());
		assertEquals(habitacion.getNumeroPersonas(),result.getNumeroPersonas());
		assertEquals(habitacion.getNumeroBanios(),result.getNumeroBanios());
		assertEquals(habitacion.getNumeroCamas(),result.getNumeroCamas());
	}
	
	/**
	 * Prueba para añadir una habitacion
	 */
	
	@Test
	void testAddHabitacionInvalidHabitacion() {
		assertThrows(EntityNotFoundException.class, () -> {
			hotelHabitacionService.addHabitacion(hotelList.get(0).getId(), 0L);
		});
	}
	
	/**
	 * Prueba para añadir una habitacion
	 */
	
	@Test
	void testAddHabitacionInvalidHotel() {
		assertThrows(EntityNotFoundException.class, () -> {
			hotelHabitacionService.addHabitacion(0L, habitacionList.get(0).getId());
		});
	}

}
