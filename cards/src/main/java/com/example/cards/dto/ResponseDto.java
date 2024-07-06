package com.example.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@Builder
public record ResponseDto (

    @Schema(description = "Status code in the response")
    String statusCode,

    @Schema(description = "Status message in the response")
    String statusMsg
){}
