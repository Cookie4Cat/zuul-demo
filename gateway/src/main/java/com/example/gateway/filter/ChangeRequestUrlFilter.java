package com.example.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.example.gateway.domain.ApiInterface;
import com.example.gateway.domain.ApiInterfaceRepository;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@Component
public class ChangeRequestUrlFilter extends ZuulFilter {
    @Autowired
    private ApiInterfaceRepository apiInterfaceRepository;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        try {
            InputStream in = request.getInputStream();
            String requestBody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            String id = JSON.parseObject(requestBody).getString("apiId");
            List<ApiInterface> interfaces = apiInterfaceRepository.findAll();
            Optional<ApiInterface> interfaceOptional = interfaces.stream().filter(i -> i.getId().equals(id)).findFirst();
            String url = interfaceOptional.map(ApiInterface::getTargetUrl).orElse("");
            Boolean isSiebelApi = url.contains("siebel");
            if(isSiebelApi){
                ctx.set("routeHost",null);
                ctx.set("serviceId","fake-service");
                ctx.set("requestURI", new URL(url).getPath());
            }else{
                ctx.set("routeHost",new URL(url));
                ctx.set("requestURI", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
