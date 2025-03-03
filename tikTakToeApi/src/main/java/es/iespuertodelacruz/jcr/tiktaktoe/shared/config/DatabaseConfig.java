package es.iespuertodelacruz.jcr.tiktaktoe.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(
                "jdbc:mysql://localhost:3306/tiktaktoe?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("jonaykb"); // ESTO root
        dataSource.setPassword("1q2w3e4r");
        return dataSource;
    }

    @Bean
    @Profile("production")
    public DataSource dataSourceProduction() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource
                .setUrl("jdbc:mysql://db:3306/tiktaktoeProduction?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("1q2w3e4r");
        return dataSource;
    }

    @Bean
    @Profile("test")
    public DataSource dataSourceTest() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:tiktaktoe;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    @Profile({ "dev", "production" })
    public MongoClient mongoClientProd() {
        return MongoClients.create("mongodb+srv://jonaykb:DuVxEWoh7suhWAWG@tiktaktoe.osave.mongodb.net/?retryWrites=true&w=majority&appName=TikTakToe");
    }

    @Bean("mongoTemplate")
    @Profile({ "dev", "production" })
    public MongoTemplate mongoTemplateProd() {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClientProd(), "tikTaktoe"));
    }
}
