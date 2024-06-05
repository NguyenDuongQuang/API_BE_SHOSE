package com.example.be_api_web.service.Impl;

import com.example.be_api_web.dto.UserDto;
import com.example.be_api_web.entity.customer.Customer;
import com.example.be_api_web.entity.staff.Staff;
import com.example.be_api_web.repository.customer.CustomerRepository;
import com.example.be_api_web.repository.staff.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements UserDetailsService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = this.staffRepository.findByEmail(username);
        if (staff != null) {
            UserDto dto = new UserDto();
            dto.setName(staff.getName());
            dto.setEmail(staff.getEmail());
            dto.setPassword(staff.getPassword());
            dto.setRole(staff.getUserRoles());
            dto.setPhone(staff.getPhone());
            return dto;
        }

        Customer customer = this.customerRepository.findByEmail(username);
        if (customer != null) {
            UserDto dto = new UserDto();
            dto.setName(customer.getName());
            dto.setEmail(customer.getEmail());
            dto.setPassword(customer.getPassword());
            dto.setRole(customer.getUserRoles());
            dto.setPhone(customer.getPhone());
            return dto;
        }

        throw new UsernameNotFoundException("Thông tin đăng nhập không hợp lệ !!");

    }
}
