package com.campusdual.appmazing.service;

import com.campusdual.appmazing.api.IProductService;
import com.campusdual.appmazing.model.Product;
import com.campusdual.appmazing.model.dao.ProductDAO;
import com.campusdual.appmazing.model.dto.ProductDTO;
import com.campusdual.appmazing.model.dto.dtomapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

// @Service: indica que esta clase es un servicio
// @Lazy: solo se va a cargar cuando se necesite, evita sobrecargas
@Service("ProductService")
@Lazy
public class ProductService implements IProductService {

    // con @AutoWired no usamos getters y setters, usamos directamente el nombre de la variable
    @Autowired
    private ProductDAO productDAO;

    // query para ver un producto pasándole el id del DTO
    @Override
    public ProductDTO queryProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        Product productFinal = this.productDAO.getReferenceById(product.getId());
        ProductDTO dto = ProductMapper.INSTANCE.toDTO(productFinal);
        return dto;
    }

    //query para ver una lista de todos los productos, se los pide al DAO que es el que conecta
    // con la BD
    @Override
    public List<ProductDTO> queryAllProducts() {
        return ProductMapper.INSTANCE.toDTOList(this.productDAO.findAll());
    }

    // insertProduct sirve para añadir un nuevo producto, le pasamos el DTO con todos los datos
    // menos el ID (ya que es autoincremental y se genera solo en la BD) lo convierte a modelo
    // (entidad) y una vez que tenemos el modelo le indicamos al DAO que lo añada
    @Override
    public int insertProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        Product product1 = this.productDAO.saveAndFlush(product);
        // uso la entidad en lugar del DTO porque voy a devolver un int, para devolver el
        // producto en si debería devolver el DTO y no la entidad
        return product1.getId();
    }

    // método para actualizar un producto en la BD, se llama al método insertProduct, el método
    // saveAndFlush dentro de este hace que si el modelo no tiene ID lo inserta en la BD, pero si
    // lo tiene solo actualiza los datos
    @Override
    public int updateProduct(ProductDTO productDTO) {
        return this.insertProduct(productDTO);
    }

    // método para eliminar un producto de la BD, le pasamos el dto, coge el ID de este dto, lo
    // convierte a modelo/entidad y le indica al DAO que elimine este elemento de la BD, devuelve
    // el id a modo informativo ya que no podemos hacer nada con ese id que ya no existe en la BD
    @Override
    public int deleteProduct(ProductDTO productDTO) {
        int id = productDTO.getId();
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        productDAO.delete(product);
        return id;
    }

    @Override
    public int buyProduct(ProductDTO productDTO, int quantity) {
        ProductDTO productToBuy = this.queryProduct(productDTO);
        if (productToBuy.isActive() && quantity <= productToBuy.getStock()) {
            productToBuy.setStock((productToBuy.getStock() - quantity));
            this.updateProduct(productToBuy);
        }
        return productToBuy.getStock();
    }

//    @Override
//    public BigDecimal totalPrice(ProductDTO productDTO, int quantity) {
//        ProductDTO productToBuy = this.queryProduct(productDTO);
//        return productToBuy.getPrice().multiply(BigDecimal.valueOf(quantity));
//    }
    @Override
    public BigDecimal buyAndShowTotalPrice(ProductDTO productDTO, int quantity) {
        // la siguiente línea es para que me devuelva todos los datos pasándole solo el ID,
        // si no lo pongo tendré que pasarle el DTO completo, con todos los datos
        ProductDTO productToBuy = this.queryProduct(productDTO);
        if (productToBuy.isActive() && quantity <= productToBuy.getStock()) {
            //buyProduct(productDTO, quantity); -- lo pongo en el controlador
            return productToBuy.getPrice().multiply(BigDecimal.valueOf(quantity));
        } else {
            BigDecimal empty = new BigDecimal(0);
            return empty;
        }
    }


}
