package main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.responce.Work;

import java.util.List;


@RestController
public class TodoListController {
    private final WorkService workService;

    public TodoListController(WorkService workService) {
        this.workService = workService;
    }


    @GetMapping("/books/")
    public List<Work> list() {
        return workService.list();
    }

    @PostMapping("/books/")
    public int add(Work work) {
        return workService.add(work);
    }

    @DeleteMapping("/books/")
    public void deleteAllWorks() {
        workService.deleteAll();
    }

    @DeleteMapping("/books/id={id}")
    public ResponseEntity delete(@PathVariable int id) {
        return workService.delete(id);
    }

    @GetMapping("/books/id={id}")
    public ResponseEntity get(@PathVariable int id) {
        return workService.get(id);
    }

    @PutMapping("/books/")
    public int putWork(Work work){
        return workService.putWork(work);
    }

}
