package com.ecom.webapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.webapp.entity.PersonEntity;
import com.ecom.webapp.response.PersonResponse;
import com.ecom.webapp.service.PersonService;

@RestController
@RequestMapping(path="/webapione")
public class PersonController {

	@Autowired
	PersonService personService;
	
//	@GetMapping("/person/{personId}")
//	public  Optional<PersonEntity> getPerson(@PathVariable int personId) {
//		 return personService.getPersonById(personId);
//	}
	
	@GetMapping("/person/{personId}")
	public  PersonResponse getPerson(@PathVariable int personId) {
		 return personService.getPersonById(personId);
	}
	
	@PostMapping("/person")
	public void addPerson(@RequestBody PersonEntity person) {
		personService.addPerson(person);
	}
	
	@GetMapping("/persons")
	public List<PersonEntity> getAllPerson(){
		return personService.getAllPerson();
	}
}
