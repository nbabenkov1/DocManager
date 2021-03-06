package controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by N.Babenkov on 15.05.2018.
 **/
@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(value = {ServletException.class, IOException.class, SQLException.class})
    public @ResponseBody
    String servletError(Exception ex) {
        ex.printStackTrace();
        //TODO: proper exception handling
        System.out.println(ex.getLocalizedMessage());
        return ex.getMessage();
    }
}
