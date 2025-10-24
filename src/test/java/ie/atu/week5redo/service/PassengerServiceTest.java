package ie.atu.week5redo.service;

import ie.atu.week5redo.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
        assertThrows(IllegalArgumentException.class, () ->
                service.create(Passenger.builder().passengerId("P001").name("meike").email("meike@gmail.com").build()));
    }
}
