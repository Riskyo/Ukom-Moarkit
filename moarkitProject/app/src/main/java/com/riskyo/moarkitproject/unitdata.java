package com.riskyo.moarkitproject;

public class unitdata {
    private String unitName;
    private String publishDate;
    private Integer unitImage;
    private String hargaunit;
    private String deskripsiunit;

    public unitdata(String unitName, String publishDate, Integer unitImage, String hargaunit, String deskripsiunit) {
        this.unitName = unitName;
        this.publishDate = publishDate;
        this.unitImage = unitImage;
        this.hargaunit = hargaunit;
        this.deskripsiunit = deskripsiunit;
    }

    public String getHargaunit() {
        return hargaunit;
    }

    public void setHargaunit(String hargaunit) {
        this.hargaunit = hargaunit;
    }

    public String getDeskripsiunit() {
        return deskripsiunit;
    }

    public void setDeskripsiunit(String deskripsiunit) {
        this.deskripsiunit = deskripsiunit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getUnitImage() {
        return unitImage;
    }

    public void setUnitImage(Integer unitImage) {
        this.unitImage = unitImage;
    }
}
