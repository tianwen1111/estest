package com.an.annotation;

import com.an.annotation.AbstractInitializeBean;
import com.an.annotation.CloudService;
import com.an.utils.ApplicationContextUtils;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceInvokeProcessor extends AbstractInitializeBean {


    private static Map<String,Object> feignMap = new HashMap<String, Object>();
    private static Map<String,Object> feignMethodMap = new HashMap<String, Object>();

    @Override
    public void initalize(String... args) {

        ApplicationContext ioc = ApplicationContextUtils.getContext();//
        String[] cloudServiceArr = ioc.getBeanNamesForAnnotation(CloudService.class);
        for (String cloudService:cloudServiceArr){
            Class<?> type = ioc.getType(cloudService);
            if (!type.isInterface()) {
                Object bean = ioc.getBean(cloudService);
                if (AopUtils.isAopProxy(bean)) {
                    Object singletonTarget = AopProxyUtils.getSingletonTarget(bean);
                    type = singletonTarget.getClass();
                }

                for (Class<?> clazz:type.getInterfaces()){
                    if (clazz.isAnnotationPresent(CloudService.class)) {
                        type=clazz;
                        break;
                    }
                }
            }

            Method[] methods = type.getMethods();
            for (Method method : methods){
                 if (method.isAnnotationPresent(CloudService.class)){
                     String value = method.getAnnotation(CloudService.class).value();
                     if (StringUtils.isEmpty(value)) {
                         value = method.getAnnotation(CloudService.class).functionId();
                     }
                     Object beanObj = ioc.getBean(cloudService);
                     if (value == null || beanObj == null){
                          continue;
                     }
                     feignMap.put(value,beanObj);
                     feignMethodMap.put(value,method);
                 }
            }
        }
    }
}
