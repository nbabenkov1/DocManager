package utils.pdf;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import utils.ConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Class for outputting pdf files into browser window via {@link HttpServletResponse}
 **/
public class PDFResponseManager {
    /**
     * Creates {@link FileInputStream} from pdDocument param and copies it in output stream from response object
     * via {@link IOUtils#copy(InputStream, OutputStream)}
     *
     * @param pdDocument - pdf file object for output
     * @param response   - servlet response
     */
    public void pdfFileOutput(PDDocument pdDocument, HttpServletResponse response) throws IOException, ServletException {
        File tmpDir = new File(ConfigManager.getProperty("Rep.Dir.Homedir") + File.separator + "temp");
        File tmpFile = File.createTempFile("out", ".pdf", tmpDir);
        String outputFileName = tmpFile.getName();
        InputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            if (pdDocument.getNumberOfPages() == 0) {
                throw new ServletException("Договор не найден в исходных файлах");
            }
            pdDocument.save(tmpFile);
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=\"" + outputFileName + "\"");
            response.setContentLength((int) tmpFile.length());
            fileInputStream = new BufferedInputStream(new FileInputStream(tmpFile));
            outputStream = new BufferedOutputStream(response.getOutputStream());
            IOUtils.copy(fileInputStream, outputStream);
        } catch (FileNotFoundException fnf) {
            throw new ServletException("Ошибка открытия/создания pdf файла");
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (outputStream != null)
                    outputStream.close();
                tmpFile.delete();
            } catch (Exception e){
                tmpFile.delete();
            }
        }
    }
}
