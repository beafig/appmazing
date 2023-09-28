package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.IProductService;
import com.campusdual.appmazing.model.Product;
import com.campusdual.appmazing.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController: indico que esto es un controlador REST (para hacer peticiones GET, PUT, POST,
// DELETE)
//RequestMapping: indica la url donde se hace la petición, pero no completa, sino el endpoint
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductService productService;

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
    public ProductDTO queryProduct (@RequestBody  ProductDTO productDTO){
        return this.productService.queryProduct(productDTO);
     }

     @GetMapping(value ="/getAll")
     public List<ProductDTO> queryAllProducts(){
        return this.productService.queryAllProducts();
     }

     @PostMapping(value = "/add")
     public int insertProduct(@RequestBody ProductDTO productDTO){
        return this.productService.insertProduct(productDTO);
     }

     // hay que indicar el id para que no cree un elemento nuevo, si no que modifique el
     // seleccionado
     @PutMapping(value = "/update")
     public int updateProduct(@RequestBody ProductDTO productDTO){
        return this.productService.updateProduct(productDTO);
     }

     @DeleteMapping (value = "/remove")
    public int deleteProduct(@RequestBody ProductDTO productDTO){
        return this.productService.deleteProduct(productDTO);
     }
}
