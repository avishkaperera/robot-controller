package de.idealo.robotcontrollerapi.api.controller;

import de.idealo.robotcontrollerapi.api.model.PositionRequest;
import de.idealo.robotcontrollerapi.api.model.Robot;
import de.idealo.robotcontrollerapi.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static de.idealo.robotcontrollerapi.util.Mapper.toResponse;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin
public class PositionController {

    private final PositionService positionService;

    @PostMapping("/position")
    public ResponseEntity<Robot> moveRobot(@RequestBody @Valid PositionRequest request) {
        log.info("Received request to re-position robot [{}]", request);
        return ResponseEntity.ok(toResponse(positionService.executeScript(request.script())));
    }
}
