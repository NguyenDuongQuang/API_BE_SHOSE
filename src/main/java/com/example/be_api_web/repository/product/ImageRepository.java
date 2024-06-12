package com.example.be_api_web.repository.product;

import com.example.be_api_web.entity.product.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query(value = "SELECT name FROM image WHERE id_product = ? AND image_default = true",nativeQuery = true)
    String getImageProduct(long product_id);
}
