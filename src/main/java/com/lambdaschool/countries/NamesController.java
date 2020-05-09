package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/name")
public class NamesController {

//    localhost:2019/name/all
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> allNames() {
        Country.getCountryList().sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        return new ResponseEntity<>(Country.getCountryList(), HttpStatus.OK);
    }

//    localhost:2019/name/start/u

    @GetMapping(value = "/start/{letter}", produces = "application/json")
    public  ResponseEntity<?> namesThatStartWith(@PathVariable char letter) {
        ArrayList<Country> tempList = new ArrayList<Country>();
        Country.getCountryList().forEach(e -> {if (e.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter))
            tempList.add(e);});
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

//    localhost:2019/name/size/20

    @GetMapping(value = "/size/{wordLenght}", produces = "application/json")
    public ResponseEntity<?> wordsLargerThan(@PathVariable int wordLenght) {
        ArrayList<Country> tempList = new ArrayList<>();
        Country.getCountryList().forEach(e -> {if(e.getName().length() >= wordLenght) tempList.add(e);});
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

}
