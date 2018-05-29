package aspects;

import models.DocCriteria;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import service.ParsingManagers.ParsingManagerOutputSingle;

/**
 * Created by N.Babenkov on 15.05.2018.
 **/
@Component
@Aspect
public class LoggingAspect {
    private Logger singleLogger = LoggerFactory.getLogger(ParsingManagerOutputSingle.class);

    @Before(value = "execution(* service.ParsingManagers.ParsingManagerOutputSingle.getPDFForSingleContract(..)) && args(criteria, district, contract)", argNames = "criteria,district,contract")
    public void singleLog(DocCriteria criteria, int district, int contract) throws Throwable{
        StringBuilder output = new StringBuilder("");
        output.append(criteria.getDocumentType()).append(" ").append(district).append(" ").append(contract);
        singleLogger.info(output.toString());
        output = new StringBuilder("");
        output.append(criteria.getPeriod()).append(" ").append(criteria.getMonth()).append(".").append(criteria.getYear());
        singleLogger.info(output.toString());
        singleLogger.info("##############");
    }
}
