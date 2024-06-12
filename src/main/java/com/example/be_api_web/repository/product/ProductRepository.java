package com.example.be_api_web.repository.product;

import com.example.be_api_web.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT * FROM product WHERE is_deleted=false AND status =1",nativeQuery = true)
    List<Product>findAll();
    
    @Query(value = "SELECT * FROM product WHERE id = ? AND is_deleted =false ",nativeQuery = true)
    Optional<Product> findById(Long id);


    @Query(value = "SELECT * FROM product WHERE is_deleted = false and (name LIKE %?1% or price LIKE %?1%) and status = 1;", nativeQuery = true)
    List<Product> findByAll(String input);

    @Query(value = "SELECT * FROM product WHERE is_deleted= false  AND name LIKE %?1% AND status = 1",nativeQuery = true)
    List<Product>findByName(String name);

    @Query(value = "SELECT * FROM product WHERE is_deleted= false  AND name LIKE %?1% AND status = 1",nativeQuery = true)
    Product findName(String name);

    @Query(value = "SELECT * FROM product WHERE product_type_id = ? AND is_deleted = false AND status = 1",nativeQuery = true)
    List<Product>findByProduct_type(long producy_type_id);

    @Query(value = "SELECT * FROM product WHERE material_id = ? AND is_deleted = false AND status =1",nativeQuery = true)
    List<Product>findByMaterial(long material_id);

    @Query(value = "SELECT * FROM product WHERE supplier_id = ? AND is_deleted = false AND status =1",nativeQuery = true)
    List<Product>findBySupplier(long supplier_id);

    @Query(value = "SELECT * FROM product WHERE price BETWEEN :price1 AND :price2 AND is_deleted = false and status = 0", nativeQuery = true)
    List<Product> findByPrice(@Param("price1") Float price1, @Param("price2") Float price2);
    
    @Query(value = "SELECT * FROM product JOIN product_detail on product_detail.product_id=product.id WHERE product_detail.color_id= ? AND product.is_deleted = false AND product.status=1",nativeQuery = true)
    List<Product>findByColor(long color_id);

    @Query(value = "SELECT * FROM product JOIN product_detail on product_detail.product_id=product.id WHERE product_detail.size= ? AND product.is_deleted = false AND product.status=1",nativeQuery = true)
    List<Product>findBySie(long size_id);

    @Query(value = "select * from product where id = ? and is_deleted = false", nativeQuery = true)
    Product findByID(Long id);
}