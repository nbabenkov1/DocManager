package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import utils.fileUploading.DocumentUploader;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by N.Babenkov on 15.05.2018.
 **/
@Controller
@CrossOrigin("*")
@RequestMapping(value = "/upload")
public class FileUploadController {
    private final DocumentUploader uploader;

    @Autowired
    public FileUploadController(DocumentUploader uploader) {
        this.uploader = uploader;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getPage() {
        return new ModelAndView("uploadingFiles");
    }

    @RequestMapping(value = "/{documentType}/{month}/{period}", method = RequestMethod.POST,
            produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String uploadFile(@PathVariable("documentType") String docType,
                      @PathVariable("month") String month,
                      @PathVariable("period") String period,
                      @RequestParam("file") MultipartFile[] files) throws ServletException {
        try {
            uploader.saveFile(docType, period, month, files[0]);
            return "Файл успешно загружен!";
        } catch (IOException io) {
            throw new ServletException(io.getMessage());
        }
    }

    @RequestMapping(value = "/{documentType}/{month}/{period}", method = RequestMethod.GET)
    public @ResponseBody
    String checkIfFileExists(@PathVariable("documentType") String docType,
                             @PathVariable("month") String month,
                             @PathVariable("period") String period) throws ServletException{
        try {
            String lastModified = uploader.fileExists(docType, period, month);
            if (lastModified == null)
                return "Файл ещё не был создан";
            else
            return "Файл был создан " + lastModified;

        } catch (IOException io){
            throw new ServletException("Error while checking if file already exists");
        }
    }
}
