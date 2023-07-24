package technical.test.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import technical.test.api.entity.Todo;

public interface TodoRepository extends ReactiveMongoRepository<Todo, String> {
}
