package com.example.be_api_web.repository.staff;

import com.example.be_api_web.entity.staff.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {

    @Query(value = "select * from staff where is_deleted  = false  order by id desc ", nativeQuery = true)
    List<Staff> findAllStaff();

    @Query(value = "select * from staff where id = ? and is_deleted = false", nativeQuery = true)
    Staff findByID(Long id);

    @Query(value = "select * from staff where name = ? and is_deleted = false ", nativeQuery = true)
    Staff findByName(String hoTen);

    @Query(value = "select * from staff where is_deleted = false and (phone LIKE %?1% OR name LIKE %?1% OR email LIKE %?1%);", nativeQuery = true)
    List<Staff> findStaffAll(String input);


    @Query(value =  "select * from staff where is_deleted = false and date(birth_day) = ?", nativeQuery = true)
    List<Staff> findStaffDate(LocalDate birth_day);

    @Query(value = "select * from staff where email = ?", nativeQuery = true)
    Staff findByEmail(String username);

    @Query(value = "select * from staff where phone = ?", nativeQuery = true)
    Staff findBySdt(String sdt);

}
