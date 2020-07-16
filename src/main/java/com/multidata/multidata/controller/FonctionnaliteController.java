package com.multidata.multidata.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.multidata.multidata.models.DataBase;
import com.multidata.multidata.models.Fonctionalite;
import com.multidata.multidata.models.fonctionnaliterequest;
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
		//creation d'un dossier spécifique
		//new File("C:\\NeOXam")
		new File("C:\\Users\\ASUS\\Desktop\\fonctionnalés\\"+fonctionalite.getName()).mkdir();
		File rep = new File("C:\\Users\\ASUS\\Desktop\\fonctionnalés\\"+fonctionalite.getName());
	
		File fichier1 = new File("C:\\Users\\ASUS\\Desktop\\fonctionnalés\\"+fonctionalite.getFileActivation());
		File fichier2 = new File("C:\\Users\\ASUS\\Desktop\\fonctionnalés\\"+fonctionalite.getFilaDesactivation());
		File fichier3 = new File("C:\\Users\\ASUS\\Desktop\\fonctionnalés\\"+fonctionalite.getFileChek());
		fichier1.renameTo(new File (rep,fichier1.getName()));
		fichier2.renameTo(new File (rep,fichier2.getName()));
		fichier3.renameTo(new File (rep,fichier3.getName()));
		//uplaodFile(fichier1);
		
        
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
	    public ResponseEntity updatefonct(@RequestBody fonctionnaliterequest fc ) {

		Fonctionalite updated = Updatefonctionalite(fc);
		 
	        return new ResponseEntity<DataBase>(new HttpHeaders(), HttpStatus.OK);
	    }
	
	
	 public Fonctionalite Updatefonctionalite(fonctionnaliterequest entity)  
	    {
	        Optional<Fonctionalite> fonctionnalite = fonctionaliterepository.findById(entity.getId());
	        System.out.println("test la valeur de id database");
	        System.out.println(entity.getUrl());
	        Fonctionalite newEntity = null;
	        if(fonctionnalite.isPresent()) 
	        {
	        	 newEntity = fonctionnalite.get();
	        	 newEntity.setName(entity.getName());
	        	 newEntity.setDesscription(entity.getDesscription());
	        	 newEntity.setFileActivation(entity.getFileActivation());
	        	 newEntity.setFilaDesactivation(entity.getFilaDesactivation());
	        	 newEntity.setUrl(entity.getUrl());
	        	 newEntity.setFileChek(entity.getFileChek());
	        	 
		         newEntity =  fonctionaliterepository.save(newEntity);
	            
	             
	           
	        } 
	        return newEntity;
	    } 
	 
	 
	 
	 @GetMapping(path = "/findfoncById/{id}")
	    @ResponseBody
	    public ResponseEntity finddbById(@PathVariable long id) {

	        StringBuffer retBuf = new StringBuffer();

	        Optional<Fonctionalite> centreIntertAccount = fonctionaliterepository.findById(id);

	      
	        return ResponseEntity.ok().body(centreIntertAccount);
	    }
	 
	 @DeleteMapping(path = "/deletefonct/{id}")
	    @ResponseBody
	    public ResponseEntity deleteById(@PathVariable long id) {

	        StringBuffer retBuf = new StringBuffer();

	        fonctionaliterepository.deleteById(id);

	        retBuf.append("fonctionalite data has been deleted successfully.");

	        return ResponseEntity.ok().body(retBuf.toString()) ;
	    }

	 
	 
	
	
	/*
	 * public void uplaodFile( MultipartFile file) throws IOException {
	 * 
	 * System.out.println("Original file Byte Size - " + file.().length); ModelFile
	 * model = new ModelFile(file.getOriginalFilename(), file.getContentType(),
	 * compressZLib(file.getBytes())); fonctionaliterepository.save(model)
	 * 
	 * 
	 * }
	 */
	 
	 
	/*
	 * public ModelFile getfile( String fileName) throws IOException {
	 * 
	 * final Optional<ModelFile> retrievedfile =
	 * fonctionaliterepository.findByName(fileName); ModelFile file = new
	 * ModelFile(retrievedImage.get().getName(), retrievedFile.get().getType(),
	 * decompressZLib(retrievedFile.get().getPicByte())); return file; }
	 */
	 
}


