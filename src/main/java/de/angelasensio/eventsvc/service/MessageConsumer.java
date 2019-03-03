package de.angelasensio.eventsvc.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import de.angelasensio.eventsvc.data.Event;
import de.angelasensio.eventsvc.event.EmployeeEvent;
import de.angelasensio.eventsvc.repository.EventRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageConsumer {

    private final EventRepository eventRepository;

    @KafkaListener(topics = "${kafka.topic}", containerFactory = "jsonKafkaListenerContainerFactory")
    public void consumerMessage(@Payload EmployeeEvent event) {
        LOG.info("consumer received event: {}", event.toString());
        Event eventToSave = new Event();
        eventToSave.setEmployeeUuid(event.getEmployeeUuid());
        eventToSave.setOperation(event.getOperation());
        eventToSave.setTimestamp(event.getTimestamp());
        eventRepository.save(eventToSave);
    }

}
