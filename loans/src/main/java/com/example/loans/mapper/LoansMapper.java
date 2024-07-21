package com.example.loans.mapper;

import com.example.loans.dto.LoansDto;
import com.example.loans.entity.Loans;

public class LoansMapper {

    public static LoansDto toLoansDto(Loans loans) {
        return LoansDto.builder()
                .mobileNumber(loans.getMobileNumber())
                .loanNumber(loans.getLoanNumber())
                .loanType(loans.getLoanType())
                .totalLoan(loans.getTotalLoan())
                .amountPaid(loans.getAmountPaid())
                .outstandingAmount(loans.getOutstandingAmount())
                .build();

    }

    public static Loans toLoans(LoansDto loansDto) {
        return Loans.builder()
                .mobileNumber(loansDto.mobileNumber())
                .loanNumber(loansDto.loanNumber())
                .loanType(loansDto.loanType())
                .totalLoan(loansDto.totalLoan())
                .amountPaid(loansDto.amountPaid())
                .outstandingAmount(loansDto.outstandingAmount())
                .build();
    }

}
