package com.xyz.contactapi;

import com.xyz.contactapi.dto.ContactDTO;
import com.xyz.contactapi.entity.Contact;
import com.xyz.contactapi.exception.ApiException;
import com.xyz.contactapi.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class ContactController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ContactService service;

    @GetMapping(value = "/contact/{id}")
    public ContactDTO getContact(@PathVariable(name = "id") Long id) {
        return Optional.ofNullable(service.getContactById(id))
                .map(e -> modelMapper.map(e, ContactDTO.class))
                .orElseThrow(() -> {
                    throw new ApiException("Contact Not found. Id: " + id);
                });
    }


    @GetMapping(value = "/contact")
    public ResponseEntity<?> contactByParams(HttpServletRequest req) {

        List<Contact> contactLst = service.processFilters(req);


        if (contactLst == null || contactLst.size() == 0) {
            System.out.println("No contact found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return contactLst.size() > 1 ? new ResponseEntity<List<Contact>>(contactLst, HttpStatus.OK)
                : new ResponseEntity<ContactDTO>(modelMapper.map(contactLst.get(0), ContactDTO.class), HttpStatus.OK);
    }


    @PostMapping(value = "/contact")
    public ResponseEntity<?> addContact(@RequestBody ContactDTO contactDTO) {
        log.info(contactDTO.toString());
        Contact contact = service.saveContact(contactDTO);

        if (contact == null) {
            System.out.println("No contact found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ContactDTO>(modelMapper.map(contact, ContactDTO.class), HttpStatus.OK);
    }

    @PutMapping(value = "/contact/{id}/image")
    public ResponseEntity<?> updateContact(@PathVariable(name = "id") Long id, @RequestBody MultipartFile image,
                                           HttpServletRequest req) throws IOException {
        Contact updatedContact = null;
        log.info("id -" + id);
        if (image == null) {
            log.info("Image is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        updatedContact = service.getContactById(id);
        updatedContact.setImage(image.getBytes());
        service.saveContact(modelMapper.map(updatedContact, ContactDTO.class));

        return new ResponseEntity<ContactDTO>(modelMapper.map(updatedContact, ContactDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/contact/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable(name = "id") Long id) {
        log.info("id - " + id);
        return service.deleteContact(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
