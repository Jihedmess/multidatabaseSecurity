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
	
	 @PutMapping(path = "/updatedb")
	    @ResponseBody
	    public ResponseEntity updateMp(@RequestBody DataBase db ) {

	        StringBuffer retBuf = new StringBuffer();

	        Optional<DataBase> newDb = databaserepository.findById(db.getId());

	        if (newDb != null) {
	           
	        	newDb.get().setName(db.getName());
	        	
	        	newDb.get().setPlatform(db.getPlatform());
	        	newDb.get().setUrl(db.getUrl());
	        	newDb.get().setUser(db.getUser());
	        	newDb.get().setPassword(db.getPassword());
	        	
	            	
	        	databaserepository.save(newDb.get());
	            
	        }

	        retBuf.append("database data update successfully.");

	        return ResponseEntity.ok().body(retBuf.toString()) ;
	    }
	 
	 
	 @DeleteMapping(path = "/deletedb/{id}")
	    @ResponseBody
	    public ResponseEntity deleteById(@PathVariable long id) {

	        StringBuffer retBuf = new StringBuffer();

	        databaserepository.deleteById(id);

	        retBuf.append("database data has been deleted successfully.");

	        return ResponseEntity.ok().body(retBuf.toString()) ;
	    }

}
