package br.com.fuzeto.bankaccountcommand.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController {

    private EventStore eventStore;

    @GetMapping
    @RequestMapping("/{aggregateId}")
    public List<Object> listEvents(@PathVariable String aggregateId) {
        return eventStore.readEvents(aggregateId)
                .asStream()
                .collect(Collectors.toList());
    }
}
