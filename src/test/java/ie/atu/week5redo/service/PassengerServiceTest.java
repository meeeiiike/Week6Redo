package ie.atu.week5redo.service;

import ie.atu.week5redo.controller.errorHandling.DuplicateExceptionHandling;
import ie.atu.week5redo.controller.errorHandling.NotFoundHandling;
import ie.atu.week5redo.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerServiceTest {

    private PassengerService service;

    @BeforeEach
    void setup(){
        service = new PassengerService();
    }

    @Test
    void createThenFindById(){
        // Uses @Builder, new way of using Constructor
        Passenger passenger = Passenger.builder().passengerId("P001").name("meike").email("meike@gmail.com").build();
        service.create(passenger);
        // Test Cases
        Optional<Passenger> find = service.findById("P001");
        assertTrue(find.isPresent());
        assertEquals("meike", passenger.getName());
        assertEquals("meike@gmail.com", passenger.getEmail());
    }

    @Test
    void duplicateIdThrowsEx(){
        Passenger passenger = Passenger.builder().passengerId("P001").name("meike").email("meike@gmail.com").build();
        service.create(passenger);
        Optional<Passenger> find = service.findById("P001");
        assertThrows(DuplicateExceptionHandling.class, () ->
                service.create(Passenger.builder().passengerId("P001").name("meike").email("meike@gmail.com").build()));
    }
    @Test
    void updateSuccess(){
        Passenger passenger = Passenger.builder().passengerId("P001").name("meike").email("meike@gmail.com").build();
        service.create(passenger);

        Passenger updated = Passenger.builder().passengerId("P001").name("updatedMeike").email("updatedMeike@gmail.com").build();
        service.update(updated);
        // Test Cases
        Optional<Passenger> find = service.findById("P001");
        assertTrue(find.isPresent());
        assertEquals("updatedMeike", find.get().getName());
        assertEquals("updatedMeike@gmail.com", find.get().getEmail());
    }
    @Test
    void updateIdNotFound(){
        Passenger passenger = Passenger.builder().passengerId("P100").name("meike").email("meike@gmail.com").build();
        assertThrows(NotFoundHandling.class, () ->
                service.update(passenger));
    }

    @Test
    void deleteSuccess(){
        Passenger passenger = Passenger.builder().passengerId("P001").name("meike").email("meike@gmail.com").build();
        service.create(passenger);
        Optional<Passenger> find = service.findById("P001");
        assertTrue(find.isPresent());
        service.delete(passenger);
        Optional<Passenger> deleted = service.findById("P001");
        assertTrue(deleted.isEmpty());
    }
    @Test
    void deleteFailure(){
        Passenger notFound = Passenger.builder().passengerId("P100").name("meike").email("meike@gmail.com").build();
        assertThrows(NotFoundHandling.class, () -> service.delete(notFound));
    }

}