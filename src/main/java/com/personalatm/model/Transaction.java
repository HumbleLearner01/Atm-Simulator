package com.personalatm.model;

import com.personalatm.helper.enums.TransactionType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(schema = "atm")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
    @CreationTimestamp
    private Date date;
    private BigDecimal amount;
    private String accountNumber;
    private TransactionType transactionType;
    private String description;
}