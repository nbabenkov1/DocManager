package utils;

import java.io.File;

/**
 * Class for getting file from server
 */
public class FileManager {
    /**Gets file of particular type from particular month and year from server
     * @param filetype - file type (Acts, SF or Ved)
     * @return target file {@link File}
     * */
    public static File getTargetFile(String filetype, String period, String month, String year){
        String filePath=ConfigManager.getProperty("Rep.Dir.Homedir")+year+File.separator
                +month+File.separator+period
                +File.separator+filetype;
        return new File(filePath);
    }
}
