package de.idealo.robotcontrollerapi.util;

import de.idealo.robotcontrollerapi.api.model.Robot;
import de.idealo.robotcontrollerapi.model.Position;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    public static Robot toResponse(Position position) {
        return Robot.builder()
                .withX(position.getX())
                .withY(position.getY())
                .withDirection(position.getDirection())
                .build();
    }
}
