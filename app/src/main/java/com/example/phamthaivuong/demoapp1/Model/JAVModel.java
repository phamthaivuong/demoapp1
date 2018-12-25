package com.example.phamthaivuong.demoapp1.Model;

import java.io.Serializable;

public class JAVModel implements Serializable {

    public String cHID;

    public String name;

    public String slug;

    public Integer totalVideos;

    public String shortname;

    public String categoryUrl;

    public String coverUrl;

    public String getcHID() {
        return cHID;
    }

    public void setcHID(String cHID) {
        this.cHID = cHID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getTotalVideos() {
        return totalVideos;
    }

    public void setTotalVideos(Integer totalVideos) {
        this.totalVideos = totalVideos;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public JAVModel() {
    }

    public JAVModel(String cHID, String name, String slug, Integer totalVideos, String shortname, String categoryUrl, String coverUrl) {
        this.cHID = cHID;
        this.name = name;
        this.slug = slug;
        this.totalVideos = totalVideos;
        this.shortname = shortname;
        this.categoryUrl = categoryUrl;
        this.coverUrl = coverUrl;
    }
}
