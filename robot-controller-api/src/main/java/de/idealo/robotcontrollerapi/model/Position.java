package de.idealo.robotcontrollerapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Position {
    private Direction direction;
    private int x;
    private int y;
}
