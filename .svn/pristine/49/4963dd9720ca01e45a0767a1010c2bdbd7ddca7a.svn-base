package utils.pdf;

/**
 * Contains settings for writing into pdf file
 **/
public class PDFConfig {
    /**Vertical offset*/
    private float lineOffset;
    /**Font size*/
    private float fontSize;
    /**Horizontal offset*/
    private int xCoords;
    /**Page height*/
    private final int PAGE_HEIGHT = 583;

    /**
     * @param fontSize {@link PDFConfig#fontSize}
     * @param lineOffset {@link PDFConfig#lineOffset}
     * @param xCoords {@link PDFConfig#xCoords}
     * */
    public PDFConfig(float fontSize, int xCoords, float lineOffset){
        setFontSize(fontSize);
        setLineOffset(lineOffset);
        setxCoords(xCoords);
    }

    private void setLineOffset(float lineOffset){
        this.lineOffset = lineOffset;
    }
    /**{@link PDFConfig#lineOffset}*/
    public float getLineOffset(){ return lineOffset; }

    private void setFontSize(float fontSize){
        this.fontSize = fontSize;
    }
    /**{@link PDFConfig#fontSize}*/
    public float getFontSize(){ return fontSize; }

    private void setxCoords(int xCoords){
        this.xCoords = xCoords;
    }
    /**{@link PDFConfig#xCoords}*/
    public int getxCoords(){ return xCoords; }
    /**{@link PDFConfig#PAGE_HEIGHT}*/
    public int getPAGE_HEIGHT() {
        return PAGE_HEIGHT;
    }
}
