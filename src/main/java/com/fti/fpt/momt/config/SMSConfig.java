package com.fti.fpt.momt.config;

import com.fti.fpt.momt.endpoint.SMSEndpoint;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tempuri.MOReceive;
import org.tempuri.MOReceiveSoap;

import javax.xml.ws.Endpoint;
import java.sql.SQLException;

import static javax.xml.ws.soap.SOAPBinding.*;

@Configuration
public class SMSConfig {

    @Autowired
    private SpringBus springBus;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() throws SQLException{
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }

    @Bean
    public MOReceiveSoap moReceiveSoap(){
        return new SMSEndpoint();
    }

    @Bean
    public MOReceive moReceive(){
        return new MOReceive();
    }

    @Bean(name = "messageRequest")
    public Endpoint messageRequest() throws Exception{
        EndpointImpl endpoint = new EndpointImpl(springBus, moReceiveSoap());
        endpoint.setBindingUri(SOAP12HTTP_MTOM_BINDING);
        endpoint.setServiceName(moReceive().getServiceName());
        endpoint.setWsdlLocation(moReceive().getWSDLDocumentLocation().toString());
        endpoint.publish("/MOReceive");
        return endpoint;
    }
}
