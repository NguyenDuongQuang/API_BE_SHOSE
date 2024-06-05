package com.example.be_api_web.repository.customer;

import com.example.be_api_web.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
//    Customer findByEmail(String email);


    @Query(value = "select * from customer where is_deleted = false ORDER BY id desc", nativeQuery = true)
    List<Customer> findAllCustomer();

    @Query(value = "select * from customer where id = ? and is_deleted = false", nativeQuery = true)
    Customer findByID(Long id);

    @Query(value = "select * from customer where name = ?", nativeQuery = true)
    Optional<Customer> findByName(String name);


    @Query(value = "select * from customer where name = ?1 OR phone = ?1 OR email = ?1 OR address = ?1", nativeQuery = true)
    List<Customer> findCustomerAll(String input);

    @Query(value = "select * from customer where is_deleted = false and date(birth_day) = ?", nativeQuery = true)
    List<Customer> findCustomerDate(LocalDate ngaySinh);

    @Query(value = "select * from customer where email = ?", nativeQuery = true)
    Customer findByEmail(String email);

    @Query(value = "select * from customer where phone  = ?", nativeQuery = true)
    Customer findBySDT(String phone);
}
