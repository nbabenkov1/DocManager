package models;

/**
 * Class for containing information about single document
 */
public class Document {
    //TODO: notnull
    /**Number of document's res**/
    private int res;
    /**Document number**/
    private int dog;
    /**Number of machine**/
    private int m_num;
    /**Index of document object**/
    private int index;
    /**Document text**/
    private String docPage;

    public Document(int res, int dog, int index){
        this(res, dog,index,0);
    }

    public Document(int res, int dog, int index, int m_num){
        setDog(dog);
        setRes(res);
        setIndex(index);
        setM_num(m_num);
    }

    public Document() {
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getDog() {
        return dog;
    }

    public void setDog(int dog) {
        this.dog = dog;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDocPage() {
        return docPage;
    }

    public void setDocPage(String docPage) {
        this.docPage = docPage;
    }

    public void setM_num(int m_num){ this.m_num = m_num; }

    public int getM_num(){return m_num;}

    @Override
    public String toString() {
        return "Document{" +
                "res=" + res +
                ", dog=" + dog +
                ", m_num=" + m_num +
                ", index=" + index +
                ", docPage='" + docPage + '\'' +
                '}';
    }
}
