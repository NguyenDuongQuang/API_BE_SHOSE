package com.example.be_api_web.repository.product;

import com.example.be_api_web.entity.product.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    List<Supplier> findAll();

    Supplier findById(long id);

    Supplier findByName(String name);
}
