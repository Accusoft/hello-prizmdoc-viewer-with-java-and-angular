package sample.config;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class CspHeaderWebFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    exchange.getResponse()
        .getHeaders()
        .add("Content-Security-Policy",
            "script-src 'self'; worker-src blob:");
    return chain.filter(exchange);
  }
}
