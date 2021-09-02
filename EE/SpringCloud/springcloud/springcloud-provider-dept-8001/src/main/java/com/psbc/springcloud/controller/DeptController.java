package com.psbc.springcloud.controller;


import com.psbc.springcloud.pojo.Dept;
import com.psbc.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id) {
        return deptService.queryById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll() {
        return deptService.queryAll();
    }


    @Autowired
    private DiscoveryClient client;

    @GetMapping("/dept/discovery")
    private Object getOrder() {
        List<String> services = client.getServices();
        System.out.println(services);

        client.getInstances("SPRINGCLOUD-PROVIDER-DEPT-8001");
        for (String instance : services
        ) {
            System.out.println(instance);
        }
        return this.client;
    }


}
