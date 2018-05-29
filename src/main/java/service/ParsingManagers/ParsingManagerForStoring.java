package service.ParsingManagers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repository.DocumentRepository;
import repository.DocumentRepositoryFromDB;
import utils.parsing.Factory.ParserFactory;
import utils.parsing.Main.Parser;
import utils.pdf.DocumentToPDFManager;
import utils.pdf.PDFConfigFactory;

import java.io.BufferedInputStream;
import java.io.IOException;


/**
 * Created by N.Babenkov on 10.05.2018.
 **/
@Service
public class ParsingManagerForStoring {
    private DocumentRepository repository;
    private ParserFactory factory;
    private DocumentToPDFManager pdfManager;

    @Autowired
    public ParsingManagerForStoring(DocumentRepositoryFromDB repository, ParserFactory factory, DocumentToPDFManager pdfManager){
        this.repository = repository;
        this.factory = factory;
        this.pdfManager = pdfManager;
    }

    private DocumentRepository getParsingResults(MultipartFile file, String docType) throws IOException {
        Parser parser = factory.getParser(docType);
        try (BufferedInputStream is = new BufferedInputStream(file.getInputStream())) {
            return parser.parseIntoCollection(is,repository);
        }
    }

    public boolean storeParsedFilesOnServer(MultipartFile file, String doctype){
        try {
            PDFConfigFactory configFactory = new PDFConfigFactory();
            DocumentRepository repository = getParsingResults(file,doctype);
            pdfManager.saveToPDFFiles(repository, configFactory.createPDFConfig(doctype));
            return true;
        } catch (IOException io){
            //TODO:logger
            return false;
        }
    }
}
