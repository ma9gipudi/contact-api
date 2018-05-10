package com.xyz.contactapi;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xyz.contactapi.dto.ContactDTO;
import com.xyz.contactapi.dto.entity.Contact;
import com.xyz.contactapi.service.ContactService;

@RestController
public class ContactController {

	Logger logger = LogManager.getLogger(ContactController.class);
	
    
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
    	
    	List<Contact> contactLst= service.processFilters(req);
    	
    	
        if(contactLst == null || contactLst.size() ==0) {
        	System.out.println("No contact found");
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return contactLst.size() > 1 ? new ResponseEntity<List<Contact>>(contactLst,HttpStatus.OK)
        		: new ResponseEntity<ContactDTO>(modelMapper.map(contactLst.get(0), ContactDTO.class),HttpStatus.OK);
    }
       

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
    
    @PutMapping(value ="/contact/{id}/image")
    public ResponseEntity<?> updateContact(@PathVariable(name = "id") Long id, @RequestBody MultipartFile image,
    		HttpServletRequest req) throws IOException {
    	Contact updatedContact = null;
    	logger.info("id -"+id);
    	if(image == null) {
    		logger.info("Image is null");
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
        
    	updatedContact = service.getContactById(id);
    	updatedContact.setImage(image.getBytes());
    	service.saveContact(modelMapper.map(updatedContact, ContactDTO.class));
    	
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
    
}
