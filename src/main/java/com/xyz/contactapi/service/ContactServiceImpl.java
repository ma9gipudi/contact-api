package com.xyz.contactapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.contactapi.dto.ContactDTO;
import com.xyz.contactapi.entity.Contact;
import com.xyz.contactapi.entity.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	Logger logger = LogManager.getLogger(ContactServiceImpl.class);

	@Autowired
	ContactRepository repository;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public Contact getContactById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

	@Override
	public List<Contact> processFilters(HttpServletRequest req) {

		logger.info("ContactServiceImpl::processFilters - start");
		String param = null;
		List<Contact> contactLst= new ArrayList<>();
		Contact contact = null;
		while (req.getParameterNames().hasMoreElements()) {
			param = req.getParameterNames().nextElement();
			break;
		}
		if (param == null) {
			return null;
		}
		if (param.equals("emailid")) {
			contact = repository.findByEmail(req.getParameter(param).toLowerCase().trim());
			contactLst.add(contact);
		} else if (param.equals("phoneNumber")) {
			contact = repository.findByPhoneNumber(req.getParameter(param).toLowerCase().trim());
			contactLst.add(contact);
		} else if (param.equals("city") || param.equals("state")) {
			contactLst = repository.findByCityOrState(req.getParameter(param).toLowerCase().trim());
			logger.info("contactLst.size - "+contactLst.size());
		}

		logger.info("ContactServiceImpl::processFilters - end");
		return contactLst;
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
