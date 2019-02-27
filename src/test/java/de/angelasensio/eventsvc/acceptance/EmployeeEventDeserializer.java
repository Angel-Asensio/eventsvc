package de.angelasensio.eventsvc.acceptance;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import de.angelasensio.eventsvc.event.EmployeeEvent;


public class EmployeeEventDeserializer extends JsonDeserializer<EmployeeEvent> {
}