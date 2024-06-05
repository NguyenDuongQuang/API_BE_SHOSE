package com.example.be_api_web.config;

import com.example.be_api_web.dto.UserDto;
import com.example.be_api_web.entity.customer.Address;
import com.example.be_api_web.entity.customer.Customer;
import com.example.be_api_web.entity.security.UserRole;
import com.example.be_api_web.entity.staff.Staff;
import com.example.be_api_web.repository.customer.Addressrepository;
import com.example.be_api_web.repository.customer.CustomerRepository;
import com.example.be_api_web.repository.staff.StaffRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtils {
    private final SecretKey secretKey;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Addressrepository addressrepository;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public JwtUtils(@Value("${app.jwtSecret}") String jwtSecret) {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = null;
        UserDto userDTO = null;
        Staff staff = this.staffRepository.findByEmail(userDetails.getUsername());
        if (staff != null) {
            userDTO = new UserDto();
            userDTO.setEmail(staff.getEmail());
            userDTO.setName(staff.getName());
            userDTO.setPassword(staff.getPassword());
            userDTO.setRole(staff.getUserRoles());
            userDTO.setPhone(staff.getPhone());
            claims = new HashMap<>();
            claims.put("hoTen", userDTO.getName());
            claims.put("email", userDTO.getEmail());
            claims.put("role", getRolesAsString(userDTO.getRole()));
            claims.put("soDienThoai", userDTO.getPhone());

        } else {
            Customer khachHang = customerRepository.findByEmail(userDetails.getUsername());
            Address diaChi = addressrepository.getAddress(khachHang.getId());
            if (diaChi != null) {
                if (khachHang != null) {
                    userDTO = new UserDto();
                    userDTO.setEmail(khachHang.getEmail());
                    userDTO.setName(khachHang.getName());
                    userDTO.setPassword(khachHang.getPassword());
                    userDTO.setRole(khachHang.getUserRoles());
                    userDTO.setPhone(khachHang.getPhone());
                    userDTO.setAddress(diaChi.getAddress());
                    claims = new HashMap<>();
                    claims.put("Họ Tên", userDTO.getName());
                    claims.put("Emial", userDTO.getEmail());
                    claims.put("role", getRolesAsString(userDTO.getRole()));
                    claims.put("Phone", userDTO.getPhone());
                    claims.put("Address", userDTO.getAddress());
                } else {
                    throw new UsernameNotFoundException("Thông tin đăng nhập không hợp lệ!!");
                }
            } else if (diaChi == null) {
                userDTO = new UserDto();
                userDTO.setEmail(khachHang.getEmail());
                userDTO.setName(khachHang.getName());
                userDTO.setPassword(khachHang.getPassword());
                userDTO.setRole(khachHang.getUserRoles());
                userDTO.setPhone(khachHang.getPhone());
                userDTO.setAddress("Không có");
                claims = new HashMap<>();
                claims.put("Họ Tên", userDTO.getName());
                claims.put("Email", userDTO.getEmail());
                claims.put("role", getRolesAsString(userDTO.getRole()));
                claims.put("Phone", userDTO.getPhone());
                claims.put("Address", userDTO.getAddress());
            }

        }
        return createToken(claims);
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("email").toString()) // Đặt Subject thành email hoặc một trường unique khác
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    private String getRolesAsString(Set<UserRole> userRoles) {
        List<String> roles = new ArrayList<>();
        for (UserRole role : userRoles) {
            roles.add(role.getRole().getRoleName());
        }
        return String.join(",", roles); // Trả về chuỗi các Role, có thể sử dụng dấu phân cách phù hợp
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
