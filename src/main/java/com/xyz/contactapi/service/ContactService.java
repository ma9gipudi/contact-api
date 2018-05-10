package com.xyz.contactapi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xyz.contactapi.dto.ContactDTO;
import com.xyz.contactapi.entity.Contact;

public interface ContactService {

    List<Contact> processFilters(HttpServletRequest req);
    Contact getContactById(Long id);
    Contact saveContact(ContactDTO contactDTO);
    Contact updateContact(Long id, ContactDTO contactDTO);
    Boolean deleteContact(Long id);
    List<ContactDTO> getContactBy(String email, String phone);
}
