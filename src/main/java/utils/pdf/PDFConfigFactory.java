package utils.pdf;

/**
 * Class for creating {@link PDFConfig} object
 **/
public class PDFConfigFactory {
    /**
     * Sets font size, line offset and x coordinates for {@link PDFConfig} object depending on document type
     * @param docType - document type
     * */
    public PDFConfig createPDFConfig(String docType){
        switch (docType){
            case "acts" : return new PDFConfig(10, 10, -10);
            case "sf"   : return new PDFConfig(8.5f, -34, -9);
            case "ved"  : return new PDFConfig(9, 40, -10);
            case "c4eta" : return new PDFConfig(10, 10, -10);
            default: return null;
        }
    }
}
