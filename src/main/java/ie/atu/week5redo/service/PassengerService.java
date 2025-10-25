package ie.atu.week5redo.service;

import ie.atu.week5redo.controller.errorHandling.DuplicateExceptionHandling;
import ie.atu.week5redo.controller.errorHandling.NotFoundHandling;
import ie.atu.week5redo.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final List<Passenger> passengerList = new ArrayList<>();

    // Creates Defensive Copy to Protect against Changes
    public List<Passenger> getPassengerList() {
        return new ArrayList<>(passengerList);
    }

    // Uses Optional to avoid Raw Null
    public Optional<Passenger> findById(String id) {
        for (Passenger p : passengerList) {
            if (p.getPassengerId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    // Uses Optional again to check if ID isPresent()
    public Passenger create(Passenger passenger) {
        if (findById(passenger.getPassengerId()).isPresent()) {
            throw new DuplicateExceptionHandling(" [WARNING] ***ID Already Exists***");
        }
        passengerList.add(passenger);
        return passenger;
    }

    public Passenger update(Passenger passenger) {
        for(Passenger updating : passengerList){
            if(updating.getPassengerId().equals(passenger.getPassengerId())){
                updating.setName(passenger.getName());
                updating.setEmail(passenger.getEmail());
                return updating;
            }
        }
    throw new NotFoundHandling(" [WARNING] ***ID Doesn't Exist*** ");
    }

    public Passenger delete(Passenger passenger){
        boolean removed = passengerList.removeIf(p-> p.getPassengerId().equals(passenger.getPassengerId()));
        if(!removed){
            throw new NotFoundHandling(" [WARNING] ***ID Doesnt Exist*** ");
        }
        return passenger;
    }

}