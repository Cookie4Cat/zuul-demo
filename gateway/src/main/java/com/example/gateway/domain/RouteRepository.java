package com.example.gateway.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RouteRepository {

    private boolean upDown = false;

    public List<RouteInfo> getAllRoutes(){
        List<RouteInfo> routes = new ArrayList<>();
        RouteInfo route;
        if(upDown){
            route = new RouteInfo("/test1","http://localhost:8081/echo1");
            upDown = false;
        }else{
            route = new RouteInfo("/test2","http://localhost:8081/echo2");
            upDown = true;
        }
        routes.add(route);
        return routes;
    }
}
