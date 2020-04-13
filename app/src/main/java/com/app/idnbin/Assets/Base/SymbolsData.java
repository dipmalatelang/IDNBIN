package com.app.idnbin.Assets.Base;

import java.util.List;

public class SymbolsData {
    private String symbolName;
    private List<String> workingPeriod;
    private List<String> multiplier;
    private String imgaeUrl;
    private String description;
    private String category;
    private boolean titleVisible;
    private boolean ischecked;

    public SymbolsData(boolean titleVisible, boolean ischecked, String symbolName, List<String> workingPeriod, List<String> multiplier, String imgaeUrl, String description, String category) {
        this.symbolName = symbolName;
        this.workingPeriod = workingPeriod;
        this.multiplier = multiplier;
        this.imgaeUrl = imgaeUrl;
        this.description = description;
        this.category = category;
        this.titleVisible = titleVisible;
        this.ischecked = ischecked;
    }

    public SymbolsData(String symbolName, String imgaeUrl, String category) {
        this.symbolName = symbolName;
        this.imgaeUrl = imgaeUrl;
        this.category = category;
    }

    public SymbolsData() {

    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public List<String> getWorkingPeriod() {
        return workingPeriod;
    }

    public void setWorkingPeriod(List<String> workingPeriod) {
        this.workingPeriod = workingPeriod;
    }

    public List<String> getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(List<String> multiplier) {
        this.multiplier = multiplier;
    }

    public String getImgaeUrl() {
        return imgaeUrl;
    }

    public void setImgaeUrl(String imgaeUrl) {
        this.imgaeUrl = imgaeUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isTitleVisible() {
        return titleVisible;
    }

    public void setTitleVisible(boolean titleVisible) {
        this.titleVisible = titleVisible;
    }

    public boolean isIschecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }
}
