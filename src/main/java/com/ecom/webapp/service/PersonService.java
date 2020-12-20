package com.ecom.webapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecom.webapp.entity.PersonEntity;
import com.ecom.webapp.repository.PersonRepository;
import com.ecom.webapp.response.PersonResponse;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepo;
	
	RestTemplate restTemplate = new RestTemplate();
	
	public void addPerson(PersonEntity person) {
		personRepo.save(person);
	}
	
//	public Optional<PersonEntity> getPersonById(int personId) {
//		return personRepo.findById(personId);
//	}
	
	/**
	 * Person with Hobby from web service two.
	 * @param personId
	 * @return
	 */
	public PersonResponse getPersonById(int personId) {
		// Fetch hobby from web service
		final String URL = "http://localhost:8082/webapitwo/hobby/person/{personId}";
		
		Map<String, Integer> reqParam = new HashMap<String, Integer>();
		reqParam.put("personId", personId);
		
		String hobby = restTemplate.getForObject(URL, String.class, reqParam);	
		
		// find person by id
		PersonEntity personEntity = personRepo.findById(personId).get();
		
		// make a person response object
		PersonResponse pres = new PersonResponse();
		pres.setPersonId(personEntity.getPersonId());
		pres.setAge(personEntity.getAge());
		pres.setName(personEntity.getName());
		
		// last set hobby
		pres.setHobby(hobby);
		
		return pres;
	}
	
	public List<PersonEntity> getAllPerson(){
		return personRepo.findAll();
	}
}

