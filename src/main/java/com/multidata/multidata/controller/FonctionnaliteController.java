package com.multidata.multidata.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.multidata.multidata.models.Fonctionalite;
import com.multidata.multidata.repository.FonctionnaliteRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FonctionnaliteController {
	
	@Autowired
	FonctionnaliteRepository fonctionaliterepository;
	
	
	@PostMapping(path = "/addfonct")
    @ResponseBody
    public ResponseEntity addDepence(@RequestBody Fonctionalite fonctionalite) {
        
         Object a =  fonctionaliterepository.save(fonctionalite);
        
        return ResponseEntity.ok().body(a);

    }
	
	
	@GetMapping(path = "/findAllfonct")
    @ResponseBody
    public ResponseEntity findAllMP() {

        StringBuffer retBuf = new StringBuffer();

        List<Fonctionalite> fonctionaliteListe = (List<Fonctionalite>) fonctionaliterepository.findAll();
         return ResponseEntity.ok().body(fonctionaliteListe);
       
    }
	
	 @PutMapping(path = "/updatefonct")
	    @ResponseBody
	    public ResponseEntity updateMp(@RequestBody Fonctionalite fc ) {

	        StringBuffer retBuf = new StringBuffer();

	        Optional<Fonctionalite> newfonct = fonctionaliterepository.findById(fc.getId());

	        if (newfonct != null) {
	           
	        	newfonct.get().setName(fc.getName());
	        	newfonct.get().setDesscription(fc.getDesscription());
	        	newfonct.get().setFileActivation(fc.getFileActivation());
	        	newfonct.get().setFilaDesactivation(fc.getFilaDesactivation());
	        	newfonct.get().setFileChek(fc.getFileChek());
	        	
	        	
	            	
	        	fonctionaliterepository.save(newfonct.get());
	            
	        }

	        retBuf.append("fonctionalite data update successfully.");

	        return ResponseEntity.ok().body(retBuf.toString()) ;
	    }
	 
	 
	 @DeleteMapping(path = "/deletefonct/{id}")
	    @ResponseBody
	    public ResponseEntity deleteById(@PathVariable long id) {

	        StringBuffer retBuf = new StringBuffer();

	        fonctionaliterepository.deleteById(id);

	        retBuf.append("fonctionalite data has been deleted successfully.");

	        return ResponseEntity.ok().body(retBuf.toString()) ;
	    }

}