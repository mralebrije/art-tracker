package com.sduran.batch.processor;

import com.sduran.model.Museum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class MuseumItemProcessor implements ItemProcessor<Museum, Museum> {

    @Override
    public Museum process(final Museum museum) {
        final String method = "process";

        Museum transformedMuseum = museum;
        LOG.info("{} Converting ({}) into ({})", method, museum, transformedMuseum);
        return transformedMuseum;
    }

    private static final Logger LOG = LoggerFactory.getLogger(MuseumItemProcessor.class);
}
