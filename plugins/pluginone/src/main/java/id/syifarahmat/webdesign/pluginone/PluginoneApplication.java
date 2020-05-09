package id.syifarahmat.webdesign.pluginone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

@SpringBootApplication(exclude = {
        LiquibaseAutoConfiguration.class
})
public class PluginoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(PluginoneApplication.class, args);
    }

}
