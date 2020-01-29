package io.pivotal.pal.tracker.paltracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.TimerTask;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    InMemoryTimeEntryRepository repository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        repository.create(timeEntryToCreate);
        return new ResponseEntity<>(timeEntryToCreate,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(repository.list(),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry expected) {

        repository.update(id,expected);
        if(expected != null) {
            return new ResponseEntity<>(expected, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id) {

        TimeEntry timerTask =  repository.find(id);

        if(timerTask != null){
            return new ResponseEntity<>(timerTask,HttpStatus.OK);
        }else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
