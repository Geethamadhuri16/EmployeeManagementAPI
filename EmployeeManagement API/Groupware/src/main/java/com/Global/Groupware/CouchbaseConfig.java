package com.Global.Groupware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories(basePackages = "com.Global.Groupware")
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Override
    public String getConnectionString() {
        return "couchbase://localhost"; // Couchbase server host
    }

    @Override
    public String getUserName() {
        return "mycouch"; // Couchbase username
    }

    @Override
    public String getPassword() {
        return "couch@16"; // Couchbase password
    }

    @Override
    public String getBucketName() {
        return "newbucket"; // Couchbase bucket name
    }
}

