package com.example.be_api_web.repository.product;

import com.example.be_api_web.entity.product.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ColorRepository extends JpaRepository<Color,Long > {
    List<Color> findAll();

    Color findById(long id);

    Color findByName(String name);
}
