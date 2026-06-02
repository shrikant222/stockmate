package com.stockmate.model;

import com.stockmate.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer_account")
public class CustomerAccount extends BaseEntity {

    @Column(nullable = false, updatable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private BigDecimal debtAmount = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal creditAmount = BigDecimal.ZERO;

    private String address;

    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;

    @OneToMany()
    private final List<Transaction> transactions = new ArrayList<>();
}
