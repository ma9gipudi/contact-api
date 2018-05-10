package com.xyz.contactapi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xyz.contactapi.dto.ContactDTO;
import com.xyz.contactapi.dto.entity.Contact;
import org.springframework.stereotype.Service;

@Service
public interface ContactService {

	public List<Contact> processFilters(HttpServletRequest req);

	public Contact getContactById(Long id);

	public Contact saveContact(ContactDTO contactDTO);

	public Contact updateContact(Long id, ContactDTO contactDTO);

	public Boolean deleteContact(Long id);

}
