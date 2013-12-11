package org.lostkingdomsfrontier.rpgcampaigner.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author John McCormick Date: 10/24/13 Time: 17:44
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.lostkingdomsfrontier.rpgcampaigner.rest.controller"})
public class MvcConfig {
}
