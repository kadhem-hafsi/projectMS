package timsoft.gateway.gatewayserverer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class GatewayServereApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServereApplication.class, args);
	}


	@Bean
	RouteLocator getewayRoutes(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(r -> r.path("/product/**").uri("lb://PRODUCTMS"))
				.route(r -> r.path("/stock/**").uri("lb://STOCKMS"))
				.route(r -> r.path("/category/**").uri("lb://CATEGORY"))
				.route(r -> r.path("/fournisseur/**").uri("lb://FOURNISSEURMS"))
				.build();

	}

}
