package com.example.Board.paging;

public class Criteria {
    private int currentPageNo;
    private int recordsPerPage;
    private int pageSize;
    private String searchKeyword;
    private String searchType;

    public Criteria() {
        this.currentPageNo = 1;
        this.recordsPerPage = 10;
        this.pageSize = 10;
    }

    public int getStartPage() {
        return (currentPageNo - 1) * recordsPerPage;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "currentPageNo=" + currentPageNo +
                ", recordsPerPage=" + recordsPerPage +
                ", pageSize=" + pageSize +
                ", searchKeyword='" + searchKeyword + '\'' +
                ", searchType='" + searchType + '\'' +
                '}';
    }
}
