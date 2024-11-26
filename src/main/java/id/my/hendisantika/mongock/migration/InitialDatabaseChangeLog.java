package id.my.hendisantika.mongock.migration;

import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongock
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 26/11/24
 * Time: 16.28
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@ChangeUnit(order = "001", id = "user-seeder", author = "hendisantika")
public class InitialDatabaseChangeLog {
    @BeforeExecution
    public void beforeSeedDatabase() {
        log.info("before seeding database...");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution() {
        log.info("rollback before seeding database...");
    }

    @Execution
    public void executionMethodName() {
        log.info("execution seeding database...");
    }
}
