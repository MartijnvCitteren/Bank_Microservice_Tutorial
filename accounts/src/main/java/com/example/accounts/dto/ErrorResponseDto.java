package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Schema(name = "ErrorResponse", description = "Error Response details")
public record ErrorResponseDto(
        @Schema(description = "API path", example = "/accounts")
        String apiPath,
        @Schema(description = "HTTP error code", example = "404")
        HttpStatus errorCode,
        @Schema(description = "Error message", example = "Resource not found")
        String errorMessage,
        @Schema(description = "Error time", example = "2021-08-25T10:15:30")
        LocalDateTime errorTime
) {
}
