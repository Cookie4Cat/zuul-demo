package com.example.gateway.zuul;

import com.example.gateway.domain.RouteInfo;
import com.example.gateway.domain.RouteRepository;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private RouteRepository routeRepository;

    public void setRouteRepository(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        return locateRoutesFromDB();
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    private Map<String, ZuulRoute> locateRoutesFromDB(){
        List<RouteInfo> routesList = routeRepository.getAllRoutes();
        Map<String, ZuulRoute> routesMap = new LinkedHashMap<>();
        for (RouteInfo route : routesList) {
            ZuulRoute zuulRoute = new ZuulRoute();
            zuulRoute.setId(route.getPath());
            zuulRoute.setPath(route.getPath());
            zuulRoute.setUrl(route.getUrl());
            routesMap.put(zuulRoute.getPath(),zuulRoute);
        }
        return routesMap;
    }
}
