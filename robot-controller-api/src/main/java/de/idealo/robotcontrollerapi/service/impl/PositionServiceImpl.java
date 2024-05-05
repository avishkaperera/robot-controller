package de.idealo.robotcontrollerapi.service.impl;

import de.idealo.robotcontrollerapi.config.AppConfig.GridConstraint;
import de.idealo.robotcontrollerapi.model.Direction;
import de.idealo.robotcontrollerapi.model.Position;
import de.idealo.robotcontrollerapi.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Predicate;

import static de.idealo.robotcontrollerapi.util.Constants.COMMAND;
import static de.idealo.robotcontrollerapi.util.Constants.FORWARD_COMMAND;
import static de.idealo.robotcontrollerapi.util.Constants.INIT_POSITION_COMMAND;
import static de.idealo.robotcontrollerapi.util.Constants.TURNAROUND_COMMAND;
import static de.idealo.robotcontrollerapi.util.Constants.TURN_AROUND_ANGLE;
import static de.idealo.robotcontrollerapi.util.Constants.TURN_LEFT_ANGLE;
import static de.idealo.robotcontrollerapi.util.Constants.TURN_LEFT_COMMAND;
import static de.idealo.robotcontrollerapi.util.Constants.TURN_RIGHT_ANGLE;
import static de.idealo.robotcontrollerapi.util.Constants.TURN_RIGHT_COMMAND;
import static de.idealo.robotcontrollerapi.util.Constants.WAIT_COMMAND;
import static de.idealo.robotcontrollerapi.util.Constants.WHITE_SPACE;

@Service
@Slf4j
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final GridConstraint constraint;

    @Override
    public Position executeScript(String controllerScript) {
        // Instantiate with initial grid position
        Position position = new Position(Direction.EAST, 0, 0);
        controllerScript.lines().filter(validateLine).forEach(line -> processAction(line, position));
        return position;
    }

    private void processAction(String action, Position position) {
        String[] actionSteps = action.split(WHITE_SPACE);

        switch (actionSteps[COMMAND].trim().toUpperCase()) {
            case INIT_POSITION_COMMAND:
                setInitialPosition(actionSteps, position);
                break;
            case FORWARD_COMMAND:
                moveForward(actionSteps, position);
                break;
            case WAIT_COMMAND:
                log.info("Nothing to do here. Command is to [{}]", actionSteps[COMMAND]);
                break;
            case TURNAROUND_COMMAND:
                turnaround(position);
                break;
            case TURN_RIGHT_COMMAND:
                turnRight(position);
                break;
            case TURN_LEFT_COMMAND:
                turnLeft(position);
                break;
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }

    private void setInitialPosition(String[] commands, Position position) {
        if (commands.length < 4) {
            throw new IllegalArgumentException("Incorrect number of commands provided for initialization");
        }

        final int x = Integer.parseInt(commands[1]);
        final int y = Integer.parseInt(commands[2]);
        final Direction direction = Direction.valueOf(commands[3].trim().toUpperCase());
        gridValidator.accept(x, y, constraint);
        position.setX(x);
        position.setY(y);
        position.setDirection(direction);
    }

    private void turnLeft(Position position) {
        turnRobot(position, TURN_LEFT_ANGLE);
    }

    private void turnRight(Position position) {
        turnRobot(position, TURN_RIGHT_ANGLE);
    }

    private void turnaround(Position position) {
        turnRobot(position, TURN_AROUND_ANGLE);
    }

    private void turnRobot(Position position, int angle) {
        int newDirectionAngle = (position.getDirection().getAngle() + angle) % 360;
        position.setDirection(Direction.getByAngle(newDirectionAngle));
    }

    private void moveForward(String[] commands, Position position) {
        if (commands.length < 2) {
            throw new IllegalArgumentException("Invalid number of commands provided for move forward action");
        }

        int stepCount = Integer.parseInt(commands[1]);

        if (position.getDirection().equals(Direction.EAST)) {
            gridValidator.accept((position.getX() + stepCount), position.getY(), constraint);
            position.setX(position.getX() + stepCount);
        } else if (position.getDirection().equals(Direction.WEST)) {
            gridValidator.accept((position.getX() - stepCount), position.getY(), constraint);
            position.setX(position.getX() - stepCount);
        } else if (position.getDirection().equals(Direction.NORTH)) {
            gridValidator.accept(position.getX(), (position.getY() - stepCount), constraint);
            position.setY(position.getY() - stepCount);
        } else if (position.getDirection().equals(Direction.SOUTH)) {
            gridValidator.accept(position.getX(), (position.getY() + stepCount), constraint);
            position.setY(position.getY() + stepCount);
        }
    }

    private final Predicate<String> validateLine = line -> Objects.nonNull(line) && !line.isBlank();

    private final GridValidator gridValidator = (x, y, constraint) -> {
        if (x < 0 || x > constraint.gridXLimit() || y < 0 || y > constraint.gridYLimit()) {
            throw new IllegalArgumentException("Invalid commands provided. Grid out of bound.");
        }
    };

    @FunctionalInterface
    private interface GridValidator {
        void accept(Integer x, Integer y, GridConstraint constraint);
    }
}
