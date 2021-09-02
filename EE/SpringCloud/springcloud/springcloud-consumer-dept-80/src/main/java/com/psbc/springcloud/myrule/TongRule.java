package com.psbc.springcloud.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TongRule {
    public IRule myRule(){
        return new RandomRule();
    }
}
