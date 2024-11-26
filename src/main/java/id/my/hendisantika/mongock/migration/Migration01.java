package id.my.hendisantika.mongock.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongock
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 26/11/24
 * Time: 17.12
 * To change this template use File | Settings | File Templates.
 */
@AllArgsConstructor
@ChangeUnit(id = "Migration01", order = "1", author = "admin")
public class Migration01 {
    private final MongoTemplate mongoTemplate;
    private final UserRepository UserRepository;

    @Execution
    public void migrationMethod() {
        // it will run only once
        mongoTemplate.save(new User("Max", "max@gmail.com", "12345678"), "user");
        mongoTemplate.save(new User("Max", "max@gmail.com", "12345678"), "user"))
        // there are multiple ways by which we can save data here, we can even use repositories here.
        // we can even use a csv file and put data into the db when this migration runs.
    }

    @RollbackExecution
    public void rollback() {
        // for rollback
        userRepository.deleteAll()
    }
}
