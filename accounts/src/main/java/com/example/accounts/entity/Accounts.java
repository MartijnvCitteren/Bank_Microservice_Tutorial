package com.example.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accounts extends BaseEntity {
    @Id
    private Long accountNumber;
    private Long customerId;
    private String accountType;
    private String branchAddress;
}
