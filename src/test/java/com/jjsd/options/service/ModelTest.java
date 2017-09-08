package com.jjsd.options.service;

import com.jjsd.options.service.impl.InvestServiceImpl;
import com.jjsd.options.service.impl.investModel.AdviceModel;
import org.junit.Test;

import java.lang.reflect.Method;

public class ModelTest {

    @Test
    public void getUrlString() throws Exception {
        Class<InvestServiceImpl> class1 = InvestServiceImpl.class;
        Object instance = class1.newInstance();
        Method method = class1.getDeclaredMethod("getRemainderDays", new Class[]{String.class});
        method.setAccessible(true);
        System.out.println(method.invoke(instance, new Object[]{"1702"}));
    }
}
