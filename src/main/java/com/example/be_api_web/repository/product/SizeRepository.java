package com.example.be_api_web.repository.product;

import com.example.be_api_web.entity.product.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size,Long> {
    List<Size> findAll();

    Size findById(long id);

    Size findByName(String name);
}
