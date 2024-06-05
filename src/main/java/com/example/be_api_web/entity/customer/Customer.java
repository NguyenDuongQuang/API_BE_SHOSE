package com.example.be_api_web.entity.customer;

import com.example.be_api_web.entity.Base;
import com.example.be_api_web.entity.security.Authority;
import com.example.be_api_web.entity.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer")
public class Customer extends Base implements UserDetails {
    @Column(name = "name", columnDefinition = "nvarchar(50) not null")
    private String name;

    @Column(name = "phone", columnDefinition = "nvarchar(10) null")
    private String phone;

    @Column(name = "email", columnDefinition = "nvarchar(200) null")
    private String email;

    @Column(name = "birth_day", columnDefinition = "Date null")
    private Date birth_day;

    @Column(name = "address", columnDefinition = "nvarchar(250) null")
    private String address;

    @Column(name = "password", columnDefinition = "nvarchar(250) null")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer")
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set = new HashSet<>();
        this.userRoles.forEach(userRole -> {
            set.add(new Authority(userRole.getRole().getRoleName()));
        });
        return set;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

