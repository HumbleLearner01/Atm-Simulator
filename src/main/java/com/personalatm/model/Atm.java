package com.personalatm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "atm")
public class Atm {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atmId;

    private String accountNumber;
    private String password;

    /*relationship*/
    @ManyToMany(mappedBy = "atms")
    private Set<AccountManagement> accountManagements = new HashSet<>();
    /*end of relationship*/
}