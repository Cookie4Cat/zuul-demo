package com.example.gateway.config;

import com.example.gateway.domain.RouteRepository;
import com.example.gateway.zuul.CustomRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class CustomZuulConfig {
    @Autowired
    private ServerProperties server;
    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private RouteRepository routeRepository;

    @Bean
    public CustomRouteLocator routeLocator() {
        CustomRouteLocator routeLocator = new CustomRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
        routeLocator.setRouteRepository(routeRepository);
        return routeLocator;
    }
}
