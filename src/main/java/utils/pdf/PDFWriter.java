package utils.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import utils.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class for creating and writing into {@link PDDocument}
 **/
class PDFWriter {
    private PDDocument pdDocument;
    private PDFont font;
    private PDFConfig pdfConfig;

    PDFWriter(PDFConfig pdfConfig) throws IOException {
        this.pdDocument = new PDDocument();
        this.pdfConfig = pdfConfig;
        setFont();
    }

    private void setFont() throws IOException{
        String fontName = ConfigManager.getProperty("fontFileName");
        File pdfFontFile = new File(getClass().getResource("/" + fontName).getFile());
        font = PDType0Font.load(pdDocument, pdfFontFile);
    }

    PDDocument getPdDocument() {
        return pdDocument;
    }

    /**
     * Writes page into {@link PDFWriter#pdDocument}.
     * If input text for page doesn't fit in one PDF page, writes a page and calls itself with what's left of input string.
     * @param page - text for page
     * */
    void writePageToPDF(String page) throws IOException {
            int yCoords = pdfConfig.getPAGE_HEIGHT();
            PDPage pdPage = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
            pdDocument.addPage(pdPage);

        try (Scanner lineScanner = new Scanner(page);
             PDPageContentStream contentStream = getPageStream(pdPage)) {
            while (lineScanner.hasNext()) {
                String line = lineScanner.nextLine();
                writeLineIntoStream(line, contentStream);
                //перенос на новую страницу
                yCoords += pdfConfig.getLineOffset();
                if (yCoords <= -1 * pdfConfig.getLineOffset()) { //если дошел до конца страницы
                    StringBuilder stringBuilder = new StringBuilder();
                    //записываем оставшуюся часть из сканнера в string
                    while (lineScanner.hasNext()) {
                        stringBuilder.append(lineScanner.nextLine());
                        stringBuilder.append("\n");
                    }
                    writePageToPDF(stringBuilder.toString());
                    break;
                }
                //вводим изменения в координаты для следующей строик
                contentStream.newLineAtOffset(0, pdfConfig.getLineOffset());
            }
            contentStream.endText();
        }
    }

    /**
     * deletes tabulation and other signs from line
     * because of encoding incompatibility
     */
    private void writeLineIntoStream(String line, PDPageContentStream contentStream) throws IOException{
        try {
            contentStream.showText(line.replace("\u0009", "  ")
                    .replaceAll("[\\f\\u000D]", ""));
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            contentStream.showText(" ");
        }
    }

    private PDPageContentStream getPageStream(PDPage pdPage) throws IOException{
        PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdPage);
        contentStream.beginText();
        contentStream.setFont(font, pdfConfig.getFontSize());        //устанавливаем шрифт
        contentStream.newLineAtOffset(pdfConfig.getxCoords(), pdfConfig.getPAGE_HEIGHT());  //координаты начала первой строки
        return contentStream;
    }
}
