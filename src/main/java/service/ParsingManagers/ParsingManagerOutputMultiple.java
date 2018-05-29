package service.ParsingManagers;

import models.DocCriteria;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repository.DocumentRepository;
import repository.DocumentRepositoryFromXLS;
import utils.pdf.DocumentToPDFManager;
import utils.pdf.PDFConfig;
import utils.pdf.PDFConfigFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Babenkov on 14.05.2018.
 **/
@Service
public class ParsingManagerOutputMultiple extends ParsingManagerOutput{
    private List<DocumentRepository> getParsingResults(DocCriteria criteria, MultipartFile file) throws IOException{
        criteria.setNumberOfMonths();
        List<DocumentRepository> documentRepositories = new ArrayList<>();
        for (int i=0; i<=criteria.getNumberOfMonths(); i++){
            DocumentRepositoryFromXLS repositoryFromXLS = new DocumentRepositoryFromXLS(new BufferedInputStream(file.getInputStream()));
            documentRepositories.add(parse(repositoryFromXLS, criteria));
            criteria.addMonth();
        }
        return  documentRepositories;
    }

    public PDDocument getPDFFromXLSContractList(DocCriteria criteria, MultipartFile file) throws IOException{
        List<DocumentRepository> repositories = getParsingResults(criteria, file);
        PDFConfigFactory factory = new PDFConfigFactory();
        PDFConfig config = factory.createPDFConfig(criteria.getDocumentType());
        return new DocumentToPDFManager().writeListToPDF(config, repositories);
    }
}
