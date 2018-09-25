package ch16.entities;

import java.util.List;

public class SingerGrid {
    private int currentPage;
    private int totalPages;
    private long totalRecords;
    private List<Singer> singerData;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Object getSingerData() {
        return singerData;
    }

    public void setSingerData(List<Singer> singerData) {
        this.singerData = singerData;
    }
}
