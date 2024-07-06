package com.example.accounts.controller;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.dto.ErrorResponseDto;
import com.example.accounts.dto.ResonseDto;
import com.example.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "CRUD API's for bank",
        description = "This API's allows you to create, fetch, update and delete accounts"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountsController {
    private final AccountsService accountsService;

    @Operation(summary = "Create a new account")
    @ApiResponse(responseCode = "201", description = "Account created successfully")
    @PostMapping("/create")
    public ResponseEntity<ResonseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResonseDto.builder()
                        .statusCode(AccountsConstants.STATUS_201)
                        .statusMessage(AccountsConstants.MESSAGE_201)
                        .build());

    }

    @Operation(summary = "Fetch account details")
    @ApiResponse(responseCode = "200", description = "Account details fetched successfully")
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        CustomerDto customerDto = accountsService.fetchAccountByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(summary = "Update account details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Failed to update account details")})

    @PutMapping("/update")
    public ResponseEntity<ResonseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResonseDto.builder()
                            .statusCode(AccountsConstants.STATUS_200)
                            .statusMessage(AccountsConstants.MESSAGE_200)
                            .build());
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(ResonseDto.builder()
                        .statusCode(AccountsConstants.STATUS_417)
                        .statusMessage(AccountsConstants.MESSAGE_417_UPDATE)
                        .build());
    }

    @Operation(summary = "Delete account")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
                    @ApiResponse(responseCode = "500", description = "Failed to delete account",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
            })

    @DeleteMapping("/delete")
    public ResponseEntity<ResonseDto> deleteAccount(@RequestParam String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResonseDto.builder()
                            .statusCode(AccountsConstants.STATUS_200)
                            .statusMessage(AccountsConstants.MESSAGE_200)
                            .build());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResonseDto.builder()
                        .statusCode(AccountsConstants.STATUS_500)
                        .statusMessage(AccountsConstants.MESSAGE_500)
                        .build());

    }


}
