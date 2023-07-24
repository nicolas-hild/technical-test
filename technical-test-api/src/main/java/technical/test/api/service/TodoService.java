package technical.test.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import technical.test.api.entity.Todo;
import technical.test.api.repository.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Mono<Todo> getTodoById(String id) {
        return todoRepository.findById(id);
    }

    public Flux<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Mono<Todo> updateTodo(String id, Todo updatedTodo) {
        return todoRepository.findById(id).flatMap(todo -> {
            todo.setDescription(updatedTodo.getDescription());
            todo.setDone(updatedTodo.isDone());
            todo.setTitle(updatedTodo.getTitle());
            return todoRepository.save(todo);
        });
    }

    public Mono<Void> deleteTodo(String id) {
        return todoRepository.deleteById(id);
    }

    public Mono<Todo> createTodo(Todo todo) {
        return todoRepository.save(todo);
    }
}
