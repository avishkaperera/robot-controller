package de.idealo.robotcontrollerapi.service.impl;

import de.idealo.robotcontrollerapi.config.AppConfig.GridConstraint;
import de.idealo.robotcontrollerapi.model.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static de.idealo.robotcontrollerapi.model.Direction.EAST;
import static de.idealo.robotcontrollerapi.model.Direction.NORTH;
import static de.idealo.robotcontrollerapi.util.TestData.validTestData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PositionServiceImplTest {

    @Mock
    private GridConstraint gridConstraint;

    @InjectMocks
    private PositionServiceImpl positionService;

    @Test
    void executeScript_andValidate_successOutcome() {
        when(gridConstraint.gridXLimit()).thenReturn(4);
        when(gridConstraint.gridYLimit()).thenReturn(4);

        Position position = positionService.executeScript(validTestData());
        assertThat(position.getDirection()).isEqualTo(NORTH);
        assertThat(position.getX()).isEqualTo(3);
        assertThat(position.getY()).isEqualTo(1);
    }

    @Test
    void executeScript_andValidate_blankScriptReturnsDefaultPosition() {
        Position position = positionService.executeScript("");
        assertThat(position.getDirection()).isEqualTo(EAST);
        assertThat(position.getX()).isZero();
        assertThat(position.getY()).isZero();
    }

    @Test
    void executeScript_andValidate_InvalidScriptThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> positionService.executeScript("dummy text 1 2"));
        assertThat(exception.getMessage()).isEqualTo("Unknown command");
    }
}