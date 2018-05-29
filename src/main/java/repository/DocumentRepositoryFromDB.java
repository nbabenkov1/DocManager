package repository;

import DAO.ActreaderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletException;

/**
 * Created by N.Babenkov on 11.05.2018.
 **/
@Repository
public class DocumentRepositoryFromDB extends DocumentRepository{
    @Autowired
    public DocumentRepositoryFromDB(ActreaderDAO dao) throws ServletException{
        super();
        dao.selectAllDocuments()
                .forEach(x->addDocument(x.getRes(),x.getDog(),x.getM_num()));
        if (getDocumentList() == null)
            throw new ServletException("Couldn't get data from database");
    }
}
