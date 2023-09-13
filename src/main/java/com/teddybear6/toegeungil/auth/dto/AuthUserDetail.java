package com.teddybear6.toegeungil.auth.dto;

import com.teddybear6.toegeungil.user.entity.UserEntity;
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
