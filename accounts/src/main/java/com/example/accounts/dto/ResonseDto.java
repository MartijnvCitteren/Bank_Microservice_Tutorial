package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "Response", description = "Response details")
@Builder
public record ResonseDto(
        @Schema(description = "sample response code", example = "200")
        String statusCode,
        @Schema(description = "sample response message", example = "Success")
        String statusMessage
) {
}
