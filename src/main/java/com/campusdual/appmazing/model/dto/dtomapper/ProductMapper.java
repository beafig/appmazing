package com.campusdual.appmazing.model.dto.dtomapper;

import com.campusdual.appmazing.model.Product;
import com.campusdual.appmazing.model.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

//@Mapper para indicar a la librería que lo va a tener que ejecutar internamente
@Mapper
public interface ProductMapper {

    //INSTANCE es una constante necesaria para que funcione que el programa internamente
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    //UN MAPPER SIEMPRE VA A TENER ESTOS 3 MÉTODOS (básicamente un mapper sirve para hacer
    // conversiones)
    // pasándole un producto devuelve un productDTO y se va a llamar toDTO (hace una conversión)
    ProductDTO toDTO(Product product);
    // le pasas una lista de productos (modelos) y devuelve una lista de productDTO
    List<ProductDTO> toDTOList(List<Product> products);

    // pasándole un productDTO lo convierte a product (modelo)
    Product toEntity(ProductDTO productDTO);
}
