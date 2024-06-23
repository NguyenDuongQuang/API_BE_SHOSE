package com.example.be_api_web.service.Impl;

import com.example.be_api_web.controller.message.Message;
import com.example.be_api_web.entity.security.Role;
import com.example.be_api_web.entity.security.UserRole;
import com.example.be_api_web.entity.staff.Staff;
import com.example.be_api_web.repository.role.RoleRepository;
import com.example.be_api_web.repository.role.UserRoleRepository;
import com.example.be_api_web.repository.staff.StaffRepository;
import com.example.be_api_web.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StafffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAllStaff();
    }

    @Override
    public ResponseEntity<?> createStaff(Staff staff) {
        String errorMessage;
        Message errorResponse;

        Optional<Staff>checkMail= Optional.ofNullable(staffRepository.findByEmail(staff.getEmail()));
        if(checkMail.isPresent()){
            errorMessage="Trùng Email";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        String email = staff.getEmail();
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            errorMessage = "Mail không đúng định dạng";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Date dateC = new Date();
        Date dateOfBirth = staff.getBirth_day();
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(birthDate, currentDate).getYears();

        if(age <= 16){
            errorMessage = "Nhan vien duoi 16 tuoi";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }


        if (dateOfBirth.after(dateC)) {
            errorMessage = "Ngày Sinh Không Được Vượt Quá Thời Gian Hiện Tại";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(staff.getName()==null||staff.getEmail()==null||staff.getAddress()==null){
            errorMessage="Vui Lòng Nhập ĐẦy Đủ Thông Tin";
            errorResponse =new Message(errorMessage,TrayIcon.MessageType.ERROR);
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        }try {
            Staff nv=new Staff();
            nv.setName(staff.getName());
            nv.setPassword(staff.getPassword());
            nv.setAddress(staff.getAddress());
            nv.setEmail(staff.getEmail());
            nv.setPhone(staff.getPhone());
            nv.setStatus(1);
            nv.setBirth_day(staff.getBirth_day());
            staffRepository.save(nv);

            Role role=roleRepository.find("STAFF");
            Set<UserRole> userRoles=new HashSet<>();
            UserRole userRole=new UserRole();
            userRole.setRole(role);
            userRole.setStaff(staff);
            userRoles.add(userRole);
            userRoleRepository.save(userRole);
            return ResponseEntity.ok().body(staff);
        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Staff> editStaff(Staff staff) {
        String errorMessage;
        Message errorResponse;

        Optional<Staff>checkMail= Optional.ofNullable(staffRepository.findByEmail(staff.getEmail()));
        if(checkMail.isPresent()){
            errorMessage="Trùng Email";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        String email = staff.getEmail();
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            errorMessage = "Mail không đúng định dạng";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Date dateC = new Date();
        Date dateOfBirth = staff.getBirth_day();
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(birthDate, currentDate).getYears();

        if(age <= 16){
            errorMessage = "Nhan vien duoi 16 tuoi";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }


        if (dateOfBirth.after(dateC)) {
            errorMessage = "Ngày Sinh Không Được Vượt Quá Thời Gian Hiện Tại";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(staff.getName()==null||staff.getEmail()==null||staff.getAddress()==null){
            errorMessage="Vui Lòng Nhập ĐẦy Đủ Thông Tin";
            errorResponse =new Message(errorMessage,TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<Staff>optional=staffRepository.findById(staff.getId());
            if(optional.isPresent()){
                Staff nv=optional.get();
                nv.setName(staff.getName());
                nv.setGender(true);
                nv.setPhone(staff.getPhone());
                nv.setAddress(staff.getAddress());
                nv.setBirth_day(staff.getBirth_day());
                nv.setStatus(1);
                return ResponseEntity.ok(staffRepository.save(nv));
            }
            else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(),TrayIcon.MessageType.ERROR),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Staff>> deleteStaff(Long id) {
        Optional<Staff>optional=staffRepository.findById(id);
        if(optional.isPresent()){
            Staff staff=optional.get();
            staff.setDeleted(true);
            staffRepository.save(staff);
            return  ResponseEntity.ok().body(staffRepository.findAllStaff());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public List<Staff> searchAll(String search) {
        return staffRepository.findStaffAll(search);
    }

    @Override
    public Staff getEmail(String email) {
        return staffRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(Long id_user) {
        staffRepository.deleteById(id_user);
    }
}
