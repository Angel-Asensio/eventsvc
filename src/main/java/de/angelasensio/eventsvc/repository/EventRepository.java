package de.angelasensio.eventsvc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.angelasensio.eventsvc.data.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    List<Event> findByEmployeeUuidOrderByTimestampAsc(UUID employeeUuid);
}
