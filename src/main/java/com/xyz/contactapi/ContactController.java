package com.xyz.contactapi;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.contactapi.dto.ContactDTO;
import com.xyz.contactapi.dto.entity.Contact;
import com.xyz.contactapi.dto.entity.ContactRepository;
import com.xyz.contactapi.service.ContactService;

@RestController
public class ContactController {

	Logger logger = LogManager.getLogger(ContactController.class);
	
    @Autowired
    ContactRepository repository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private ContactService service;

    @GetMapping(value = "/contact/{id}")
    public ResponseEntity<?> getContact(@PathVariable(name = "id") Long id)  {
    	logger.info("id - "+id);
        
    	Contact contact = service.getContactById(id);
        
        if(contact == null) {
        	logger.info("No contact found with id - "+id);
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ContactDTO>(modelMapper.map(contact, ContactDTO.class),HttpStatus.OK);
    }
    
    @GetMapping(value = "/contact")
    public ResponseEntity<?> contactByParams(HttpServletRequest req) {
    	
    	Contact contact = null;
    	T t= service.processFilters(req);
    	
    	
        if(contact == null) {
        	System.out.println("No contact found");
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ContactDTO>(modelMapper.map(contact, ContactDTO.class),HttpStatus.OK);
    }
    
   /* @GetMapping(value = "/contact")
    public ResponseEntity<?> contactByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber) {
    	logger.info("phoneNumber - "+phoneNumber);
        Contact contact = repository.findByPhoneNumber(phoneNumber);
        
        if(contact == null) {
        	System.out.println("No contact found with phoneNumber - "+phoneNumber);
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ContactDTO>(modelMapper.map(contact, ContactDTO.class),HttpStatus.OK);
    }*/

   

    @PostMapping(value ="/contact")
    public ResponseEntity<?> addContact(@RequestBody ContactDTO contactDTO) {
        logger.info(contactDTO);
        Contact contact = service.saveContact(contactDTO);

        if(contact == null) {
        	System.out.println("No contact found");
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ContactDTO>(modelMapper.map(contact, ContactDTO.class),HttpStatus.OK);
    }
    
    @PutMapping(value ="/contact/{id}")
    public ResponseEntity<?> updateContact(@PathVariable(name = "id") Long id, @RequestBody ContactDTO contactDTO) {
    	Contact updatedContact = null;
    	logger.info("id -"+id);
        
    	updatedContact = service.updateContact(id,contactDTO);
    	
    	if(updatedContact == null) {
        	System.out.println("No contact found");
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ContactDTO>(modelMapper.map(updatedContact, ContactDTO.class),HttpStatus.OK);
    }
    
    @DeleteMapping(value ="/contact/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable(name = "id") Long id) {
    	logger.info("id - "+id);
        return service.deleteContact(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    
    /*Condition<?, ?> isNotZero = new Condition<ContactDTO, Contact>() {
        public boolean applies(MappingContext<ContactDTO, Contact> context) {
          return context.getSource().getId() != null || context.getSource().getId() != 0;
        }
      };
      
	public void shouldMapConditionally() {
    	    modelMapper.addMappings(new PropertyMap<ContactDTO, Contact>() {
    	      protected void configure() {
    	        when(isNotZero).map(source).setId(null);
    	      }
    	    });
	}*/
}
