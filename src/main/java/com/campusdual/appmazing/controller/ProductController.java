package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.IProductService;
import com.campusdual.appmazing.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

//@RestController: indico que esto es un controlador REST (para hacer peticiones GET, PUT, POST,
// DELETE)
//RequestMapping: indica la url donde se hace la petición, pero no completa, sino el endpoint
@RestController
@RequestMapping("/products")
public class ProductController {
    //@autowired: hace referencia a la interfaz del servicio
    @Autowired
    private IProductService productService;

    //@GetMapping: indico que es una petición de tipo GET
    @GetMapping
    public String testController() {
        return "Product controller works";
    }

    @PostMapping
    public String testController(@RequestBody String name) {
        return "Product controller works, " + name + "!";
    }

    @GetMapping(value = "/testMethod")
    public String testControllerMethod() {
        return "Product controller method works";
    }

    // método que devuelve un DTO pasándole el id
    @PostMapping(value = "/get")
    public ProductDTO queryProduct(@RequestBody ProductDTO productDTO) {
        return this.productService.queryProduct(productDTO);
    }

    @GetMapping(value = "/getAll")
    public List<ProductDTO> queryAllProducts() {
        return this.productService.queryAllProducts();
    }

    @PostMapping(value = "/add")
    public int insertProduct(@RequestBody ProductDTO productDTO) {
        return this.productService.insertProduct(productDTO);
    }

    // hay que indicar el id para que no cree un elemento nuevo, si no que modifique el
    // seleccionado
    @PutMapping(value = "/update")
    public int updateProduct(@RequestBody ProductDTO productDTO) {
        return this.productService.updateProduct(productDTO);
    }

    @DeleteMapping(value = "/delete")
    public int deleteProduct(@RequestBody ProductDTO productDTO) {
        return this.productService.deleteProduct(productDTO);
    }

    // aunque con los métodos buyProduct() se actualiza la BD, se usa el método POST y no el PUT
    // porque le estoy pidiendo que me devuelva datos y no le paso el DTO completo, si no
    // solamente el ID. Si paso el DTO completo y nada más y lo uso para actualizar datos uso
    // PUT, sino siempre POST.
    @PostMapping(value = "/buy5")
    public int buyProduct(@RequestBody ProductDTO productDTO) {
        int quantity = 5;
        return this.productService.buyProduct(productDTO, quantity);
    }

    @PostMapping(value = "/buy")
    public int buyProduct(@RequestBody Map<String, Integer> body) {
        int quantity = body.get("quantity");
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(body.get("id"));
        return this.productService.buyProduct(productDTO, quantity);
    }

    @PostMapping(value = "/price")
    public BigDecimal buyAndShowTotalPrice(@RequestBody ProductDTO productDTO) {
        int quantity = 4;
        //la lógica de comprar la pongo aquí, así mi método buyAndShowTotalPrice tiene una lógica
        // independiente, y podría crear otro controlador para ver el precio, pero que no
        // comprase, es decir, que no sacara de stock.
        this.productService.buyProduct(productDTO, quantity);
        return this.productService.buyAndShowTotalPrice(productDTO, quantity);
    }
}
