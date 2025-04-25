package asm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import asm.interceptor.*;



@Configuration
public class InterConfig implements WebMvcConfigurer{

	@Autowired
	SecurityInterceptor auth;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(auth)
			.addPathPatterns("/admin/**");
	}
}