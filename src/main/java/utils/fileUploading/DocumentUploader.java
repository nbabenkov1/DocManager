package utils.fileUploading;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Stores file in repository on server
 **/
@Component
public class DocumentUploader {
    @Value("${Rep.Dir.Homedir}")
    private String directoryPath;

    public DocumentUploader() {
    }

    /**
     * @return file date if file exists, else null
     */
    public String fileExists(String docType, String period, String month) throws IOException{
        String fullFileName = getFullFileName(getFullPath(period,month), docType);
        File file = new File(fullFileName);
        if (file.exists()) {
            BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            long lastModified = attr.lastModifiedTime().toMillis();
            return df.format(lastModified);
        }
        return null;
    }

    public void saveFile(String docType, String period, String month, MultipartFile file) throws IOException {
        String fullPath = getFullPath(period, month);
        checkDirectory(fullPath);
        try (BufferedInputStream is = new BufferedInputStream(file.getInputStream())) {
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(prepareFile(docType, fullPath)));
            IOUtils.copy(is, os);
        }
    }

    private void checkDirectory(String fullPath) throws IOException {
        File filePath = new File(fullPath);
        filePath.mkdirs();
    }

    private File prepareFile(String docType, String fullPath) throws IOException {
        File file = new File(getFullFileName(fullPath, docType));
        if (file.exists())
            file.delete();
        file.createNewFile();
        return file;
    }

    private String getFullPath(String period, String month) {
        return directoryPath + getYear(month) + File.separator +
                month + File.separator +
                period;
    }

    private String getYear(String month) {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (month.equals("01") && currentMonth == 11)
            return String.valueOf(currentYear + 1);
        else if (month.equals("12") && currentMonth == 0)
            return String.valueOf(currentYear - 1);
        else return String.valueOf(currentYear);
    }

    private String getFullFileName(String basepath, String docType){
        return basepath + File.separator +
                docType + ".txt";
    }
}
