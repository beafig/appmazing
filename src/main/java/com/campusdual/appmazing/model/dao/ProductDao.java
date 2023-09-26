package com.campusdual.appmazing.model.dao;

import com.campusdual.appmazing.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// el mismo nombre del modelo añadiendo Dao (Data Access Object)
// lleva un mapa interno en el que se indica el tipo de dato de la clase Product, y el tipo de
// dato de la clave primaria, que sería int, pero no admite datos primitivos por eso usamos Integer
public interface ProductDAO extends JpaRepository<Product, Integer> {

}
