package de.idealo.robotcontrollerapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.idealo.robotcontrollerapi.model.Direction;
import de.idealo.robotcontrollerapi.model.Position;
import de.idealo.robotcontrollerapi.service.impl.PositionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static de.idealo.robotcontrollerapi.util.TestData.aPositionRequest;
import static de.idealo.robotcontrollerapi.util.TestData.aRobot;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PositionController.class)
class PositionControllerIT {

    @MockBean
    private PositionServiceImpl positionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void moveRobot_andValidate_returnsSuccessOutcome() throws Exception {
        when(positionService.executeScript(any())).thenReturn(new Position(Direction.NORTH, 3, 1));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/position")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aPositionRequest())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(aRobot())));
    }
}