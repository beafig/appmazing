package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.IContactService;
import com.campusdual.appmazing.model.dto.ContactDTO;
import com.campusdual.appmazing.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController: indico que esto es un controlador REST (para hacer peticiones GET, PUT, POST,
// DELETE)
//RequestMapping: indica la url donde se hace la petición, pero no completa, sino el endpoint
@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private IContactService contactService;

    //@GetMapping: indico que es una petición de tipo GET
    @GetMapping
    public String testController() {
        return "Contact controller works";
    }

    // método que devuelve un DTO pasándole el id
    @PostMapping(value = "/get")
    public ContactDTO queryContact (@RequestBody  ContactDTO contactDTO){
        return this.contactService.queryContact(contactDTO);
    }

    @GetMapping(value ="/getAll")
    public List<ContactDTO> queryAllContacts(){
        return this.contactService.queryAllContacts();
    }

    @PostMapping(value = "/add")
    public int insertContact(@RequestBody ContactDTO contactDTO){
        return this.contactService.insertContact(contactDTO);
    }

    // hay que indicar el id para que no cree un elemento nuevo, si no que modifique el
    // seleccionado
    @PutMapping(value = "/update")
    public int updateContact(@RequestBody ContactDTO contactDTO){
        return this.contactService.updateContact(contactDTO);
    }

    @DeleteMapping (value = "/delete")
    public int deleteContact(@RequestBody ContactDTO contactDTO){
        return this.contactService.deleteContact(contactDTO);
    }


}
