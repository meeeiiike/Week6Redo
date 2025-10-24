package ie.atu.week5redo.service;

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
    public Optional<Passenger> findById(String id){
        for(Passenger p : passengerList){
            if(p.getPassengerId().equals(id)){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    // Uses Optional again to check if ID isPresent()
    public Passenger create(Passenger p){
        if(findById(p.getPassengerId()).isPresent()){
            throw new IllegalArgumentException("[WARNING] ***ID Already Exists***");
        }
        passengerList.add(p);
        return p;
    }
}
