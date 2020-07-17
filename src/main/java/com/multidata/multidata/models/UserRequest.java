package com.multidata.multidata.models;

import java.util.HashSet;
import java.util.Set;

public class UserRequest {
	
	
	
	private Long id;
	 private String username;
	 

	   

	    private String email;
	    
	    private String role;
	    
	  
	   
	    private String password;
	  
	    public String getUsername() {
	        return username;
	    }
	 
	    public void setUsername(String username) {
	        this.username = username;
	    }
	 
	    public String getEmail() {
	        return email;
	    }
	 
	    public void setEmail(String email) {
	        this.email = email;
	    }
	 
	    public String getPassword() {
	        return password;
	    }
	 
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public String getRole() {
	      return this.role;
	    }
	    
	    public void setRole(String role) {
	      this.role = role;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
	    
	    
	    
}

