package com.una.carassistschedulebackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.amazonaws.auth.*;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.una.carassistschedulebackend.entities.Assistance;
import com.una.carassistschedulebackend.models.AssistanceType;
import com.una.carassistschedulebackend.persistence.AssistanceRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, AssistanceTests.DynamoDbConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AssistanceTests {

    private static Logger LOGGER = LoggerFactory.getLogger(AssistanceTests.class);

    @Configuration
    @EnableDynamoDBRepositories(basePackageClasses = { AssistanceTests.class })
    public static class DynamoDbConfig {

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

    @Autowired
    private AssistanceRepository repository;

    @Test
    public void creationTest() throws ParseException {
        LOGGER.info("Creating objects...");
        Assistance sv1 = new Assistance("Wash", BigDecimal.valueOf(520.01));
        Assistance sv2 = new Assistance("OilChange", BigDecimal.valueOf(115.56));
        Assistance sv3 = new Assistance("FilterChange", BigDecimal.valueOf(115.56));
        repository.save(sv1);
        repository.save(sv2);
        repository.save(sv3);
        Iterable<Assistance> list = repository.findAll();
        assertNotNull(list.iterator());
        for (Assistance assistance : list) {
            LOGGER.info(assistance.toString());
        }
        LOGGER.info("Searching an object");
        List<Assistance> result = repository.findByAssistanceType(AssistanceType.Wash);
        assertEquals(result.size(), 1);
        LOGGER.info("Found: {}", result.size());
    }

    @Test
    public void deleteTest() throws ParseException {
        LOGGER.info("Deleting objects...");
        List<Assistance> result = repository.findByAssistanceType(AssistanceType.Wash);
        for (Assistance assistance : result) {
            LOGGER.info("Excluindo Service id = " + assistance.getId());
            repository.delete(assistance);
        }
        result = repository.findByAssistanceType(AssistanceType.Wash);
        assertEquals(result.size(), 0);
        LOGGER.info("Successfully deleted");
    }
}
