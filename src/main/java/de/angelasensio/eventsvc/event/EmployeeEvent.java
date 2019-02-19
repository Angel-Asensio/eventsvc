package de.angelasensio.eventsvc.event;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EmployeeEvent {

    private UUID employeeUuid;
    private String operation;
    private Long timestamp;

}
