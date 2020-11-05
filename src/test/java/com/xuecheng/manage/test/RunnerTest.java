package com.xuecheng.manage.test;

import com.xuecheng.manage_cms_client.ManageCmsClientApplication;
import com.xuecheng.manage_cms_client.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author wuangjing
 * @create 2020/11/5-10:53
 * @Description:
 */
@SpringBootTest(classes = ManageCmsClientApplication.class)
@RunWith(SpringRunner.class)
public class RunnerTest {



    @Autowired
    private RabbitTemplate template;
    @Test
    public void test1() {
        String age="xing ";
       String ams="gms";
        String message="hello world i m form spring boot factory";
        template.convertAndSend(RabbitConfig.EX_ROUTING_CMS_POSTPAGE,"5a751fab6abb5044e0d19ea1",message);
    }
}
