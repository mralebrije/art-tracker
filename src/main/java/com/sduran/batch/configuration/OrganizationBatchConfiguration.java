package com.sduran.batch.configuration;

import com.sduran.api.web.response.OrganizationSodaResponse;
import com.sduran.batch.listener.JobCompletionNotificationListener;
import com.sduran.batch.processor.OrganizationSodaResponseItemProcessor;
import com.sduran.model.Organization;
import com.socrata.api.Soda2Consumer;
import com.socrata.exceptions.SodaError;
import com.socrata.model.soql.SoqlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class OrganizationBatchConfiguration {

    @Value("${soda.api.endpoint.baltimore}")
    private String baltimoreEndpoint;
    @Value("${soda.api.resource.arts.organizations}")
    private String organizationResource;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public EntityManagerFactory entityManagerFactory;

    // tag::readerwriterprocessor[]
    @Bean
    public ListItemReader<OrganizationSodaResponse> readerOrganization() {
        final String method = "readerOrganization";

        Soda2Consumer consumer = Soda2Consumer.newConsumer(baltimoreEndpoint);

        // To get query results
        List<OrganizationSodaResponse> organizationList = null;
        try {

            LOG.info("{}, resource: {}", method, organizationResource);


            organizationList = consumer.query(organizationResource,
                    SoqlQuery.SELECT_ALL,
                    OrganizationSodaResponse.LIST_TYPE);
        } catch (InterruptedException e) {
            LOG.error("{}, LongRunningQueryException:{}",method, e);
        } catch (SodaError sodaError) {
            LOG.error("{}, SodaError:{}",method, sodaError);
        }

        ListItemReader<OrganizationSodaResponse> reader = null;
        if (organizationList != null) {
            LOG.info("{}, Successful query Organization, elements found:{}", method, organizationList.size());
            reader = new ListItemReader<>(organizationList);
        } else {
            LOG.info("{}, Unsuccessful query Organization", method);
        }
        return reader;
    }

    @Bean
    public OrganizationSodaResponseItemProcessor processorOrganization() {
        return new OrganizationSodaResponseItemProcessor();
    }

    @Bean
    public JpaItemWriter<Organization> writerOrganization() {
        JpaItemWriter<Organization> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
    // end::readerwriterprocessor[]

    @Bean
    public JobExecutionListener listenerOrganization() {
        return new JobCompletionNotificationListener();
    }

    // tag::jobstep[]
    @Bean
    public Job importOrganizationSodaResponseJob() {
        return jobBuilderFactory.get("importOrganizationSodaResponseJob")
                .incrementer(new RunIdIncrementer())
                .listener(listenerOrganization())
                .flow(step1Organization())
                .end()
                .build();
    }

    @Bean
    public Step step1Organization() {
        return stepBuilderFactory.get("step1Museumm")
                .<OrganizationSodaResponse, Organization>chunk(1)
                .reader(readerOrganization())
                .processor(processorOrganization())
                .writer(writerOrganization())
                .build();
    }
    // end::jobstep[]

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationBatchConfiguration.class);
}