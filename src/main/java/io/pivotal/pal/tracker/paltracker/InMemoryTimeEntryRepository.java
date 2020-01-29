package io.pivotal.pal.tracker.paltracker;

import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository {


    private static Map timeEntyMap = new HashMap<String,TimeEntry>();

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        if(timeEntry != null) {
            timeEntyMap.put(timeEntry.getId(), timeEntry);
        }

        return (TimeEntry) timeEntyMap.get(timeEntry.getId());
    }

    @Override
    public TimeEntry find(long timeEntryId) {

        if(timeEntryId >0){
            return (TimeEntry) timeEntyMap.get(timeEntryId);
        }
            return  null;
    }

    @Override
    public List<TimeEntry> list() {

        return new ArrayList<TimeEntry>(timeEntyMap.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        TimeEntry existingTimeEntry = (TimeEntry) timeEntyMap.get(id);

        existingTimeEntry.setDate(timeEntry.getDate());
        existingTimeEntry.setHours(timeEntry.getHours());
        existingTimeEntry.setProjectId(timeEntry.getProjectId());
        existingTimeEntry.setUserId(timeEntry.getUserId());

        timeEntyMap.put(id,timeEntry);

        return existingTimeEntry;

    }

    @Override
    public void delete(long timeEntryId) {

       TimeEntry timeEntry = (TimeEntry) timeEntyMap.get(timeEntryId);
        timeEntyMap.remove(timeEntry.getId());

    }
}
