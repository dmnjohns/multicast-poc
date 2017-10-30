package org.openrepose.poc.multicast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Random;

@Service
public class RandoMessageService {

    private static Logger LOG = LoggerFactory.getLogger(RandoMessageService.class);

    private Map<String, Date> hostList;
    private String hostName;
    private String port;
    private RestTemplate rest;

    private Random random = new Random();

    @Autowired
    public RandoMessageService(Map<String, Date> hostList, @Value("HOSTNAME") String hostName, @Value("server.port") String port) {
        this.hostList = hostList;
        this.hostName = hostName;
        this.port = port;
        this.rest = new RestTemplate(Arrays.asList(new StringHttpMessageConverter()));
    }

    @Scheduled(fixedRate = 3000)
    public void sendRandoMessage() {
        Object[] hosts = hostList.keySet().toArray();
        if(hosts.length > 0) {
            Object target = hosts[random.nextInt(hosts.length)];
            LOG.info("Sending a message to {} ...", target);
            rest.postForLocation(target.toString() + ":" + port, hostName);
            LOG.info("...message sent");
        } else {
            LOG.info("Nobody to send messages to yet.");
        }
    }
}
