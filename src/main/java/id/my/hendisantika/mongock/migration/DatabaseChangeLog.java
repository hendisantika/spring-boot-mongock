package id.my.hendisantika.mongock.migration;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import id.my.hendisantika.mongock.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongock
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 26/11/24
 * Time: 17.58
 * To change this template use File | Settings | File Templates.
 */
@ChangeLog
public class DatabaseChangeLog {
    @ChangeSet(order = "001", id = "seedDataBaseWithUsers", author = "hendisantika")
    public void seedDataBase(MongockTemplate mongockTemplate) {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setName("ITADORI YUJI");
        user1.setEmail("yuji@yopmail.com");

        User user2 = new User();
        user2.setName("Megumi");
        user2.setEmail("fushiguro@yopmail.com");
        userList.add(user1);
        userList.add(user2);
        mongockTemplate.insert(userList, "users");
    }
}
