package com.xyz.contactapi.service;

import javax.servlet.http.HttpServletRequest;

import com.xyz.contactapi.dto.ContactDTO;
import com.xyz.contactapi.dto.entity.Contact;

public interface ContactService {

	public <T> T processFilters(HttpServletRequest req);

	public Contact getContactById(Long id);

	public Contact saveContact(ContactDTO contactDTO);

	public Contact updateContact(Long id, ContactDTO contactDTO);

	public Boolean deleteContact(Long id);

}
