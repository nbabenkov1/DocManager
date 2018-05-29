package utils.pdf;

import models.Document;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import repository.DocumentRepository;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Writes objects from {@link DocumentRepository} into pdf file(s)
 **/
@Component
public class DocumentToPDFManager {
    @Value("${Rep.Dir.Homedir}")
    private String homeDir;

    //contains district numbers
    private final int[] districts = {11, 13, 14, 15, 16};

    /**
     * Writes {@link DocumentRepository#documentList} into pdf, collects all documents failed to create pdf on
     *
     * @return {@link PDDocument}
     */
    public PDDocument writeListToPDF(PDFConfig pdfConfig, List<DocumentRepository> documentRepositories) throws IOException {
        PDFWriter pdfWriter = new PDFWriter(pdfConfig);
        List<Document> failedDocs = documentRepositories.stream()
                .map(DocumentRepository::getDocumentList)
                .flatMap(Collection::stream)
                .filter(x -> {
                    try {
                        return !writeDocumentToPDF(pdfWriter, x);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .collect(Collectors.toList());

        return pdfWriter.getPdDocument();
    }

    /**
     * Writes {@link Document#docPage} from {@link Document} into pdf via {@link PDFWriter}
     */
    private boolean writeDocumentToPDF(PDFWriter pdfWriter, Document document) throws IOException {
        if (document.getDocPage() != null) {
            pdfWriter.writePageToPDF(document.getDocPage());
            return true;
        }
        return false;
    }

    /**
     * Creates and saves pdf files for each district from
     * {@link DocumentRepository#documentList}
     */
    public void saveToPDFFiles(DocumentRepository repository, PDFConfig pdfConfig) throws IOException {
        if (repository == null)
            throw new IOException("Репозиторий документов не определен");
        prepareDirectories();
        repository.sortCollection();
        List<Document> documentList = repository.getDocumentList();
        Map<Integer, PDFWriter> pdfWriters = new HashMap<>();

        for (int i = 0; i <= 4; i++) {
            pdfWriters.put(districts[i], new PDFWriter(pdfConfig));
        }

        for (Document document : documentList) {
            if (document.getDocPage() == null) continue;
            pdfWriters.get(document.getRes()).writePageToPDF(document.getDocPage());
        }

        for (int i = 0; i <= 4; i++) {
            File file = new File(homeDir
                    + "ActsFiles" + File.separator + "RS" + districts[i] + ".pdf");
            try (PDDocument pdDocument = pdfWriters.get(districts[i]).getPdDocument()) {
                pdDocument.save(file);
                file.createNewFile();
            }
        }
    }

    /**
     * Clears/creates ActsFiles folder on server
     */
    private void prepareDirectories() throws IOException {
        File directory = new File(homeDir + "ActsFiles");
        if (!directory.exists()) {
            if (!directory.mkdirs())
                throw new IOException("Couldn't create directory for pdf");
        } else {
            Optional<File[]> files = Optional.ofNullable(directory.listFiles());
            files.ifPresent(x ->
                    Arrays.asList(x).forEach(f -> {
                        if (f.getAbsolutePath().endsWith(".pdf"))
                            if (!f.delete())
                                throw new UncheckedIOException(new IOException("Couldn't delete pdf file"));
                    }));
        }
    }
}
