package config;

import org.springframework.context.annotation.*;


/**
 * Created by N.Babenkov on 10.05.2018.
 **/
@Configuration
@Import(DBConfig.class)
@ComponentScan({"DAO", "repository", "service.ParsingManagers", "utils.parsing.Factory", "utils.pdf", "utils.fileUploading", "aspects"})
@PropertySource("classpath:config.properties")
@EnableAspectJAutoProxy
public class AppConfig {

}
