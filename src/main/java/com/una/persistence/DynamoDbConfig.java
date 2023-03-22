package com.una.persistency;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class DynamoDbConfig {
    private static final DynamoDBMapperConfig.TableNameResolver TABLE_NAME_RESOLVER = (className, config) -> "schedule";
    ClientConfiguration getClientConfiguration() {
        ClientConfiguration cfg = new ClientConfiguration ();
        cfg.setProtocol(Protocol.HTTPS);
        cfg.setProxyPort(8099);
        return cfg;
    }

    @Bean(name="dynamoDBMapper")
    public DynamoDBMapper dynamoDBMapperLocal() {
        Regions region = Regions.US_EAST_2;
        DynamoDBMapperConfig dbMapperConfig = new DynamoDBMapperConfig.Builder().withTableNameResolver(TABLE_NAME_RESOLVER).build();
        AmazonDynamoDBClient dynamoClient = getAmazonDynamoDBLocalClient(region);
        return new DynamoDBMapper(dynamoClient, dbMapperConfig);
    }

    // This Client is configured for Local only with the Local Profile.
    private AmazonDynamoDBClient getAmazonDynamoDBLocalClient(Regions region) {
        return (AmazonDynamoDBClient) AmazonDynamoDBClientBuilder.standard().withRegion(region).build();
    }
}
