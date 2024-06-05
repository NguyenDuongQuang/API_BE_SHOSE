package com.example.be_api_web.entity.staff;

import com.example.be_api_web.entity.Base;
import com.example.be_api_web.entity.security.Authority;
import com.example.be_api_web.entity.security.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "staff")
@EntityListeners(AuditingEntityListener.class)
public class Staff extends Base implements UserDetails {
    @Column(name = "Address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "Name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "gender", columnDefinition = "bit")
    private Boolean gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private Integer status;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd")
    @Column(name = "birth_day")
    private Date birth_day;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "staff")
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

