package de.idealo.robotcontrollerapi.api.model;

import jakarta.validation.constraints.NotBlank;

public record PositionRequest(
        @NotBlank String script
) {
}
