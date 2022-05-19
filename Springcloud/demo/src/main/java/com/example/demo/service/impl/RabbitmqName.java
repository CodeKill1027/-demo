package com.example.demo.service.impl;

import org.springframework.stereotype.Component;

/**
 * @author yzj
 */

public interface RabbitmqName {
      String BOOT_QUEUE ="bootQueue";
      String FANOUT_EXCHANGE ="fanoutExchange";
   String DIRECT_EXCHANGE ="directExchange";
      String TOPIC_EXCHANGE ="topicExchange";
}
