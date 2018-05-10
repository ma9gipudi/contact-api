package com.xyz.contactapi.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.xyz.contactapi.dto.ContactDTO;
import com.xyz.contactapi.dto.entity.Contact;
import com.xyz.contactapi.dto.entity.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	Logger logger = LogManager.getLogger(ContactServiceImpl.class);

	@Autowired
	ContactRepository repository;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public Contact getContactById(Long id) {
		Optional<Contact> optContact= repository.findById(id);
		return optContact.isPresent() ? optContact.get() : null;
	}

	@Override
	public <T> T processFilters(HttpServletRequest req) {

		logger.info("ContactServiceImpl::processFilters - start");
		String param = null;
		Contact contact = null;
		while (req.getParameterNames().hasMoreElements()) {
			param = req.getParameterNames().nextElement();
			break;
		}
		if (param == null) {
			return null;
		}
		if (param.equals("emailid")) {
			contact = repository.findByEmail(req.getParameter(param));
		} else if (param.equals("phoneNumber")) {
			contact = repository.findByPhoneNumber(req.getParameter(param));
		} else if (param.equals("city")) {

		} else if (param.equals("state")) {

		}

		logger.info("ContactServiceImpl::processFilters - end");
		return (T) contact;
	}

	@Override
	public Contact saveContact(ContactDTO contactDTO) {
		
		return repository.save(modelMapper.map(contactDTO, Contact.class));
	}
	

	@Override
	public Contact updateContact(Long id, ContactDTO contactDTO) {
		 
		Contact updatedContact = null;
		
		Optional<Contact> contact = repository.findById(id);
	        
	        if(!contact.isPresent()) {
	        	logger.info("No contact found with id - "+id);
	        	updatedContact = repository.save(modelMapper.map(contactDTO, Contact.class));
	        } else {
	        	updatedContact = repository.save(modelMapper.map(contactDTO, Contact.class));
	        }
		
		return updatedContact;
	}

	@Override
	public Boolean deleteContact(Long id) {
		
		Optional<Contact> contact = repository.findById(id);
        
        if(!contact.isPresent()) {
        	System.out.println("No contact found with id - "+id);
        	 return false;
        } else {
        	repository.delete(contact.get());
        }
		
		return true;
	}

}
