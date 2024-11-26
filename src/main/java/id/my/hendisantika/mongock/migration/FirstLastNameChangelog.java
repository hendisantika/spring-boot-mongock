package id.my.hendisantika.mongock.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongock
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 26/11/24
 * Time: 17.06
 * To change this template use File | Settings | File Templates.
 */
@ChangeUnit(id = "FirstLastNameChangelog", order = "1", author = "omaryaya")
public class FirstLastNameChangelog {

    Logger logger = LoggerFactory.getLogger(FirstLastNameChangelog.class);
    AtomicInteger successfulUserUpdatesCounter = new AtomicInteger();

    @Execution
    public void setFirstAndLastNameToUsers(MongoTemplate mongoTemplate) {
        Query query = new Query(
                where("fullName").ne(null)
                        .andOperator(where("firstName").is(null),
                                where("lastName").is(null))
        );

        query.fields().include("_id", "fullName");

        long usersWithoutFirstAndLastName = mongoTemplate.count(query, User.class);
        query.limit(100); // set after counting all users to avoid always getting 100 as the maximum number of users


        List<User> users = mongoTemplate.find(query, User.class);
        while (users != null || users.getSize() != 0) {
            users.forEach(user -> {
                try {
                    Criteria criteria = where("_id").is(user.getId());
                    setNamesForUser(user);
                    String[] names = splitNamesForUser(user);
                    String firstName = names[0], lastName = names[1];
                    Update update = new Update()
                            .set("firstName", firstName)
                            .set("lastName", lastName);
                    mongoTemplate.findAndModify(new Query(criteria), update, User.class);
                    successfulUserUpdatesCounter.getAndIncrement();
                } catch (Exception ex) {
                    logger.error(String.format("Faield to set firstName & lastName for user with id %s", user.getId()), ex);
                }
            });

            users = mongoTemplate.find(query, User.class);
        }

        logger.info("First and last names set for {} users out of {} total.", successfulUserUpdatesCounter, usersWithoutFirstAndLastName);

    }

    private String[] splitNamesForUser(User user) {
        if (user.getFullName() == null || user.getFullName().isEmpty() || !user.getFullName().contains(" ")) {
            throw new ParseNameException("Failed to parse the user's name");
        }
        return user.getFullName().split(" ");
    }
}
