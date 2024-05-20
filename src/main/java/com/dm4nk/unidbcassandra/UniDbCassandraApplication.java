package com.dm4nk.unidbcassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories
public class UniDbCassandraApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniDbCassandraApplication.class, args);
    }
}
