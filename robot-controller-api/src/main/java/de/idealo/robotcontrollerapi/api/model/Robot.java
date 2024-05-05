package de.idealo.robotcontrollerapi.api.model;

import de.idealo.robotcontrollerapi.model.Direction;
import lombok.Builder;

@Builder(setterPrefix = "with")
public record Robot(
        int x,
        int y,
        Direction direction
) {
}
