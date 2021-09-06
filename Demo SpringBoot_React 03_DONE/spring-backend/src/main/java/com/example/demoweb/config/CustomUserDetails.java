package com.example.demoweb.config;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private static final long serialVersionUID = -3531439484732724621L;

    private final String email;
    private final String phone;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                             String email, String phone) {
        super(username, password, authorities);
        this.email = email;
        this.phone = phone;
    }



    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

}
