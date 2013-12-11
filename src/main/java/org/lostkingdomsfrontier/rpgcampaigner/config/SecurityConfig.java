package org.lostkingdomsfrontier.rpgcampaigner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author John McCormick Date: 12/4/13 Time: 17:38
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("dmJohn").password("totalPartyKill").roles("USER");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication().withUser("dmJohn").password("totalPartyKill").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/rpgCampaigner/**").hasRole("USER")
                .anyRequest().anonymous().and()
                .httpBasic().and()
                .csrf().disable();  // NOTE keep this disabled until i really understand it - it breaks POST calls
    }

}
