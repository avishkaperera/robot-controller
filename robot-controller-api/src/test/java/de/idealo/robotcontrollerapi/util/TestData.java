package de.idealo.robotcontrollerapi.util;

import de.idealo.robotcontrollerapi.api.model.PositionRequest;
import de.idealo.robotcontrollerapi.api.model.Robot;
import de.idealo.robotcontrollerapi.model.Direction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestData {

    public static String validTestData() {
        return """
                POSITION 1 3 EAST
                FORWARD 3
                WAIT
                TURNAROUND
                FORWARD 1
                RIGHT
                FORWARD 2
                """;
    }

    public static PositionRequest aPositionRequest() {
        return new PositionRequest("POSITION 1 3 EAST\nFORWARD 3\nWAIT\nTURNAROUND\nFORWARD 1\nRIGHT\nFORWARD 2");
    }

    public static Robot aRobot() {
        return Robot.builder()
                .withY(1)
                .withX(3)
                .withDirection(Direction.NORTH)
                .build();
    }
}
