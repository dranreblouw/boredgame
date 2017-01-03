package boredgame;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.util.Arrays;

@Configuration
public class MongoDBConfig extends AbstractMongoConfiguration {

    @Override
    public String getDatabaseName() {
        return "boredgame";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(new ServerAddress("ds151228.mlab.com", 51228),
                Arrays.asList(MongoCredential.createCredential("server","boredgame", "password1".toCharArray())));
    }
}
