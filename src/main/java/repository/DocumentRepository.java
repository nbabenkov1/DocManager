package repository;

import models.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class for storing, filling and sorting of Document collection
 */
public class DocumentRepository {
    private List<Document> documentList;

    public DocumentRepository() {
        documentList = new ArrayList<>();
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    /**
     * Returns Document collection {@link DocumentRepository#documentList}
     **/
    public List<Document> getDocumentList() {
        return documentList;
    }

    /**
     * Adds text into particular Document object in collection {@link DocumentRepository#documentList}
     *
     * @param page {@link models.Document#docPage}
     * @return true if Document with number of district and contract
     * from params was found in the collection {@link DocumentRepository#documentList} and document text was successfully added.
     **/
    public boolean addPageIfExists(int district, int contract, String page) {
        int index = getIndexIfExists(district, contract);
        if (index > -1) {
            if (documentList.get(index).getDocPage() == null) {
                documentList.get(index).setDocPage(page);
                return true;
            }
        }
        return false;
    }

    /**
     * Creates new Document object with index of current {@link DocumentRepository#documentList} size
     * and adds it to collection.
     *
     * @param district {@link models.Document#res}
     * @param contract {@link models.Document#dog}
     * @see DocumentRepository#addDocument(int, int, int)
     **/
    public void addDocument(int district, int contract) {
        if (getIndexIfExists(district, contract) == -1) {
            Document document = new Document(district, contract, documentList.size());
            documentList.add(document);
        }
    }

    /**
     * Creates new Document object with index of current {@link DocumentRepository#documentList} size
     * and adds it to collection.
     *
     * @param district {@link models.Document#res}
     * @param contract {@link models.Document#dog}
     * @param m_num    {@link models.Document#m_num}
     * @see DocumentRepository#addDocument(int, int)
     **/
    void addDocument(int district, int contract, int m_num) {
        if (getIndexIfExists(district, contract) == -1) {
            Document document = new Document(district, contract, documentList.size(), m_num);
            documentList.add(document);
        }
    }

    /**
     * Searches for Document object in collection {@link DocumentRepository#documentList}.
     *
     * @return -1 if Document with these district and contract numbers isn't found
     * and {@link models.Document#index} if otherwise
     **/
    private int getIndexIfExists(int district, int contract) {
        return documentList.stream()
                .filter(d -> ((d.getDog() == contract) && (d.getRes() == district)))
                .map(Document::getIndex)
                .findFirst()
                .orElse(-1);
    }

    /**
     * Sorts the collection {@link DocumentRepository#documentList} by machine number
     * of Document objects {@link models.Document#m_num}
     **/
    public void sortCollection() {
        documentList.sort(Comparator.comparing(Document::getM_num)
                .thenComparing(Document::getRes)
                .thenComparing(Document::getDog)
        );
    }
}
