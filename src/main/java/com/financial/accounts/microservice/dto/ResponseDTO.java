package com.financial.accounts.microservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(
        name = "Response",
        description = "Schema holds the details of the response such as status code and status message")
@AllArgsConstructor
public class ResponseDTO {
    @Schema(
            description = "Status code of the response",
            example = "200 OK"
    )
    private String statusCode;
    @Schema(
            description = "Status message of the response",
            example = "Customer details retrieved successfully"
    )
    private String statusMessage;
}
