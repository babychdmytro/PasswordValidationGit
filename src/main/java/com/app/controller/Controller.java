package com.app.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Password;
import com.app.service.Service;


@RestController
public class Controller {

	@Autowired
	Service service;
	
    @RequestMapping(value="/validate", method=RequestMethod.POST, produces="application/json",  consumes = "application/json")
	public Set<String> validatePassword(@RequestBody Password pwd) {
    	
    	return service.validatePassword(pwd.getPassword());
    	
	}
	
}
