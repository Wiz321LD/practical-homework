package org.example.hw5.config;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @NonNull
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @NonNull
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ApplicationConfiguration.class};
    }

    @NonNull
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
