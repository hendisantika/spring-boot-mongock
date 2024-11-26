package id.my.hendisantika.mongock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongock
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 26/11/24
 * Time: 17.07
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String fullName;
    private String firstName;
    private String lastName;
    private Date dob;
}
