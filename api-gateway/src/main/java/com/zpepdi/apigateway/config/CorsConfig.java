package com.zpepdi.apigateway.config;


//@Configuration
//public class CorsConfig implements WebFilter {

//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//
//        corsConfiguration.addAllowedOrigin("*"); //允许任何域名使用
//        corsConfiguration.addAllowedHeader("*"); //允许任何头
//        corsConfiguration.addAllowedMethod("*"); //允许任何方法（post、get等）
//        return corsConfiguration;
//    }
//
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig());
//        return new CorsFilter(source);
//    }

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        if (CorsUtils.isCorsRequest(request)) {
//            ServerHttpResponse response = exchange.getResponse();
//            HttpHeaders headers = response.getHeaders();
//            headers.add("Access-Control-Allow-Origin", "*");
//            headers.add("Access-Control-Allow-Methods", "*");
//            headers.add("Access-Control-Max-Age", "18000");
//            headers.add("Access-Control-Allow-Headers", "*");
//            if (request.getMethod() == HttpMethod.OPTIONS) {
//                response.setStatusCode(HttpStatus.OK);
//                return Mono.empty();
//            }
//        }
//        return chain.filter(exchange);
//    }
//}
