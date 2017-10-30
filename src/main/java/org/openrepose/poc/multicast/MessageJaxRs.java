package org.openrepose.poc.multicast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Component
@Path("/")
public class MessageJaxRs {
    private static Logger LOG = LoggerFactory.getLogger(MessageJaxRs.class);

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void getMessage(String host) {
        LOG.info("Received message from {}", host);
    }
}
