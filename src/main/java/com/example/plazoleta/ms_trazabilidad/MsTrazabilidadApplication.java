package com.example.plazoleta.ms_trazabilidad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.plazoleta.ms_trazabilidad.infrastructure.adapters.feign")
public class MsTrazabilidadApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTrazabilidadApplication.class, args);
	}

}
