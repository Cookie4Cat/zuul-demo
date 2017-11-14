package com.example.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

@Component
@RefreshScope
public class ChangeBodyFilter extends ZuulFilter {

    public void refreshAll(){
        System.out.println("laowang");
    }

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
        try {
            RequestContext context = getCurrentContext();
            InputStream in = (InputStream) context.get("requestEntity");
            if (in == null) {
                in = context.getRequest().getInputStream();
            }
            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            body = "request body modified via request wrapper: "+ body;
            byte[] bytes = body.getBytes("UTF-8");
            URL url = new URL("http://localhost:8081/echo");
            context.set("routeHost",url);
            context.setRequest(new HttpServletRequestWrapper(getCurrentContext().getRequest()) {
                @Override
                public ServletInputStream getInputStream() throws IOException {
                    return new ServletInputStreamWrapper(bytes);
                }

                @Override
                public int getContentLength() {
                    return bytes.length;
                }

                @Override
                public long getContentLengthLong() {
                    return bytes.length;
                }
            });
        }
        catch (IOException e) {
            rethrowRuntimeException(e);
        }
        return null;
    }

}
