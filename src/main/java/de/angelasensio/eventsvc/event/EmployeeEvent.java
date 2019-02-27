package de.angelasensio.eventsvc.event;

import java.util.UUID;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class EmployeeEvent {

    private UUID employeeUuid;
    private String operation;
    private Long timestamp;

}
