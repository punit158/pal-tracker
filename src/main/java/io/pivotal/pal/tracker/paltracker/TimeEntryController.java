package io.pivotal.pal.tracker.paltracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TimeEntryController {

    @Autowired
    InMemoryTimeEntryRepository repository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
    }

    @PostMapping("/addTimeTracker")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        repository.create(timeEntryToCreate);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/getAllTimeTracker")
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(repository.list());
    }

    @PutMapping("updateTimeTracker/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody TimeEntry expected) {

        repository.update(id,expected);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("deleteTimeTracker/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        repository.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("findTimeTracker/{id}")
    public ResponseEntity read(@PathVariable(value = "id") Long id) {

        return ResponseEntity.ok(repository.find(id));

    }
}