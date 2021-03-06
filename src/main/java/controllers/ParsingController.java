package controllers;

import models.DocCriteria;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.ParsingManagers.ParsingManagerForStoring;
import service.ParsingManagers.ParsingManagerOutputMultiple;
import service.ParsingManagers.ParsingManagerOutputSingle;
import utils.pdf.PDFResponseManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by N.Babenkov on 10.05.2018.
 **/
@Controller
@CrossOrigin(value = "*")
@RequestMapping("/parse")
public class ParsingController {
    private final ParsingManagerForStoring store;
    private final ParsingManagerOutputSingle single;
    private final ParsingManagerOutputMultiple multiple;

    @Autowired
    public ParsingController(ParsingManagerForStoring actsStore, ParsingManagerOutputSingle single, ParsingManagerOutputMultiple multiple) {
        this.store = actsStore;
        this.single = single;
        this.multiple = multiple;
    }

    @RequestMapping(value = "/{docType}/store", method = RequestMethod.POST,
    produces = "text/plain;charset=UTF-8")
    public void parseAndStorePDF(@RequestParam(name = "file") MultipartFile[] file,
                                 @PathVariable(value = "docType") String docType) throws ServletException{
         if (!store.storeParsedFilesOnServer(file[0], docType))
             throw new ServletException("ОШИБКА ПРИ СОЗДАНИИ PDF");
    }

    @RequestMapping(value="/{documentType}/get/{district}/{contract}", method = RequestMethod.GET,
    produces = "text/plain;charset=UTF-8")
    public void parseAndGetSingle(DocCriteria criteria,
                                  @PathVariable(value = "district") int district,
                                  @PathVariable(value = "contract") int contract,
                                  HttpServletResponse response) throws ServletException{
        try(PDDocument document = single.getPDFForSingleContract(criteria, district, contract)){
            PDFResponseManager responseManager = new PDFResponseManager();
            responseManager.pdfFileOutput(document, response);
        } catch (IOException io){
            io.printStackTrace();
            throw new ServletException("Ошибка при создании PDF");
        }
    }

    @RequestMapping(value = "/{documentType}/get/fromxls", method = RequestMethod.POST,
    produces = "text/plain;charset=UTF-8")
    public void parseAndGetFromXls(DocCriteria criteria,
                                   @RequestParam(name="file") MultipartFile[] file,
                                   HttpServletResponse response) throws ServletException{
        try(PDDocument document = multiple.getPDFFromXLSContractList(criteria, file[0])){
            PDFResponseManager responseManager = new PDFResponseManager();
            responseManager.pdfFileOutput(document, response);
        } catch (IOException io){
            throw new ServletException("Ошибка при создании PDF");
        }
    }
}
