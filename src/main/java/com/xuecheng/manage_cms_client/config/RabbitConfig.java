package com.xuecheng.manage_cms_client.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author wuangjing
 * @create 2020/11/5-9:44
 * @Description: queue_cms_postpage/ex_routing_cms_postpage
 * queue_cms_postpage_name
 * ex_routing_key
 */
@Configuration
public class RabbitConfig {

    public static final String EX_ROUTING_CMS_POSTPAGE = "EX_ROUTING_CMS_POSTPAGE";
    public static final String QUEUE_CMS_POSTPAGE = "QUEUE_CMS_POSTPAGE";


    @Value("${xuecheng.mq.queue}")
    public String queue_cms_postpage_name;

    @Value("${xuecheng.mq.routingKey}")
    public String ex_routing_key;

    //配置队列
    @Bean(QUEUE_CMS_POSTPAGE)
    public Queue QUEUE_CMS_POSTPAGE() {
        return new Queue(queue_cms_postpage_name);
    }


    //配置交换机 durable持久化交换机
    @Bean(EX_ROUTING_CMS_POSTPAGE)
    public Exchange EX_ROUTING_CMS_POSTPAGE() {
        return ExchangeBuilder.topicExchange(EX_ROUTING_CMS_POSTPAGE).durable(true).build();
    }

    //配置交换机和队列绑定
    @Bean
    public Binding BINDING_ROUTING_QUEUE_CMS(@Qualifier(QUEUE_CMS_POSTPAGE) Queue queue, @Qualifier(EX_ROUTING_CMS_POSTPAGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ex_routing_key).noargs();
    }


}
