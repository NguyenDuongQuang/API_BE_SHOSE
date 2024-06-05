package com.example.be_api_web.repository.customer;

import com.example.be_api_web.entity.customer.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Addressrepository extends JpaRepository<Address,Long> {
    @Query(value = "select * from address where customer_id = ? and address_default = true", nativeQuery = true)
    Address getAddress(long customer_id);

    @Query(value = "select * from address where customer_id = ?", nativeQuery = true)
    List<Address> findByCustomerID(long id);

    @Query(value = "select * from address where customer_id = ? and id = ?", nativeQuery = true)
    Address findbyCustomerAndID(long customer_id, long id);


}
