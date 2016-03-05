package com.sikorasoftware;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfiguration {

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createConnector());
        return tomcat;
    }

    private Connector createConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11Protocol");
        //Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8009);

        return connector;
    }

}