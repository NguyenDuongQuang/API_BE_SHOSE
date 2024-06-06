package com.example.be_api_web.repository.product;

import com.example.be_api_web.entity.product.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material,Long> {

    List<Material>findAll();

    Material findById(long id);

    Material findByName(String name);
}
