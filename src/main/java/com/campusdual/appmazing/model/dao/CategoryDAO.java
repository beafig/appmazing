package com.campusdual.appmazing.model.dao;

import com.campusdual.appmazing.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
}
