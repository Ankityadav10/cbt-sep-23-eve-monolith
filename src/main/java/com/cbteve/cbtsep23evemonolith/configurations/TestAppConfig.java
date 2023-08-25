package com.cbteve.cbtsep23evemonolith.configurations;

import com.cbteve.cbtsep23evemonolith.MyNum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestAppConfig
{

    @Bean("one")
    @Scope("singleton")
    public MyNum getNumOne()
    {
        MyNum  num3 = new MyNum();
        num3.setNum(23);
        return num3;
    }

    @Bean("two")
    @Scope("singleton")
    @Lazy
    public MyNum getNumTwo()
    {
        MyNum  num3 = new MyNum();
        num3.setNum(45);
        return num3;
    }

    @Bean("three")
    @Scope("singleton")
    public MyNum getNumThree()
    {
       MyNum  num3 = new MyNum();
       num3.setNum(76);
       return num3;
    }



}
