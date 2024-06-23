package com.example.be_api_web.service;

import com.example.be_api_web.entity.security.UserRole;
import com.example.be_api_web.entity.staff.Staff;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface StaffService {
    List<Staff>findAll();

    ResponseEntity<?>createStaff(Staff staff);

    ResponseEntity<Staff>editStaff(Staff staff);

    ResponseEntity<List<Staff>>deleteStaff(Long id);

    List<Staff>searchAll(String search);

    Staff getEmail(String email);

    public void deleteUser(Long id_user);

}
