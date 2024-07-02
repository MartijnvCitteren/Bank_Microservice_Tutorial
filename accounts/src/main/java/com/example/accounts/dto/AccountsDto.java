package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "Accounts", description = "Accounts details")
@Builder
public record AccountsDto(
        @Schema(description = "accountNumber", example = "1234567890")
        Long accountNumber,
        @Schema(description = "accountType", example = "Savings")
        String accountType,
        @Schema(description = "branchAddress", example = "123 Main St, New York, NY 10030")
        String branchAddress
) {
}
