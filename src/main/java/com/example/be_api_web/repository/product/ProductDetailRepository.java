package com.example.be_api_web.repository.product;


import com.example.be_api_web.entity.product.Product;
import com.example.be_api_web.entity.product.Product_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<Product_Detail,Long> {
    @Query(value = "SELECT spct FROM product_detail WHERE spct.product.id=:idsp AND spct.isDeleted=false ",nativeQuery = true)
    List<Product_Detail>findProduct_DetailById(@Param("id") Long id);

    @Query(value = "SELECT * FROM product_detail WHERE is_deleted=false ORDER BY id DESC ",nativeQuery = true)
    List<Product_Detail>findAll();

    @Query(value = "SELECT * FROM Product_Detail WHERE id= ?",nativeQuery = true)
    Optional<Product_Detail> findById(Long id);

    @Query("SELECT pd FROM Product_Detail pd " +
            "JOIN pd.product p " +
            "JOIN pd.size s " +
            "JOIN pd.color c " +
            "JOIN p.material m " +
            "JOIN p.supplier sup " +
            "JOIN p.product_type pt " +
            "WHERE p.isDeleted = false AND " +
            "(p.name LIKE %:keyword% OR " +
//            "p.price LIKE %:keyword% OR " +
            "m.name LIKE %:keyword% OR " +
            "sup.name LIKE %:keyword% OR " +
            "pt.name LIKE %:keyword%)")
    List<Product_Detail> searchAll(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM product_detail WHERE name=?",nativeQuery = true)
    Optional<Product_Detail>findByName(String name);

    @Query(value = "SELECT * FROM product_detail WHERE product_id= ?" ,nativeQuery = true)
    List<Product_Detail>findByProductId(Long id);

    @Query(value = "SELECT quantity FROM product_detail WHERE size_id= ?1 AND color_id =?2 and product_id= ?3",nativeQuery = true)
    Integer getQuantityHienCo(long size_id,long color_id,long product_id );

    @Query(value = "SELECT quantity FROM product_detail WHERE size_id= ?1 AND color_id =?2 and product_id= ?3",nativeQuery = true)
    Product_Detail getProduct_Detail(long size_id,long color_id,long product_id);

    @Query(value = "DELETE FROM product_detail WHERE ID ?",nativeQuery = true)
    void deleteProduct_Detail(long id);

    @Query(value = "SELECT * FROM product_detail WHERE is_deleted = false AND product_id = ?", nativeQuery = true)
    List<Product_Detail> findProductDetails();



}
