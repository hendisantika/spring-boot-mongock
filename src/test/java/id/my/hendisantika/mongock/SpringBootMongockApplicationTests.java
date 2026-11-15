package id.my.hendisantika.mongock;

import id.my.hendisantika.mongock.model.User;
import id.my.hendisantika.mongock.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class SpringBootMongockApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void mongockMigrationSeedsUsers() {
        assertThat(userRepository.findAll())
                .extracting(User::getEmail)
                .contains("yuji@yopmail.com", "fushiguro@yopmail.com");
    }

}
