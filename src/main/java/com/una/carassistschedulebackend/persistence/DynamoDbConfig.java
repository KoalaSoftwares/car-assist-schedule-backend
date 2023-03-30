package com.una.carassistschedulebackend.persistence;

import com.amazonaws.auth.*;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableDynamoDBRepositories
public class DynamoDbConfig {
    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        List<AWSCredentialsProvider> providers = new ArrayList<>();
        providers.add(new ProfileCredentialsProvider());
        providers.add(new DefaultAWSCredentialsProviderChain());
        return new AWSCredentialsProviderChain(
                providers.toArray(new AWSCredentialsProvider[providers.size()]));
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(amazonAWSCredentialsProvider())
                .withRegion(Regions.US_EAST_1).build();
    }
}
