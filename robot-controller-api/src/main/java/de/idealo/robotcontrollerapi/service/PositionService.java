package de.idealo.robotcontrollerapi.service;

import de.idealo.robotcontrollerapi.model.Position;

public interface PositionService {

    Position executeScript(String controllerScript);
}
