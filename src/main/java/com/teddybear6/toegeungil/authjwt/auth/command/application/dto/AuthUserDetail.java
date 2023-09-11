package com.teddybear6.toegeungil.authjwt.auth.command.application.dto;

import com.teddybear6.toegeungil.authjwt.user.command.domain.model.UserEntity;
import org.springframework.cglib.core.TypeUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AuthUserDetail implements UserDetails {
    private UserEntity userEntity;
    private AuthUserDetail getAuthorities;

    public AuthUserDetail(){

    }

    public AuthUserDetail(UserEntity userEntity) {this.userEntity=userEntity;}

    public UserEntity getUserEntity() {
        return userEntity;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoleList().forEach(r -> authorities.add(() -> r));
        return authorities;
    }


    @Override
    public String getPassword() {return userEntity.getUserPassword();}

    @Override
    public String getUsername() {return userEntity.getUserName();}

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
