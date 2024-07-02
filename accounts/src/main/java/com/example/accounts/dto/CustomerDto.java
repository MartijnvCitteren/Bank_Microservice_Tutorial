package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Schema(name = "Customer", description = "Customer details")
@Builder
public record CustomerDto(
        @Schema(description = "Customer name", example = "John Doe")
       @NotEmpty(message = "Name cannot be empty")
       String name,

         @Schema(description = "Customer email", example = "john.doe@email.com")
       @Email(message = "Please enter a valid Email")
       @NotEmpty
       String email,

        @Schema(description = "Customer mobile number", example = "0612345678")
       @NotBlank(message = "Mobile number cannot be empty")
       @Pattern(regexp = "^06\\d{8}$", message = "Mobile number must start with 06 and have exactly 10 digits")
       String mobileNumber,

       @Schema(description = "Customer accounts details")
       AccountsDto accountsDto
) {
}
