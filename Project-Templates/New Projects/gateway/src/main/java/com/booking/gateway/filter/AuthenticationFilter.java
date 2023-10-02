package com.booking.gateway.filter;

import com.booking.gateway.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private RestTemplate rest;
    @Autowired
    private JwtUtils jwtUtils;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(AuthenticationFilter.Config config) {
        return ((exchange, chain) -> {
            ServerWebExchange exchange1 = null;
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey("Authorization"))
                    throw new RuntimeException("Authorization header is missing");
                String token = exchange.getRequest().getHeaders().get("Authorization").get(0);
                System.err.println(token);
                if(token != null && token.startsWith("Bearer ")) token = token.substring(7);
                System.err.println(token);
                String roles = jwtUtils.validate(token);
                ServerHttpRequest request = exchange.getRequest().mutate().header("roles", roles).build();
                exchange1 = exchange.mutate().request(request).build();
            }
            return chain.filter(exchange1);
        });
    }

    public static class Config {
    }
}
