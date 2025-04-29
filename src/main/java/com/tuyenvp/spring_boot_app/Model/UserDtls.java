package com.tuyenvp.spring_boot_app.Model;

import jakarta.persistence.*;

import java.util.Date;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_dtls")
public class UserDtls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "mobile_number")
    private String mobileNumber;

    private String email;

    private String province;

    private String district;

    private String commune;

    private String address;

    private String password;

    @Column(name = "profile_image")
    private String profileImage;

    private String role;

    @Column(name = "is_enable")
    private Boolean isEnable;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "failed_attempt")
    private Integer failedAttempt;

    @Column(name = "lock_time")
    private Date lockTime;

    @Column(name = "resetToken")
    private String resetToken;

    public boolean isLoggedIn() {
        // Add your login logic here (e.g., session or token verification)
        return this.isLoggedIn();
    }


}
