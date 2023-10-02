package com.campusdual.appmazing.service;

import com.campusdual.appmazing.api.IContactService;
import com.campusdual.appmazing.api.IProductService;
import com.campusdual.appmazing.model.Contact;
import com.campusdual.appmazing.model.Product;
import com.campusdual.appmazing.model.dao.ContactDAO;
import com.campusdual.appmazing.model.dao.ProductDAO;
import com.campusdual.appmazing.model.dto.ContactDTO;
import com.campusdual.appmazing.model.dto.ProductDTO;
import com.campusdual.appmazing.model.dto.dtomapper.ContactMapper;
import com.campusdual.appmazing.model.dto.dtomapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("ContactService")
@Lazy
public class ContactService implements IContactService {

    @Autowired
    private ContactDAO contactDAO;

    @Override
    public ContactDTO queryContact(ContactDTO contactDTO) {
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDTO);
        Contact contactFinal = this.contactDAO.getReferenceById(contact.getId());
        ContactDTO dto = ContactMapper.INSTANCE.toDTO(contactFinal);
        return dto;
    }
    @Override
    public List<ContactDTO> queryAllContacts() {
        return ContactMapper.INSTANCE.toDTOList(this.contactDAO.findAll());
    }
    @Override
    public int insertContact(ContactDTO contactDTO) {
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDTO);
        Contact contact1 = this.contactDAO.saveAndFlush(contact);
        return contact1.getId();
    }
    @Override
    public int updateContact(ContactDTO contactDTO) {
        return this.insertContact(contactDTO);
    }
    @Override
    public int deleteContact(ContactDTO contactDTO) {
        int id = contactDTO.getId();
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDTO);
        contactDAO.delete(contact);
        return id;
    }
}
