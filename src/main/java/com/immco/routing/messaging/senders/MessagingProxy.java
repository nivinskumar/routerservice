package com.immco.routing.messaging.senders;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

import com.immco.routing.RoutingKeyConstants;
import com.immco.routing.messaging.common.AbstractMsg;
import com.immco.routing.messaging.common.PingMsg;
import com.immco.routing.utils.LogCtx;
import com.immco.routing.utils.LoggerService;

@Service
public class MessagingProxy {

	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;
	@Autowired
	private MappingJackson2MessageConverter mappingJackson2MessageConverter;

	@PostConstruct
	public void init() {
		this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);
	}

	public void sendRoutingMsg(AbstractMsg msg) {
		this.rabbitMessagingTemplate.convertAndSend(RoutingKeyConstants.EXCHANGE, msg.getRoutingKey(), msg);
		System.out.println("Send ////////////////////////////////////////" + msg.getContext());

	}

	public void sendPingMsg(String routingKey) {
		PingMsg n = new PingMsg(routingKey);
		this.rabbitMessagingTemplate.convertAndSend(RoutingKeyConstants.EXCHANGE,
				routingKey, n);
		LoggerService.getInstance().info(LogCtx.ROUTING_PING, n.getRxLogMsg(), MessagingProxy.class);
	}

}