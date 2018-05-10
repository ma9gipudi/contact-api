package com.xyz.contactapi;

import com.xyz.contactapi.dto.entity.Contact;
import com.xyz.contactapi.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ContactService contactService;


    @Nested
    public class WhenContactsAreAvailable {

        @BeforeEach
        public void setUp() {
            when(contactService.getContactById(Long.valueOf(1)))
                    .thenReturn(Contact.builder().id(Long.valueOf(12345)).build());
        }

        @Test
        public void testGetContact_WhenAvailable() throws Exception {
            mockMvc.perform(get("/contact/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(12345));
        }
    }
}