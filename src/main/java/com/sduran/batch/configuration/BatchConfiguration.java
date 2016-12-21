package com.sduran.batch.configuration;

import com.sduran.batch.listener.JobCompletionNotificationListener;
import com.sduran.batch.processor.MuseumItemProcessor;
import com.sduran.model.Museum;
import com.socrata.api.Soda2Consumer;
import com.socrata.exceptions.SodaError;
import com.socrata.model.soql.SoqlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
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
public class BatchConfiguration {

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
    public ListItemReader<Museum> reader() {
        final String method = "reader";

        Soda2Consumer consumer = Soda2Consumer.newConsumer(baltimoreEndpoint);

        // To get a query results
        List<Museum> museumList = null;
        try {
            museumList = consumer.query(museumsResource,
                    SoqlQuery.SELECT_ALL,
                    Museum.LIST_TYPE);
        } catch (InterruptedException e) {
            LOG.error("reader LongRunningQueryException: {}", e);
        } catch (SodaError sodaError) {
            LOG.error("reader SodaError: {}", sodaError);
        }

        ListItemReader<Museum> reader = null;
        if (museumList != null) {
            LOG.info("{} Successful query Museum, elements foud:{}", method, museumList.size());
            reader = new ListItemReader<>(museumList);
        } else {
            LOG.info("{} Unsuccessful query Museum", method);
        }
        return reader;
    }

    @Bean
    public MuseumItemProcessor processor() {
        return new MuseumItemProcessor();
    }

    @Bean
    public JpaItemWriter<Museum> writer() {
        JpaItemWriter<Museum> writer = new JpaItemWriter<Museum>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importMuseumJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importMuseumJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Museum, Museum>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]

    private static final Logger LOG = LoggerFactory.getLogger(BatchConfiguration.class);
}