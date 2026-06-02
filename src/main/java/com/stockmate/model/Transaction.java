package com.stockmate.model;

import com.stockmate.enums.PaymentMode;
import com.stockmate.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity{

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerAccount customerAccount;

}
