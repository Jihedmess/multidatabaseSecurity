package com.multidata.multidata.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.multidata.multidata.models.DataBase;
import com.multidata.multidata.models.databaseRequest;
import com.multidata.multidata.repository.DatabaseRepository;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.multidata.multidata.models.DataBase;
import com.multidata.multidata.repository.DatabaseRepository;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DataBaseController {
	@Autowired
	DatabaseRepository databaserepository;
	
	
	@PostMapping(path = "/adddb")
    @ResponseBody
    public ResponseEntity addDepence(@RequestBody DataBase database) {

    	DataBase newDataBse = new DataBase();
    	newDataBse.setPlatform(database.getPlatform());
    	newDataBse.setUrl(database.getUrl());
    	newDataBse.setUser(database.getUser());
    	newDataBse.setPassword(database.getPassword());
    	newDataBse.setName(database.getName());
        
         Object a =  databaserepository.save(newDataBse);

        
         
        return ResponseEntity.ok().body(a);

    }
	
	
	@GetMapping(path = "/findAlldb")
    @ResponseBody
    public ResponseEntity findAllMP() {

        StringBuffer retBuf = new StringBuffer();

        List<DataBase> centreInteretAccountList = (List<DataBase>) databaserepository.findAll();
         return ResponseEntity.ok().body(centreInteretAccountList);
       
    }
	@GetMapping(path = "/finddbById/{id}")
    @ResponseBody
    public ResponseEntity finddbById(@PathVariable long id) {

        StringBuffer retBuf = new StringBuffer();

        Optional<DataBase> centreIntertAccount = databaserepository.findById(id);

      
        return ResponseEntity.ok().body(centreIntertAccount);
    }
	 
	   
	 @PutMapping(path = "/updatedb")
	    public ResponseEntity<DataBase> createOrUpdate(@RequestBody databaseRequest employee){
	    System.out.println("test la valeur de id database");
        System.out.println(employee.getUrl());
                                          
		 DataBase updated = createOrUpdateDatabase(employee);
		 
	        return new ResponseEntity<DataBase>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	 
	 public DataBase createOrUpdateDatabase(databaseRequest entity)  
	    {
	        Optional<DataBase> database = databaserepository.findById(entity.getId());
	        System.out.println("test la valeur de id database");
	        System.out.println(entity.getUrl());
	        DataBase newEntity = null;
	        if(database.isPresent()) 
	        {
	        	 newEntity = database.get();
	        	    newEntity.setPlatform(entity.getPlatform());
		            newEntity.setUrl(entity.getUrl());
		            newEntity.setUser(entity.getUser());
		            newEntity.setPassword(entity.getPassword());
		            newEntity.setName(entity.getName());
		            newEntity =  databaserepository.save(newEntity);
	            
	             
	           
	        } 
	        return newEntity;
	    } 
	 
	 
	 @DeleteMapping(path = "/deletedb/{id}")
	    @ResponseBody
	    public ResponseEntity deleteById(@PathVariable long id) {

	        StringBuffer retBuf = new StringBuffer();

	        databaserepository.deleteById(id);

	        retBuf.append("database data has been deleted successfully.");

	        return ResponseEntity.ok().body(retBuf.toString()) ;
	    }
	 
	 
	 
	 /*
	  *  newEntity.setPlatform(db.getPlatform());
	            newEntity.setUrl(db.getUrl());
	            newEntity.setUser(db.getUser());
	            newEntity.setPassword(db.getPassword());
	            databaserepository.save(newEntity);
	  */          
	  

}
