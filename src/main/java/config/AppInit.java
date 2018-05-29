package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import utils.ConfigManager;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

/**
 * Created by N.Babenkov on 14.05.2018.
 **/
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    private final int maxUploadSize = 100 * 1024 * 1024;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                AppConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        File uploadDir = new File(ConfigManager.getProperty("Rep.Dir.Homedir") + "temp");
        MultipartConfigElement element = new MultipartConfigElement(uploadDir.getAbsolutePath(), maxUploadSize, maxUploadSize*2, maxUploadSize/2);
        registration.setMultipartConfig(element);

    }
}
