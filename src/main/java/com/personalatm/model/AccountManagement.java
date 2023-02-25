package com.personalatm.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(schema = "atm")
public class AccountManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    private String bankName;
    private String userAccountName;
    private String accountNumber;
    private String cvv2;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date expirationDate;
    @CreationTimestamp
    private Date creationDate;
    private boolean activated = false;
    private Double balance;

    /*relationship*/
    @ManyToOne
    private User user;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "account_atm",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "atm_id"))
    private Set<Atm> atms = new HashSet<>();
    /*end of relationship*/
}