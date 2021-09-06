package com.example.demoweb.model;

import com.example.demoweb.constant.RequiredMessage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = RequiredMessage.REQUIRED_USERNAME)
    private String username;

    @Column
    @NotBlank(message = RequiredMessage.REQUIRED_PASSWORD)
    private String password;

    @Column
    @NotBlank(message = RequiredMessage.REQUIRED_EMAIL)
    @Email(message = RequiredMessage.EMAIL_INVALID,flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    @Column
    @Size(min = 10, max = 10, message = "PhoneNumber should be 10 digit")
    @Pattern(regexp="(^$|[0-9]{10})", message = "PhoneNumber is invalid")
    private String phone;
}

