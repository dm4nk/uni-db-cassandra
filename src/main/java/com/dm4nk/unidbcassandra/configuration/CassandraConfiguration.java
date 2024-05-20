package com.dm4nk.unidbcassandra.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;

//@Configuration
public class CassandraConfiguration /*extends AbstractCassandraConfiguration*/ {

//    @Override
    public String getKeyspaceName() {
        return "testKeySpace";
    }

//    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.RECREATE_DROP_UNUSED;
    }
}
