package com.example.demoweb.model;

import com.example.demoweb.constant.RequiredMessage;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern.Flag;

@Entity
@Table(name="employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    @NotBlank(message = RequiredMessage.REQUIRED_FIRSTNAME)
    private String firstname;

    @Column(name = "last_name")
    @NotBlank(message = RequiredMessage.REQUIRED_LASTNAME)
    private String lastname;

    @Column(name = "email_id")
    @NotBlank(message = RequiredMessage.REQUIRED_EMAIL)
    @Email(message = RequiredMessage.EMAIL_INVALID,flags = { Flag.CASE_INSENSITIVE })
    private String emailId;

}
