package de.angelasensio.eventsvc.controller;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import de.angelasensio.eventsvc.data.Event;
import de.angelasensio.eventsvc.repository.EventRepository;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Event> getEventsForEmployee(@PathVariable UUID id) {
        requireNonNull(id, "id cannot be null");
        return eventRepository.findByEmployeeUuidOrderByTimestampAsc(id);
    }
}
