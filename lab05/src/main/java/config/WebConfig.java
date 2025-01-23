package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import service.VisitInterceptor;
import service.ErrorInterceptor;

/**
 * Web configuration class to register interceptors.
 * Registers the VisitInterceptor and ErrorInterceptor to handle visit and error count cookies.
 *
 * Ensures that interceptors are configured for the application.
 *
 * @author Bartosz Pałucki
 * @version 5.1
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final VisitInterceptor visitInterceptor;
    private final ErrorInterceptor errorInterceptor;

    /**
     * Constructs a WebConfig with the specified VisitInterceptor and ErrorInterceptor.
     *
     * @param visitInterceptor the VisitInterceptor to register
     * @param errorInterceptor the ErrorInterceptor to register
     */
    @Autowired
    public WebConfig(VisitInterceptor visitInterceptor, ErrorInterceptor errorInterceptor) {
        this.visitInterceptor = visitInterceptor;
        this.errorInterceptor = errorInterceptor;
    }

    /**
     * Adds interceptors to the registry.
     *
     * @param registry the InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(visitInterceptor).addPathPatterns("/**");
        registry.addInterceptor(visitInterceptor).excludePathPatterns("/cookies/set");
        registry.addInterceptor(errorInterceptor).addPathPatterns("/**");
    }
}