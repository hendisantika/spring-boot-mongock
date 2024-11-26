package id.my.hendisantika.mongock.repository;

import id.my.hendisantika.mongock.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

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
public interface UserRepository extends MongoRepository<User, ObjectId> {
}
