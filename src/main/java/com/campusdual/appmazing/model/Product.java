package com.campusdual.appmazing.model;

// creamos un modelo que será nuestra tabla de la DB, cada columna de la tabla será un atributo
// del objeto
// no hay constructor en los modelos ¿por qué?

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

// @Entity esta clase es una entidad, en un elemento que se puede guardar en base de datos
@Entity
// @Table: indicar de que tabla viene
@Table(name = "PRODUCTS")
public class Product {
    //@Id para indicar que es la clave primaria
    //@GeneratedValue para indicar que es autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // @column pra indicar que son columnas de la tabla
    @Column
    private String name;
    @Column
    private int stock;
    // para el precio se usa clase de Java especial
    @Column
    private BigDecimal price;
    @Column
    private boolean active;
    @Column
    private Date date_added;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }
}
