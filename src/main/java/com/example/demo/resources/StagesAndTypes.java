package com.example.demo.resources;

public enum StagesAndTypes {
    Stage1("Type1"),
    Stage2("Type2"),
    Stage3("Type3"),
    Stage4("Type4");
    public final String label;
    private StagesAndTypes(String label) {
        this.label = label;
    }
}
