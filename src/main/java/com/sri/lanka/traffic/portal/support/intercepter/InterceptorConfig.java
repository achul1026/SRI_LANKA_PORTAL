package com.sri.lanka.traffic.portal.support.intercepter;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	private final Locale DEFAULT_LOCALE = new Locale("eng");

	@Bean
	public LocaleResolver localeResolver() {

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("lang");
		
		resolver.setDefaultLocale(DEFAULT_LOCALE);
		return resolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}
	
	@Bean
    public LocaleInterceptor localeInterceptor() {
        return new LocaleInterceptor();
    }
	
	@Bean
	public MessageSource messageSource(@Value("${srilanka.messages.basename}") String basename,
			@Value("${srilanka.messages.encoding}") String encoding) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames(basename.split(","));
		messageSource.setDefaultEncoding(encoding);
		return messageSource;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(localeInterceptor());
	}

}