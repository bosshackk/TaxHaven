package com.quad.TaxHaven.domain.user;

import com.quad.TaxHaven.domain.util.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@MappedSuperclass
public class User {
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private Boolean isEmailVerified;
    private String userName;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
