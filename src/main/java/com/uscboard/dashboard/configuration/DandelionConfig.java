// package com.uscboard.dashboard.configuration;

// import com.github.dandelion.core.web.DandelionFilter;

// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.boot.web.servlet.ServletRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class DandelionConfig {

//     @Bean
//     public DandelionDialect dandelionDialect(){
//         return new DandelionDialect();
//     }
//     @Bean
//     public DataTablesDialect dataTablesDialect(){
//         return new DataTablesDialect();

//     }
//     @Bean
//     public FilterRegistrationBean FilterRegistrationBean(){
//         FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//         filterRegistrationBean.setFilter(new DandelionFilter());
//         return filterRegistrationBean;
//     }
//     @Bean
//     public ServletRegistrationBean servletRegistrationBean(){

//         ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
//         servletRegistrationBean.setServlet(new DendelionServlet());
//         servletRegistrationBean.addUrlMappings("/dandelion-assets/*");
//         servletRegistrationBean.setName("dandelionServlet");
//         return servletRegistrationBean;
//         }

    
// }
