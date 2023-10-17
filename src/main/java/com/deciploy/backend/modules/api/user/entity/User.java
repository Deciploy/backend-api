package com.deciploy.backend.modules.api.user.entity;

import com.deciploy.backend.modules.api.company.entity.Company;
import com.deciploy.backend.modules.api.team.entity.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity(name = "user_account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String email;

    private String fullName;
    @JsonIgnore
    private String password;

    @Getter
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Team team;

    public User() {

    }

    public User(String fullName, String email, String password, String[] roles, Company company, Team team) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.roles = Arrays.stream(roles).toList();
        this.company = company;
        this.team = team;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }

    public void setRoles(String[] roles) {
        this.roles = Arrays.stream(roles).toList();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Company getCompany() {
        return company;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}