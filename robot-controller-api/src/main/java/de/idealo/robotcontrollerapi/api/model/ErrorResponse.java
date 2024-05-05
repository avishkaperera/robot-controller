package de.idealo.robotcontrollerapi.api.model;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int code,
        String message
) {
}
