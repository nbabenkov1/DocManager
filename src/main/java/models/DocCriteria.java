package models;

/**
 * Criteria for selecting documents
 **/
public class DocCriteria {
    private String documentType;
    private String month;
    private String year;
    private String monthEnd;
    private String yearEnd;
    private String period;
    private int numberOfMonths;

    public DocCriteria() {
    }

    public void addMonth(){
        if (this.month.equals("12")) {
            this.year = String.valueOf(Integer.parseInt(this.year) + 1);
            this.month = "01";
        } else {
            this.month = String.format("%02d", Integer.parseInt(this.month) + 1);
        }
    }

    public void setNumberOfMonths(){
        if ((monthEnd == null) || (yearEnd == null)) {
            this.numberOfMonths = 0;
            return;
        }

        int yearDiff = Integer.parseInt(yearEnd) - Integer.parseInt(this.year);
        this.numberOfMonths = yearDiff*12 + Integer.parseInt(monthEnd) - Integer.parseInt(this.month);
    }

    public int getNumberOfMonths(){
        return this.numberOfMonths;
    }
    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonthEnd() {
        return monthEnd;
    }

    public void setMonthEnd(String monthEnd) {
        this.monthEnd = monthEnd;
    }

    public String getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(String yearEnd) {
        this.yearEnd = yearEnd;
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "" + documentType +
                ", " + month +
                "." + year +
                " - " + monthEnd +
                "." + yearEnd +
                ", " + period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
