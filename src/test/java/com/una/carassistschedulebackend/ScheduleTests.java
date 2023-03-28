package com.una.carassistschedulebackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.una.carassistschedulebackend.entidades.Schedule;
import com.una.carassistschedulebackend.models.ServiceType;
import com.una.carassistschedulebackend.persistence.ScheduleRepository;
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

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, ScheduleTests.DynamoDBConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScheduleTests {

	private static Logger LOGGER = LoggerFactory.getLogger(ScheduleTests.class);
	private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");

	@Configuration
		@EnableDynamoDBRepositories(basePackageClasses = { ScheduleTests.class })
		public static class DynamoDBConfig {

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
		private ScheduleRepository repository;

		@Test
		public void creationTest() throws ParseException {
			LOGGER.info("Creating objects...");
			Schedule s1 = new Schedule(df.parse("20/03/2023"), BigDecimal.valueOf(102.34), ServiceType.OilChange.toString(), "Henrique", "HR-V");
			Schedule s2 = new Schedule(df.parse("20/03/2023"), BigDecimal.valueOf(115.56), ServiceType.Wash.toString(), "Caio", "Ferrari");
			Schedule s3 = new Schedule(df.parse("20/03/2023"), BigDecimal.valueOf(89.52), ServiceType.OilChange.toString(), "Jeff", "Lambo");
			Schedule s4 = new Schedule(df.parse("20/03/2023"), BigDecimal.valueOf(174.88), ServiceType.Wash.toString(), "Daniel", "Mercedes");
			Schedule s5 = new Schedule(df.parse("20/03/2023"), BigDecimal.valueOf(2.52), ServiceType.FilterChange.toString(), "Pedro", "Audi");
			repository.save(s1);
			repository.save(s2);
			repository.save(s3);
			repository.save(s4);
			repository.save(s5);
			Iterable<Schedule> list = repository.findAll();
			assertNotNull(list.iterator());
			for (Schedule schedule : list) {
				LOGGER.info(schedule.toString());
			}
			LOGGER.info("Searching an object");
			List<Schedule> result = repository.findByServiceType(ServiceType.OilChange);
			assertEquals(result.size(), 2);
			LOGGER.info("Found: {}", result.size());
		}

		@Test
		public void deleteTest() throws ParseException {
			LOGGER.info("Deleting objects...");
			List<Schedule> result = repository.findByClientName("Henrique");
			for (Schedule schedule : result) {
				LOGGER.info("Excluindo Schedule id = "+schedule.getId());
				repository.delete(schedule);
			}
			result = repository.findByClientName("Henrique");
			assertEquals(result.size(), 0);
			LOGGER.info("Successfully deleted");
		}
}
