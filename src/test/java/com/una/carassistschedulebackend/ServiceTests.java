package com.una.carassistschedulebackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.una.carassistschedulebackend.entidades.Service;
import com.una.carassistschedulebackend.models.ServiceType;
import com.una.carassistschedulebackend.persistence.ServiceRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, ServiceTests.DynamoDbConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceTests {

    private static Logger LOGGER = LoggerFactory.getLogger(ServiceTests.class);

    @Configuration
    @EnableDynamoDBRepositories(basePackageClasses = { ServiceTests.class })
    public static class DynamoDbConfig {

        @Value("${amazon.aws.accesskey}")
        private String amazonAWSAccessKey;

        @Value("${amazon.aws.secretkey}")
        private String amazonAWSSecretKey;

        public AWSCredentialsProvider amazonAWSCredentialsProvider() {
            return new AWSStaticCredentialsProvider(amazonAWSCredentials());
        }

        @Bean
        public AWSCredentials amazonAWSCredentials() {
            return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
        }

        @Bean
        public AmazonDynamoDB amazonDynamoDB() {
            return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
                    .withRegion(Regions.US_EAST_1).build();
        }
    }

    @Autowired
    private ServiceRepository repository;

    @Test
    public void creationTest() throws ParseException {
        LOGGER.info("Creating objects...");
        Service sv1 = new Service("Wash", BigDecimal.valueOf(520.01));
        Service sv2 = new Service("OilChange", BigDecimal.valueOf(115.56));
        Service sv3 = new Service("FilterChange", BigDecimal.valueOf(115.56));
        repository.save(sv1);
        repository.save(sv2);
        repository.save(sv3);
        Iterable<Service> list = repository.findAll();
        assertNotNull(list.iterator());
        for (Service service : list) {
            LOGGER.info(service.toString());
        }
        LOGGER.info("Searching an object");
        List<Service> result = repository.findById(sv2.getId());
        assertEquals(result.size(), 1);
        LOGGER.info("Found: {}", result.size());
    }

    @Test
    public void deleteTest() throws ParseException {
        LOGGER.info("Deleting objects...");
        List<Service> result = repository.findByServiceType(ServiceType.Wash);
        for (Service service : result) {
            LOGGER.info("Excluindo Service id = " + service.getId());
            repository.delete(service);
        }
        result = repository.findByServiceType(ServiceType.Wash);
        assertEquals(result.size(), 0);
        LOGGER.info("Successfully deleted");
    }
}
