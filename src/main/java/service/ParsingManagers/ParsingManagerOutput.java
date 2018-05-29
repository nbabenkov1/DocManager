package service.ParsingManagers;

import models.DocCriteria;
import repository.DocumentRepository;
import utils.ConfigManager;
import utils.FileManager;
import utils.parsing.Factory.ParserFactory;
import utils.parsing.Main.Parser;

import javax.servlet.ServletException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by N.Babenkov on 14.05.2018.
 **/
public abstract class ParsingManagerOutput {
    /**
     * Creates parser {@link Parser} object with {@link ParserFactory} and uses it to
     * parse .txt file from server with {@link Parser#parseIntoCollection(BufferedInputStream, DocumentRepository)}
     * @param documentRepository {@link DocumentRepository}
     * @return changed {@link DocumentRepository} with filled {@link models.Document#docPage} fields
     * in {@link models.Document} objects of {@link DocumentRepository#documentList}
     * @exception ServletException when .txt file isn't found on server
     * */
    DocumentRepository parse(DocumentRepository documentRepository, DocCriteria criteria)throws IOException {
        ParserFactory factory = new ParserFactory();
        Parser parser = factory.getParser(criteria.getDocumentType());
        String fileName = ConfigManager.getProperty(criteria.getDocumentType()+"FileName");
        File file = FileManager.getTargetFile(fileName, criteria.getPeriod(), criteria.getMonth(), criteria.getYear());
        if (!file.exists()) {
            throw new IOException(fileName + ": " + "Не найден исходный .txt файл");
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            return parser.parseIntoCollection(inputStream, documentRepository);
        }
    }
}
