package com.example.be_api_web.controller.security;

import com.example.be_api_web.config.JwtUtils;
import com.example.be_api_web.entity.customer.Customer;
import com.example.be_api_web.entity.staff.Staff;
import com.example.be_api_web.helper.UserNotFoundException;
import com.example.be_api_web.model.request.JwtRequest;
import com.example.be_api_web.model.response.JwtResponse;
import com.example.be_api_web.repository.customer.CustomerRepository;
import com.example.be_api_web.repository.staff.StaffRepository;
import com.example.be_api_web.service.Impl.StaffServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class SecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StaffServiceImpl staffDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // generte token
    @SneakyThrows
    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
            UserDetails userDetails = this.staffDetailsService.loadUserByUsername(jwtRequest.getEmail());
            String token = this.jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
    }

    private void authenticate(String username, String password) throws Exception {
        Staff staff = this.staffRepository.findByEmail(username);
        if (staff != null) {
            if (staff.getStatus() == 1) {
                throw new DisabledException("Nhân viên đã nghỉ việc");
            }
        } else {
            Customer customer = this.customerRepository.findByEmail(username);
            if (customer == null) {
                throw new BadCredentialsException("Sai thông tin đăng nhập");
            }
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Error");
        }
    }

    // return the details of current user
    @GetMapping("/current-user")
    public Staff getCurrentUser(Principal principal) {
        return (Staff) this.staffDetailsService.loadUserByUsername(principal.getName());
    }
}

