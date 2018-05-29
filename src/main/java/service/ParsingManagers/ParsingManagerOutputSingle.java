package service.ParsingManagers;

import models.DocCriteria;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import repository.DocumentRepository;
import utils.pdf.DocumentToPDFManager;
import utils.pdf.PDFConfig;
import utils.pdf.PDFConfigFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Babenkov on 14.05.2018.
 **/
@Service
public class ParsingManagerOutputSingle extends ParsingManagerOutput {
    public ParsingManagerOutputSingle() {
    }

    private List<DocumentRepository> getParsingResults(DocCriteria criteria, int district, int contract) throws IOException {
        List<DocumentRepository> documentRepositories = new ArrayList<>();
        criteria.setNumberOfMonths();
        for (int i = 0; i <= criteria.getNumberOfMonths(); i++) {
            DocumentRepository documentRepository = new DocumentRepository();
            documentRepository.addDocument(district, contract);
            documentRepositories.add(parse(documentRepository, criteria));
            criteria.addMonth();
        }
        return documentRepositories;
    }

    public PDDocument getPDFForSingleContract(DocCriteria criteria, int district, int contract) throws IOException{
        List<DocumentRepository> documentRepositories = getParsingResults(criteria, district, contract);
        PDFConfigFactory factory = new PDFConfigFactory();
        PDFConfig config = factory.createPDFConfig(criteria.getDocumentType());
        return new DocumentToPDFManager().writeListToPDF(config, documentRepositories);
    }
}
