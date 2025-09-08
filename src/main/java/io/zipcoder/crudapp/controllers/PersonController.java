package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.zipcoder.crudapp.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonRepository repository;
    private List<Person> personList = new ArrayList<>();
   // private AtomicInteger idCounter = new AtomicInteger();
    @GetMapping()
    public ResponseEntity<Iterable<Person>> getPersonList(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        Person person = repository.findOne(id);
        if (person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> createPerson(@RequestBody Person p){
        return new ResponseEntity<>(repository.save(p), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Person> updatePerson(@RequestBody Person p){
        Person person = repository.findOne(p.getId());
        if (person == null ){
            return new ResponseEntity<>(repository.save(p), HttpStatus.CREATED);
        }
       return new ResponseEntity<>(repository.save(p), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable Long id){
        Person person = repository.findOne(id);
        return new ResponseEntity<>(person, HttpStatus.NO_CONTENT);
    }
}
