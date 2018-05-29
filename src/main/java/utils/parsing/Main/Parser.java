package utils.parsing.Main;


import repository.DocumentRepository;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Parsing pages from txt file into {@link DocumentRepository}
 */
public abstract class Parser {
    private int contract;
    private int district;
    /**
     * Line, by which parser will find contract and district numbers
     */
    private String keyCharSequence;
    /**
     * Line, by which parser will parse input file to pages
     */
    private String delimiter;
    /**
     * Number of pages, added into {@link DocumentRepository}
     */
    private int parsedPagesCount = 0;

    protected void setContract(int contract) {
        this.contract = contract;
    }

    protected void setDistrict(int district) {
        this.district = district;
    }

    protected void setKeyCharSequence(String keyCharSequence) {
        this.keyCharSequence = keyCharSequence;
    }

    protected void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Writes pages from input file into {@link DocumentRepository} via
     * {@link Parser#pageContainsContractAndDistrict(String)} (BufferedInputStream, DocumentRepository)}
     * and {@link Parser#writePageIntoModel(String, DocumentRepository)}
     *
     * @param inputStream        - input file
     * @param documentRepository - {@link DocumentRepository} with identified {@link DocumentRepository#documentList}
     */
    public DocumentRepository parseIntoCollection(BufferedInputStream inputStream, DocumentRepository documentRepository) throws IOException {
        try (Scanner scanner = new Scanner(inputStream, "Cp1251")) {
            scanner.useDelimiter(delimiter);
            String page;
            //кол-во найденных участок-договор
            while (scanner.hasNext()) {
                page = scanner.next();
                if (page.length() > 1000000) {
                    try (Scanner scanner1 = new Scanner(page)) {
                        scanner1.useDelimiter("================================================================================================================================");
                        while (scanner1.hasNext()) {
                            String page1 = scanner1.next();
                            if (pageContainsContractAndDistrict(page1))
                                writePageIntoModel(page1, documentRepository);
                        }
                    }
                } else if (pageContainsContractAndDistrict(page))
                    writePageIntoModel(page, documentRepository);
            }
        }
        System.out.println("Всего добавлено: " + parsedPagesCount);
        return documentRepository;
    }

    /**
     * Finds district and contract numbers on input page by {@link Parser#keyCharSequence}
     */
    protected boolean pageContainsContractAndDistrict(String page) {
        if (!page.contains(keyCharSequence)) return false;

        try {
            int startIndex = page.indexOf(keyCharSequence);
            //записываем индекс начала номера 61.......
            startIndex = page.indexOf("61", startIndex);
            String pageSub = page.substring(startIndex, startIndex + 8);
            if (pageSub.contains("[0-9]+")) {
                System.out.println(pageSub + " " + pageSub.length());
                return false;
            }
            //magic code
            int kod = Integer.parseInt(pageSub.substring(3, 4));
            //участок
            setDistrict(Integer.parseInt(pageSub.substring(1, 3)));
            //договор
            switch (kod) {
                case 6:
                    setContract(Integer.parseInt(pageSub.substring(4, 6)));
                    break;
                case 4:
                    setContract(Integer.parseInt(pageSub.substring(3, 7)));
                    break;
                default:
                    setContract(Integer.parseInt(pageSub.substring(4, 8)));
            }
            return true;
        } catch (StringIndexOutOfBoundsException s) {
            return false;
        }
    }

    /**
     * Calls {@link DocumentRepository#addPageIfExists(int, int, String)} and increments {@link Parser#parsedPagesCount}
     */
    private void writePageIntoModel(String page, DocumentRepository documentRepository) {
        if (documentRepository.addPageIfExists(district, contract, delimiter.replace("\\f", "") + page))
            parsedPagesCount++;
    }
}
