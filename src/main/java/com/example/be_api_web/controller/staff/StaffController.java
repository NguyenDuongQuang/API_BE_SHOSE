package com.example.be_api_web.controller.staff;

import com.example.be_api_web.entity.product.Material;
import com.example.be_api_web.entity.staff.Staff;
import com.example.be_api_web.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Staff>>getAll(){
        return ResponseEntity.ok().body(staffService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?>createStaff(@RequestBody Staff staff){
        return ResponseEntity.ok().body(staffService.createStaff(staff));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Staff>updateStaff(@RequestBody Staff staff){
        return staffService.editStaff(staff);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Staff>>deleteStaff(@PathVariable("id") Long id){
        return staffService.deleteStaff(id);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<Staff>>searchStaff(@PathVariable("search") String search){
        return ResponseEntity.ok().body(staffService.searchAll(search));
    }
}
