package com.jjsd.options.dao;

import com.jjsd.options.entity.user.Option;
import com.jjsd.options.entity.user.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujing on 2017/9/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyRepositoryTest {
    @Autowired
    private PropertyRepository propertyRepository;
    @Test
    public void findByEmail() throws Exception {

        Property property=new Property();
        property.setEmail("aaaa");
        Option option=new Option();
        option.setName("中");
        List l=new ArrayList();
        l.add(option);
        property.setOptions(l);

        propertyRepository.save(property);


    }

}