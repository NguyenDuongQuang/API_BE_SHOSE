package com.example.be_api_web.repository.product;

import com.example.be_api_web.entity.product.Product_Type;
import com.example.be_api_web.entity.product.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_TypeRepository extends JpaRepository<Product_Type,Long> {
    List<Product_Type> findAll();

    Product_Type findById(long id);

    Product_Type findByName(String name);
}
