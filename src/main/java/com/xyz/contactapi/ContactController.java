package com.xyz.contactapi;

import com.xyz.contactapi.dto.ContactDTO;
import com.xyz.contactapi.dto.entity.Contact;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ContactController {

    @Autowired
    ContactRepository repository;

    @GetMapping(value = "/contact/{id}")
    public ContactDTO contact(@PathVariable(name = "id") long id) {
        return repository.findById(id)
                .map(e -> new ModelMapper().map(e, ContactDTO.class))
                .orElseThrow(() -> new RuntimeException());
    }

    @GetMapping(value = "/contact")
    public List<ContactDTO> contact() {
        return repository.findAll().stream()
                .map(e -> new ModelMapper().map(e, ContactDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping(value ="/contact")
    public void updateContact(@RequestBody ContactDTO contactDTO) {
        repository.save(new ModelMapper().map(contactDTO, Contact.class));
    }
}
