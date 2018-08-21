package com.exxeta.maau.demoliquibase.controller;

import com.exxeta.maau.demoliquibase.exception.RequestErrorException;
import com.exxeta.maau.demoliquibase.model.DailyProduction;
import com.exxeta.maau.demoliquibase.repository.DailyProductionRepository;
import com.exxeta.maau.demoliquibase.repository.FactoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dailyProd")
public class DailyProductionController {

    private DailyProductionRepository dailyProductionRepo;
    private FactoryRepository factoryRepository;


    public DailyProductionController(DailyProductionRepository dailyProductionRepository, FactoryRepository factoryRepository){
        this.dailyProductionRepo = dailyProductionRepository;
        this.factoryRepository = factoryRepository;
    }

    @RequestMapping(value = "{factoryId}/create", method = RequestMethod.POST)
    public DailyProduction createDailyProduction(@PathVariable(value = "factoryId") Long factoryId,
                                                 @RequestBody DailyProduction addDailyProdRequest){
        return factoryRepository.findById(factoryId)
                .map(factory -> {
                    addDailyProdRequest.setFactory(factory);
                    return dailyProductionRepo.save(addDailyProdRequest);
                }).orElseThrow(() -> new RequestErrorException("Factory with id " + factoryId + " not found."));

    }




    //RETRIEVE all daily Productions
    @RequestMapping(method = RequestMethod.GET)
    public List<DailyProduction> getDailyProductions(){
        return dailyProductionRepo.findAll();
    }


    //DELETE Car via dailyProdId
    @RequestMapping(value = "/delete/{dailyProdId}", method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable("dailyProdId") Long dailyProdId){
        dailyProductionRepo.deleteById(dailyProdId);
    }

    /*-----------
      CREATE CAR WITHOUT DAILY_PROD_ID AND SET DAILY_PROD_ID VIA UPDATE METHOD
      ----------------------- */

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public DailyProduction addDailyProd(@RequestBody DailyProduction addDailyProductionRequest) {
        DailyProduction dailyProd = new DailyProduction();
        dailyProd.setLocalDate(addDailyProductionRequest.getLocalDate());
        return dailyProductionRepo.save(dailyProd);
    }

    //UPDATE
    @RequestMapping(value = "/{dailyProdId}/setFactory/{factoryId}", method = RequestMethod.PUT)
    public void updateDailyProd(@PathVariable (value = "dailyProdId") Long dailyProdId,
                                @PathVariable (value = "factoryId") Long factoryId){
        List<DailyProduction> allDailyProductions =  dailyProductionRepo.findAll();
        DailyProduction selectedDp = new DailyProduction();
        for (DailyProduction dp: allDailyProductions){
            if (dp.getId().equals(dailyProdId)){
                selectedDp = dp;
            }
        }
        final DailyProduction dpToStore = selectedDp;
        if (!dpToStore.equals(null)){
            factoryRepository.findById(factoryId)
                    .map(factory -> {
                        dpToStore.setFactory(factory);
                        return dailyProductionRepo.save(dpToStore);
                    });
        }

    }
}
