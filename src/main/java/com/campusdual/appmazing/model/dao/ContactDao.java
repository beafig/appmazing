package com.campusdual.appmazing.model.dao;

import com.campusdual.appmazing.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDAO extends JpaRepository<Contact, Integer> {

}
