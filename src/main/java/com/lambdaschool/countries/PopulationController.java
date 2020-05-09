package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/population")
public class PopulationController {

//    localhost:2019/population/size/1000000000

    @GetMapping(value = "/size/{number}", produces = "application/json")
    ResponseEntity<?> getCountriesWithPopulationLargerThan(@PathVariable int number) {
        ArrayList<Country> tempList = new ArrayList<>();
        Country.getCountryList().forEach(e -> {if(e.getPopulation() >= number) tempList.add(e); });
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

//    localhost:2019/population/min

    @GetMapping(value = "/min", produces = "application/json")
    ResponseEntity<?> getMinPopulation() {
        ArrayList<Country> tempList = Country.getCountryList();
        tempList.sort((e1, e2) -> (int) (e1.getPopulation() - e2.getPopulation()));
        return new ResponseEntity<>(tempList.get(0), HttpStatus.OK);
    }

//    localhost:2019/population/min

    @GetMapping(value = "/max", produces = "application/json")
    ResponseEntity<?> getMaxPopulation() {
        ArrayList<Country> tempList = Country.getCountryList();
        tempList.sort((e1, e2) -> (int) (e2.getPopulation() - e1.getPopulation()));
        return new ResponseEntity<>(tempList.get(0), HttpStatus.OK);
    }

//    *** STRETCH GOALS ***

//    localhost:2019/population/median

    @GetMapping(value = "/median", produces = "application/json")
    ResponseEntity<?> getMedianAge() {
        ArrayList<Country> tempList = Country.getCountryList();
        tempList.sort((e1, e2) -> (int) (e1.getPopulation() - e2.getPopulation()));
        return new ResponseEntity<>(tempList.get((tempList.size() - 1) / 2 + 1), HttpStatus.OK);
    }
}
