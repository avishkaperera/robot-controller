package de.idealo.robotcontrollerapi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum Direction {
    NORTH(0),
    EAST(90),
    SOUTH(180),
    WEST(270);

    private static final Map<Integer, Direction> BY_ANGLE = new HashMap<>();

    static {
        for (Direction value : values()) {
            BY_ANGLE.put(value.angle, value);
        }
    }

    @Getter
    private final int angle;

    public static Direction getByAngle(int angle) {
        return BY_ANGLE.get(angle);
    }
}
