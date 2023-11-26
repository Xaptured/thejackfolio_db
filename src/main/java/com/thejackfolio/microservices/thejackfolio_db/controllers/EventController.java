/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.entities.Viewers;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.DataBaseOperationException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.EventException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.MapperException;
import com.thejackfolio.microservices.thejackfolio_db.exceptions.TeamException;
import com.thejackfolio.microservices.thejackfolio_db.models.Event;
import com.thejackfolio.microservices.thejackfolio_db.models.Team;
import com.thejackfolio.microservices.thejackfolio_db.models.Viewer;
import com.thejackfolio.microservices.thejackfolio_db.services.EventService;
import com.thejackfolio.microservices.thejackfolio_db.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;

@Tag(name = "Event", description = "Event management APIs")
@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService service;

    @Operation(
            summary = "Save OR Update events",
            description = "Save or Update events and gives the same event response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-event")
    public ResponseEntity<Event> saveOrUpdateEvent(@RequestBody Event event, @RequestParam boolean isCreate, @RequestParam boolean isUpdate) {
        try {
            if(event == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            event = service.saveOrUpdateEvent(event, isCreate, isUpdate);
            event.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException | EventException exception) {
            event.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(event);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @Operation(
            summary = "Get event",
            description = "Get event with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-event/{name}")
    public ResponseEntity<Event> getEvent(@PathVariable String name) {
        Event event = null;
        try {
            if(name == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            event = service.getEvent(name);
            event.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            event = new Event();
            event.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(event);
        }
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @Operation(
            summary = "Save OR Update teams",
            description = "Save or Update teams and gives the same team response with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-team")
    public ResponseEntity<Team> saveOrUpdateTeam(@RequestBody Team team, @RequestParam boolean isCreate, @RequestParam boolean isUpdate) {
        try {
            if(team == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            team = service.saveOrUpdateTeam(team, isCreate, isUpdate);
            team.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException | TeamException exception) {
            team.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(team);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(team);
    }

    @Operation(
            summary = "Get team",
            description = "Get team with a message which defines whether the request is successful or not."
    )
    @GetMapping("/get-team/{name}")
    public ResponseEntity<Team> getTeam(@PathVariable String name) {
        Team team = null;
        try {
            if(name == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            team = service.getTeam(name);
            team.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            team = new Team();
            team.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(team);
        }
        return ResponseEntity.status(HttpStatus.OK).body(team);
    }

    @Operation(
            summary = "Save viewer",
            description = "Save viewer with a message which defines whether the request is successful or not."
    )
    @PostMapping("/save-viewer")
    public ResponseEntity<Viewer> saveViewer(@RequestBody Viewer viewer) {
        try {
            if(viewer == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.saveViewer(viewer);
            viewer.setMessage(StringConstants.REQUEST_PROCESSED);
        } catch (MapperException | DataBaseOperationException exception) {
            viewer.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(viewer);
        }
        return ResponseEntity.status(HttpStatus.OK).body(viewer);
    }

    @Operation(
            summary = "Is a viewer or not",
            description = "Is a viewer or not."
    )
    @GetMapping("/is-viewer")
    public ResponseEntity<Boolean> isViewer(@RequestParam String email, @RequestParam Integer eventId) {
        Boolean isViewer = false;
        try {
            if(email == null || eventId == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            isViewer = service.isViewer(email, eventId);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(isViewer);
        }
        return ResponseEntity.status(HttpStatus.OK).body(isViewer);
    }
}
