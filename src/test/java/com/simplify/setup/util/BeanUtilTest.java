package com.simplify.setup.util;

import com.simplify.setup.domain.BeanTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/19 0:05
 */
public class BeanUtilTest {

    @Test
    public void getClassTypeTest() {

        Class<?>[] classType = BeanUtil.getClassType(BeanTest.class, new String[]{"id", "userName"});
        for(Class clazz : classType) {
            System.out.println(clazz.getName());
        }
    }

    @Test
    public void getFieldValueTest(){

        BeanTest beanTest = new BeanTest();
        //beanTest.setId(1L);
        beanTest.setAge(null);
        beanTest.setUserName("");

        Object[] fieldValues = BeanUtil.getFieldValue(beanTest, new String[]{"id", "userName","age"});
        System.out.println("fieldValues数组长度:" + fieldValues.length);
        for(Object obj : fieldValues) {
            System.out.println(obj);
        }
    }
}