package com.xyz.contactapi;

import com.xyz.contactapi.dto.ContactDTO;
import com.xyz.contactapi.dto.entity.Contact;
import com.xyz.contactapi.dto.entity.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.http.HTTPException;

@RestController
public class ContactController {

    @Autowired
    ContactRepository repository;

    @GetMapping(value = "/contact/{id}")
    public ContactDTO contact(@PathVariable(name = "id") long id) {
        return repository.findById(id)
                .map(x -> new ModelMapper().map(x, ContactDTO.class))
                .orElseThrow(() -> new RuntimeException());
    }

    @PostMapping(value ="/contact")
    public void updateContact(@RequestBody ContactDTO contactDTO) {
        System.out.println(contactDTO);
        repository.save(new ModelMapper().map(contactDTO, Contact.class));
    }
}
