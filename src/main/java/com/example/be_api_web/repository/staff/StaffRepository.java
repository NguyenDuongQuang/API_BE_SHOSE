package com.example.be_api_web.repository.staff;

import com.example.be_api_web.entity.staff.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
    Staff findByEmail(String username);
}
