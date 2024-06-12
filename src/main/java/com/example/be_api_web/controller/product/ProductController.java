package com.example.be_api_web.controller.product;

import com.example.be_api_web.dto.ProductDto;
import com.example.be_api_web.dto.SPCTDto;
import com.example.be_api_web.entity.product.Product;
import com.example.be_api_web.entity.product.Product_Detail;
import com.example.be_api_web.repository.product.ImageRepository;
import com.example.be_api_web.repository.product.ProductDetailRepository;
import com.example.be_api_web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ImageRepository imageRepository;
    
    @Autowired
    private ProductDetailRepository productDetailRepository;
    
    @GetMapping("/get-all-readly")
    public ResponseEntity<?>getAll(){
        List<Product>list=productService.findAll();
        List<ProductDto> listDto= new ArrayList<>();
        for(Product product:list){
            ProductDto productDto=new ProductDto();
            productDto.setId(product.getId());
            productDto.setName_Product(product.getName());
            productDto.setStatus(1);
            productDto.setPrice(product.getPrice());
            String image =imageRepository.getImageProduct(product.getId());
            productDto.setImage_product(image);
            listDto.add(productDto);
        }
        return ResponseEntity.ok(listDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>>getAllPro(){
        return ResponseEntity.ok().body(productService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?>createPro(@RequestBody ProductDto productDto){
        return ResponseEntity.ok().body(productService.saveProduct(productDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?>updatePro(@RequestBody ProductDto productDto){
        return ResponseEntity.ok().body(productService.saveEdit(productDto));
    }
    @GetMapping("/search/{search}")
    public ResponseEntity<?>searchPro(@PathVariable("search") String search){
        return productService.searchAll(search);
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<?>getImage(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(imageRepository.getImageProduct(id));
    }
    @DeleteMapping("/product_detail/{id}")
    private void deleteProDetail(@RequestBody Product_Detail product_detail){
        productDetailRepository.deleteById(product_detail.getId());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Product>>deletePro(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

//    @PostMapping("/delete_pro_detail")
//    public ResponseEntity<?>delteProdetail(@RequestBody SPCTDto spctDto){
//        return ResponseEntity.ok().body(productDetailRepository.findByProductId(spctDto.getProductId()));
//    }

}
