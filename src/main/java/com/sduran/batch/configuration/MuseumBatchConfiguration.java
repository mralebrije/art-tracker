package com.sduran.batch.configuration;

import com.sduran.api.web.response.MuseumSodaResponse;
import com.sduran.batch.listener.JobCompletionNotificationListener;
import com.sduran.batch.processor.MuseumSodaResponseItemProcessor;
import com.sduran.model.Museum;
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
public class MuseumBatchConfiguration {

    @Value("${soda.api.endpoint.baltimore}")
    private String baltimoreEndpoint;
    @Value("${soda.api.resource.arts.museums}")
    private String museumsResource;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public EntityManagerFactory entityManagerFactory;

    // tag::readerwriterprocessor[]
    @Bean
    public ListItemReader<MuseumSodaResponse> readerMuseum() {
        final String method = "readerMuseum";

        Soda2Consumer consumer = Soda2Consumer.newConsumer(baltimoreEndpoint);

        // To get query results
        List<MuseumSodaResponse> museumList = null;
        try {

            LOG.info("{}, resource: {}", method, museumsResource);


            museumList = consumer.query(museumsResource,
                    SoqlQuery.SELECT_ALL,
                    MuseumSodaResponse.LIST_TYPE);
        } catch (InterruptedException e) {
            LOG.error("{}, LongRunningQueryException:{}", method, e);
        } catch (SodaError sodaError) {
            LOG.error("{}, SodaError:{}", method, sodaError);
        }

        ListItemReader<MuseumSodaResponse> reader = null;
        if (museumList != null) {
            LOG.info("{}, Successful query Museum, elements found:{}", method, museumList.size());
            reader = new ListItemReader<>(museumList);
        } else {
            LOG.info("{}, Unsuccessful query Museum", method);
        }
        return reader;
    }

    @Bean
    public MuseumSodaResponseItemProcessor processorMuseum() {
        return new MuseumSodaResponseItemProcessor();
    }

    @Bean
    public JpaItemWriter<Museum> writerMuseum() {
        JpaItemWriter<Museum> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
    // end::readerwriterprocessor[]

    @Bean
    public JobExecutionListener listenerMuseum() {
        return new JobCompletionNotificationListener();
    }

    // tag::jobstep[]
    @Bean
    public Job importMuseumSodaResponseJob() {
        return jobBuilderFactory.get("importMuseumSodaResponseJob")
                .incrementer(new RunIdIncrementer())
                .listener(listenerMuseum())
                .flow(step1Museum())
                .end()
                .build();
    }

    @Bean
    public Step step1Museum() {
        return stepBuilderFactory.get("step1Museum")
                .<MuseumSodaResponse, Museum>chunk(1)
                .reader(readerMuseum())
                .processor(processorMuseum())
                .writer(writerMuseum())
                .build();
    }
    // end::jobstep[]

    private static final Logger LOG = LoggerFactory.getLogger(MuseumBatchConfiguration.class);
}