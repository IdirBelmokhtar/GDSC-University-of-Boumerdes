package com.gdsc.umbb;

public class itemdoc {
    private String namedoc;
    private String icon;

    public itemdoc(String namedoc, String icon) {
        this.namedoc = namedoc;
        this.icon = icon;
    }

    public String getNamedoc() {
        return namedoc;
    }

    public void setNamedoc(String namedoc) {
        this.namedoc = namedoc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
