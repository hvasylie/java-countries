package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/age")
public class AgeController {

//    localhost:2019/age/age/{number}

    @GetMapping(value = ("/age/{number}"), produces = "application/json")
    ResponseEntity<?> getByAge(@PathVariable int number) {
        ArrayList<Country> tempList = new ArrayList<>();
        Country.getCountryList().forEach(e -> {if(e.getMedianAge() >= number) tempList.add(e);});
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

//    localhost:2019/age/min

    @GetMapping(value = "/min", produces = "application/json")
    ResponseEntity<?> getMinMedianAge() {
        ArrayList<Country> tempList = Country.getCountryList();
        tempList.sort((e1, e2) -> (e1.getMedianAge() - e2.getMedianAge()));
        return new ResponseEntity<>(tempList.get(0), HttpStatus.OK);
    }

//    localhost:2019/age/max

    @GetMapping(value = "/max", produces = "application/json")
    ResponseEntity<?> getMaxMedianAge() {
        ArrayList<Country> tempList = Country.getCountryList();
        tempList.sort((e1, e2) -> (e2.getMedianAge() - e1.getMedianAge()));
        return new ResponseEntity<>(tempList.get(0), HttpStatus.OK);
    }

//    *** STRETCH GOALS ***

//    localhost:2019/age/median

    @GetMapping(value = "/median", produces = "application/json")
    ResponseEntity<?> getMedianAge() {
        ArrayList<Country> tempList = Country.getCountryList();
        tempList.sort((e1, e2) -> e1.getMedianAge() - e2.getMedianAge());
        return new ResponseEntity<>(tempList.get((tempList.size() - 1) / 2 + 1), HttpStatus.OK);
    }

}
