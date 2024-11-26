package id.my.hendisantika.mongock.migration;

import io.mongock.api.annotations.ChangeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

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

}
