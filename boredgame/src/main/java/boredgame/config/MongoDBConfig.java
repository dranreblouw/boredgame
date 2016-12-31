package boredgame.config;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBConfig {

    /*
     * Factory bean that creates the com.mongodb.Mongo instance
     */
    public
    @Bean
    Mongo mongo() throws Exception {
        return new Mongo("localhost");
    }

    public
    @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "boredgame");
    }
}
