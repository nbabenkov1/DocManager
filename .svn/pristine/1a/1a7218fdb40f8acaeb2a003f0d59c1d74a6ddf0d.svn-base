package controllers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import utils.ConfigManager;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.junit.Assert.*;

/**
 * Created by N.Babenkov on 15.05.2018.
 **/
public class FileUploadControllerTest extends ControllerTest {
    @Value("${Rep.Dir.Homedir}")
    private String directoryPath;

    @BeforeClass
    public static void prepareDir() {
        File file = new File(ConfigManager.getProperty("Rep.Dir.Homedir") + "2018\\05\\itog\\Acts.txt");
        if (file.exists())
            file.delete();
        assertTrue(!file.exists());
    }

    @Test
    public void uploadFileTest() throws Exception {
        File file = new File(directoryPath + "testResources\\Acts.txt");
        MockMultipartFile multipartFile = new MockMultipartFile("file", new FileInputStream(file));
        mockMvc.perform(fileUpload("/upload/acts/05/itog")
                .file(multipartFile));
    }

    @AfterClass
    public static void checkFile() {
        File file = new File(ConfigManager.getProperty("Rep.Dir.Homedir") + "2018\\05\\itog\\Acts.txt");
        assertTrue(file.exists());
    }
}
