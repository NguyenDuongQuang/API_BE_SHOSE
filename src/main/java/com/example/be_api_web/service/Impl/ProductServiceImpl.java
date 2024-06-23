package com.example.be_api_web.service.Impl;

import com.example.be_api_web.controller.message.Message;
import com.example.be_api_web.dto.ProductDto;
import com.example.be_api_web.entity.product.*;
import com.example.be_api_web.entity.product.Color;
import com.example.be_api_web.repository.product.*;
import com.example.be_api_web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private Product_TypeRepository product_typeRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ImageRepository imageRepository;

    Long id_product;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ResponseEntity<Product> saveEdit(ProductDto productDto) {
//        Product optional = productRepository.findName(productDto.getName_Product());
        String errorMessages;
        Message erroResponse;
        if(productDto.getPrice() <= 0){
            errorMessages="Số Tiền Phải Lớn Hơn 0";
            erroResponse =new Message(errorMessages,TrayIcon.MessageType.ERROR);
            return new ResponseEntity(erroResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Product> optionalProduct=productRepository.findById(productDto.getId());
            if(optionalProduct.isPresent()){
                Product product=optionalProduct.get();
                product.setName(productDto.getName_Product());
                product.setPrice(productDto.getPrice());
                product.setStatus(1);
                product.setProduct_type(productDto.getProduct_type());
                product.setMaterial(productDto.getMarterial());
                product.setSupplier(productDto.getSupplier());
                productRepository.save(product);
                return ResponseEntity.ok(product);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Product>> deleteProduct(Long id) {
        try {
            Optional<Product> optionalProduct=productRepository.findById(id);
            if(optionalProduct.isPresent()){
                Product product=optionalProduct.get();
                product.setDeleted(true);
                product.setStatus(1);
                productRepository.save(product);
                return ResponseEntity.ok().body(productRepository.findAll());
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<?> searchAll(String input) {
        List<Product> products=productRepository.findByAll(input);
        List<ProductDto>productDtos=new ArrayList<>();
        for(Product pro:products){
            ProductDto productDto=new ProductDto();
            productDto.setName_Product(pro.getName());
            productDto.setPrice(pro.getPrice());
            productDto.setMarterial(pro.getMaterial());
            productDto.setSupplier(pro.getSupplier());
            productDto.setProduct_id(pro.getId());
            productDto.setImage_product(imageRepository.getImageProduct(pro.getId()));
            productDtos.add(productDto);
        }
        return ResponseEntity.ok().body(productDtos);
    }

    @Override
    public ResponseEntity<?> saveProduct(ProductDto productDto) {
        Product optional = productRepository.findName(productDto.getName_Product());
        String errorMessages;
        Message erroResponse;

        if(optional != null){
            errorMessages="Trùng tên Sản Phẩm";
            erroResponse=new Message(errorMessages,TrayIcon.MessageType.ERROR);
            Map<String,String>mapResponse=new HashMap<>();
            mapResponse.put("messages",errorMessages);
            return new ResponseEntity(mapResponse,HttpStatus.BAD_REQUEST);
        }
        Map<String,Object> response=new HashMap<>();
        Material material=materialRepository.findByID(productDto.getMarterial_id());
        Product_Type product_type =product_typeRepository.findByID(productDto.getProduct_type_id());
        Supplier supplier=supplierRepository.findByID(productDto.getSupplier_id());

        Product product=new Product();
        product.setName(productDto.getName_Product());
        product.setPrice(productDto.getPrice());
        product.setStatus(1);
        product.setProduct_type(product_type);
        product.setMaterial(material);
        product.setSupplier(supplier);
        productRepository.save(product);

        id_product=product.getId();
        List<Product_Detail> product_details=new ArrayList<>();
        for (Long size_id : productDto.getSize()){
            Size size=sizeRepository.findByID(productDto.getSize_id());
            for(Long color_id :productDto.getColor()){
                Color color=colorRepository.findByID(productDto.getColor_id());
                Product_Detail proDetail=new Product_Detail();
                proDetail.setProduct(product);
                proDetail.setColor(color);
                proDetail.setSize(size);
                proDetail.setStatus(true);
                proDetail.setQuantity(productDto.getQuantity());
                productDetailRepository.save(proDetail);
                product_details.add(proDetail);
            }
        }
        response.put("list",product_details);
        response.put("id_product",product.getId());
        return ResponseEntity.ok().body(response);

    }

    @Override
    public List<Object> ProductAll(Long id_product) {
        Product product=productRepository.findByID(id_product);
        List<Product_Detail>product_details=productDetailRepository.findByProductId(id_product);
        List<Object>response=new ArrayList<>();
        response.add(product);
        response.add(product_details);
        return response;
    }

    @Override
    public ResponseEntity<?> getPrice(Float price1, Float price2) {
        try {
            List<Product>products=productRepository.findByPrice(price1,price2);
            List<ProductDto> product_details=new ArrayList<>();
            for(Product pro :products){
                ProductDto productDto=new ProductDto();
                productDto.setProduct_type(pro.getProduct_type());
                productDto.setId(pro.getId());
                productDto.setSupplier(pro.getSupplier());
                productDto.setProduct_id(pro.getId());
                productDto.setName_Product(pro.getName());
                productDto.setPrice(pro.getPrice());
                productDto.setMarterial(pro.getMaterial());
                productDto.setImage_product(imageRepository.getImageProduct(pro.getId()));
                product_details.add(productDto);
            }
            return ResponseEntity.ok().body(product_details);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
