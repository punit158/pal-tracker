package io.pivotal.pal.tracker.paltracker;

import io.pivotal.pal.tracker.paltracker.TimeEntry;

import java.util.List;
//interface
public interface TimeEntryRepository {
    TimeEntry create(TimeEntry timeEntry);
    TimeEntry find(Long id);
    List<TimeEntry> list();
    TimeEntry update(Long id, TimeEntry timeEntry);
    void delete(Long id);
}