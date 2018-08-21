package com.exxeta.maau.demoliquibase.controller;


import com.exxeta.maau.demoliquibase.model.Car;
import com.exxeta.maau.demoliquibase.model.Dealer;
import com.exxeta.maau.demoliquibase.repository.CarRepository;
import com.exxeta.maau.demoliquibase.repository.DealerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dealer")
public class DealerController {

    private DealerRepository dealerRepository;
    private CarRepository carRepository;

    public DealerController (DealerRepository dealerRepository, CarRepository carRepo){
        this.dealerRepository = dealerRepository;
        this.carRepository = carRepo;
    }

    //CREATE
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Dealer createDealer(@RequestBody Dealer addDealerRequest){
        Dealer dealer = new Dealer();
        dealer.setName(addDealerRequest.getName());
        return dealerRepository.save(dealer);
    }

    //RETRIEVE
    @RequestMapping(method = RequestMethod.GET)
    public List<Dealer> getAllDealer(){
        return dealerRepository.findAll();
    }


    @RequestMapping(value = "/{dealerId}/getCars")
    public List<Car> getAllCars(@PathVariable Long dealerId){
        return carRepository.findByDealerId(dealerId);

    }


    //UPDATE


    //DELETE

    @RequestMapping(value = "/delete/{dealerId}", method = RequestMethod.DELETE)
    public void deleteDealer(@PathVariable (value = "dealerId") Long dealerId){
        dealerRepository.deleteById(dealerId);

    }
}
