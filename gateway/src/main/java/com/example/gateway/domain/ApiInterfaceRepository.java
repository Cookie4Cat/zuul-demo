package com.example.gateway.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenyou.guo
 */
@Repository
public class ApiInterfaceRepository {
    public List<ApiInterface> findAll(){
        List<ApiInterface> apiInterfaces = new ArrayList<>();
        ApiInterface apiInterface1 = new ApiInterface("1", "http://localhost:8082/siebel/fake-service/ribbon-route-service");
        ApiInterface apiInterface2 = new ApiInterface("2", "http://localhost:8082/fake-service/sample-route-service");
        apiInterfaces.add(apiInterface1);
        apiInterfaces.add(apiInterface2);
        return apiInterfaces;
    }
}
