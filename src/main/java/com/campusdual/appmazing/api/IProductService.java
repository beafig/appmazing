package com.campusdual.appmazing.api;

import com.campusdual.appmazing.model.dto.ProductDTO;
import java.util.List;

public interface IProductService {
    ProductDTO queryProduct (ProductDTO product);
    List<ProductDTO> queryAllProducts();
    int insertProduct (ProductDTO product);
    int updateProduct (ProductDTO product);
    int deleteProduct(ProductDTO product);
}
