package utils.pdf;

import models.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import repository.DocumentRepository;
import utils.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by N.Babenkov on 25.05.2018.
 **/
@RunWith(MockitoJUnitRunner.class)
public class DocumentToPDFManagerTest {
    private static String homeDir = ConfigManager.getProperty("Rep.Dir.Homedir");

    private DocumentToPDFManager manager = new DocumentToPDFManager();
    @Mock
    private DocumentRepository repository;
    private PDFConfig config;

    @Before
    public void prepare(){
        File directory = new File(homeDir+"ActsFiles");
        if (directory.exists())
            directory.delete();

        PDFConfigFactory factory = new PDFConfigFactory();
        config = factory.createPDFConfig("acts");

        ReflectionTestUtils.setField(manager, "homeDir", homeDir);
    }

    @Test(expected = IOException.class)
    public void saveToPDFFilesNullTest() throws IOException {
        manager.saveToPDFFiles(null,config);
    }

    @Test
    public void saveToPDFFilesTest() throws IOException{
        List<Document> documents = new ArrayList<>();
        Document doc1 = new Document(11,767,0);
        Document doc2 = new Document(13,873, 1);
        doc1.setDocPage("TEST PAGE");
        doc2.setDocPage("TEST PAGE 2");
        documents.add(doc1);
        documents.add(doc2);

        when(repository.getDocumentList()).thenReturn(documents);
        manager.saveToPDFFiles(repository, config);
        verify(repository,times(1)).sortCollection();
        verify(repository,times(1)).getDocumentList();
        assertTrue(checkDirectory());
    }

    private boolean checkDirectory(){
        File directory = new File(homeDir+"ActsFiles");
        if (directory.exists()) {
            File[] files = directory.listFiles();
            return !(files == null || files.length != 5);
        } else return false;
    }

}
