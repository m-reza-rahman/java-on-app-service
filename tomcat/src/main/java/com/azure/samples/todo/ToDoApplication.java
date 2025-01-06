package com.azure.samples.todo;

import java.util.logging.Logger;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

public class ToDoApplication implements WebApplicationInitializer {

    private final static Logger logger = Logger.getLogger(ToDoApplication.class.getName());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.warning("Starting ToDoApplication...");
        // Create the application context
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        logger.warning("Registering config...");

        // Add the ContextLoaderListener
        servletContext.addListener(new ContextLoaderListener(context));

        // Register and configure the DispatcherServlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
                new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/resources/*");
        logger.warning("DispatcherServlet registered with mapping '/resources/*'");
    }
}
