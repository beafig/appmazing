package com.campusdual.appmazing.api;

import com.campusdual.appmazing.model.dto.ProductDTO;
import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    //CRUD (create, read, update y delete) métodos
    ProductDTO queryProduct (ProductDTO productDTO);
    List<ProductDTO> queryAllProducts();
    int insertProduct (ProductDTO productDTO);
    int updateProduct (ProductDTO productDTO);
    int deleteProduct(ProductDTO productDTO);
    int buyProduct(ProductDTO productDTO, int quantity);
    BigDecimal buyAndShowTotalPrice(ProductDTO productDTO, int quantity);
}
