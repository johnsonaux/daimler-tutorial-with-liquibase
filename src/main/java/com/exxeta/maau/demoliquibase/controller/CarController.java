package com.exxeta.maau.demoliquibase.controller;

import com.exxeta.maau.demoliquibase.exception.RequestErrorException;
import com.exxeta.maau.demoliquibase.model.Car;
import com.exxeta.maau.demoliquibase.repository.CarRepository;
import com.exxeta.maau.demoliquibase.repository.DailyProductionRepository;
import com.exxeta.maau.demoliquibase.repository.DealerRepository;
import com.exxeta.maau.demoliquibase.repository.FactoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private FactoryRepository factoryRepository;
    private DailyProductionRepository dailyProductionRepo;
    private CarRepository carRepository;
    private DealerRepository dealerRepository;


    public CarController (FactoryRepository factoryRepository, CarRepository carRepository,
                          DailyProductionRepository dailyProductionRepo, DealerRepository dealerRepository){
        this.factoryRepository = factoryRepository;
        this.carRepository = carRepository;
        this.dailyProductionRepo = dailyProductionRepo;
        this.dealerRepository = dealerRepository;
    }

    //CREATE new car and set dailyProd
    @RequestMapping(value = "/{dailyProdId}/create", method = RequestMethod.POST)
    public Car addCarToDailyProd(@PathVariable(value = "dailyProdId") Long dailyProdId,
                                 @RequestBody Car addCarRequest){

        return dailyProductionRepo.findById(dailyProdId)
                .map(dailyProduction -> {
                    if (!dailyProduction.getFactory().getAcceptedType().equals(addCarRequest.getType())){
                        throw new RequestErrorException("Car type not accepted at this Factory - seems like" +
                                "being the wrong Daily Production!");
                    } else {
                        addCarRequest.setDailyProduction(dailyProduction);
                    }
                    return carRepository.save(addCarRequest);
                }).orElseThrow(() -> new RequestErrorException("DailyProduction not found with id " + dailyProdId));
    }

    //RETRIEVE all cars
    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    //UPDATE car vehicleModel
    @RequestMapping(value = "/updateModel/{carId}", method = RequestMethod.PUT)
    public void updateCarModel(@PathVariable (value= "carId") Long carId,
                               @RequestBody Car addCarRequest){
        carRepository.findById(carId).map(car -> {
            car.setVehicleModel(addCarRequest.getVehicleModel());
            return carRepository.save(car);
        });
    }


    //DELETE Car via carId
    @RequestMapping(value = "/delete/{carId}", method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable("carId") Long carId){
        carRepository.deleteById(carId);
    }

   /*-----------
      CREATE CAR WITHOUT DAILY_PROD_ID AND SET DAILY_PROD_ID VIA UPDATE METHOD
      ----------------------- */

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void addCar(@RequestBody Car addCarRequest) {
        Car car = new Car();
        car.setType(addCarRequest.getType());
        car.setVehicleClass(addCarRequest.getVehicleClass());
        car.setVehicleModel(addCarRequest.getVehicleModel());
        carRepository.save(car);
    }

    //UPDATE
    @RequestMapping(value = "/{carId}/setDailyProd/{dailyProdId}", method = RequestMethod.PUT)
    public void updateCar(@PathVariable (value = "carId") Long carId,
                          @PathVariable (value = "dailyProdId") Long dailyProdId){


        final Car carToStore = selectCarById(carId);
        if (!carToStore.equals(null)){
            dailyProductionRepo.findById(dailyProdId)
                    .map(dailyProduction -> {
                        if (dailyProduction.getFactory() == null){
                            throw new RequestErrorException("Please first add DailyProduction to a Factory");
                        } else {
                            if (!dailyProduction.getFactory().getAcceptedType().equals(carToStore.getType())){
                                throw new RequestErrorException("Car Type not accepted in DailyProd´s Factory");
                            } else {
                                carToStore.setDailyProduction(dailyProduction);
                            }
                        }
                        return carRepository.save(carToStore);
                    });
        }

    }



    @RequestMapping(value = "/{carId}/toDealer/{dealerId}", method = RequestMethod.PUT)
    public void sellCarToDealer(@PathVariable (value = "carId") Long carId,
                                @PathVariable (value = "dealerId") Long dealerId){

        final Car updatedCar = selectCarById(carId);
        if (!updatedCar.equals(null)){
            dealerRepository.findById(dealerId)
                    .map(dealer -> {
                        updatedCar.setDealer(dealer);
                        return carRepository.save(updatedCar);
                    });
        }

    }


    protected Car selectCarById(Long carId) {
        List<Car> allCars =  carRepository.findAll();
        Car selectedCar = new Car();
        for (Car car: allCars){
            if (car.getId().equals(carId)){
                selectedCar = car;
            }
        }
        return selectedCar;
    }
}
