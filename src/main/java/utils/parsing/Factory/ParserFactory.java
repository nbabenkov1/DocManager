package utils.parsing.Factory;

import org.springframework.stereotype.Component;
import utils.parsing.concrete.*;
import utils.parsing.Main.Parser;

/**
 * Factory for creating concrete implementor of {@link Parser}
 **/
@Component
public class ParserFactory {
    public Parser getParser(String docType){
        switch (docType) {
            case "sf":
                return new SFParser();
            case "ved":
                return new VedParser();
            case "acts":
                return new ActsParser();
            case "c4eta":
                return new C4etaParser();
        }
        return null;
    }
}
