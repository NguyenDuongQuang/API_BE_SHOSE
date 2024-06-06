package com.example.be_api_web.repository.product;

import com.example.be_api_web.entity.product.Product_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<Product_Detail,Long> {
}
