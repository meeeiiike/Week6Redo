package ie.atu.week5redo.controller;

import ie.atu.week5redo.controller.errorHandling.NotFoundHandling;
import ie.atu.week5redo.model.Passenger;
import ie.atu.week5redo.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    // Constructor based Dependency Injection!
    private final PassengerService service;
    public PassengerController(PassengerService service) {
        this.service = service;
    }

    // Show List of Passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getPassengerList(){
        return ResponseEntity.ok(service.getPassengerList());
    }

    // Find by ID using Optionals '.get()' method
    @GetMapping("/{id}")
    public ResponseEntity<Passenger> findById(@PathVariable String id){
        Optional<Passenger> find = service.findById(id);
        if(find.isEmpty()){
            throw new NotFoundHandling(" [WARNING] ***ID Doesnt Exist*** ");
        }
        else{
            return ResponseEntity.ok(find.get());
        }
    }

    // Create Single Passenger Returning 201
    @PostMapping
    public ResponseEntity<Passenger> create(@Valid @RequestBody Passenger passenger){
        Passenger created = service.create(passenger);
        return ResponseEntity.created(URI.create("/api/passengers/" + created.getPassengerId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> update(@Valid @RequestBody Passenger passenger, @PathVariable String id){
        Optional<Passenger> find = service.findById(id);
        if(find.isEmpty()){
            throw new NotFoundHandling(" [WARNING] ***ID Doesnt Exist*** ");
        }
        else{
            passenger.setPassengerId(id); // prevents 5xx code errors
            Passenger updated = service.update(passenger);
            return ResponseEntity.ok(updated);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Passenger> delete(@PathVariable String id) {
        Optional<Passenger> find = service.findById(id);
        if (find.isEmpty()) {
            throw new NotFoundHandling(" [WARNING] ***ID Doesnt Exist*** ");
        } else {
            Passenger deleted = service.delete(find.get());
            return ResponseEntity.ok(deleted);
        }
    }

}