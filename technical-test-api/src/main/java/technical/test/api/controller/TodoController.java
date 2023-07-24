package technical.test.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.entity.Todo;
import technical.test.api.service.TodoService;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public Flux<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping("/todos")
    public Mono<Todo> addTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @GetMapping("/todos/{id}")
    public Mono<Todo> getTodoById(@PathVariable String id) {
        return todoService.getTodoById(id);
    }

    @PutMapping("/todos/{id}")
    public Mono<Todo> updateTodo(@PathVariable String id, @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }

    @DeleteMapping("/todos/{id}")
    public Mono<Void> deletetodo(@PathVariable String id) {
        return todoService.deleteTodo(id);
    }

}
