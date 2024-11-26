package id.my.hendisantika.mongock.migration;

import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongock
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 26/11/24
 * Time: 17.10
 * To change this template use File | Settings | File Templates.
 */
@Log4j2
@ChangeUnit(id = "customer-initializer", order = "001", author = "myself")
public class CustomerInitialization {
    @BeforeExecution
    public void beforeExecution(MongoTemplate mongoTemplate) {
        log.info("######### BeforeExecution!!!");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution(MongoTemplate mongoTemplate) {
        log.info("######### Rollback - BeforeExecution!!!");
    }

    @Execution
    public void execution(CustomerRepository repository) {
        log.info("######### Initialize data!!!");
        List<Customer> customerFlux = Arrays.asList("Madhura", "Josh", "Olga").stream()
                .map(Customer::new)
                .map(repository::save)
                .toList();
        log.info(customerFlux);
    }

    // This isn't a good rollback :o
    @RollbackExecution
    public void rollbackExecution(CustomerRepository repository) {
        log.info("######### Rollback Execution!!!");
        repository.deleteAll();
    }
}
