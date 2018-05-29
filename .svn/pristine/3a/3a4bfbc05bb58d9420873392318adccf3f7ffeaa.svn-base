package controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by N.Babenkov on 14.05.2018.
 **/
public class ParsingControllerTest extends ControllerTest{
    @Value("${Rep.Dir.Homedir}")
    String testHomeDir;

    @Test
    public void storeOnServerTest() throws Exception {
        File file1 = new File(testHomeDir + "2017\\08\\itog\\Acts.txt");
        MockMultipartFile file = new MockMultipartFile("file", new FileInputStream(file1));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/parse/acts/store")
                .file(file).param("docType", "acts"));
    }

    @Test
    public void getPDFForSingle() throws Exception {
        long start = System.currentTimeMillis();
        mockMvc.perform(get("/parse/acts/get/11/767").param("month", "08")
                .param("year", "2017")
                .param("monthEnd", "08")
                .param("yearEnd", "2017")
                .param("period", "itog"))
                .andExpect(status().isOk());
        System.out.println("time for single: " + (System.currentTimeMillis()-start)/1000);
    }

    @Test
    public void getPDFForMultiple() throws Exception {
        File file1 = new File(testHomeDir + "testResources\\DocManagerTest.xlsx");
        MockMultipartFile file = new MockMultipartFile("file", new FileInputStream(file1));
        mockMvc.perform(fileUpload("/parse/acts/get/fromxls")
                .file(file)
                .param("month", "08")
                .param("year", "2017")
                .param("monthEnd", "08")
                .param("yearEnd", "2017")
                .param("period", "itog"))
                .andExpect(status().isOk());
    }

}
