package org.si.repairs_state.vaadin;


public enum ViewPaths {
    LOGIN(""),
    SIGN_UP("prisijungimas"),
    MAIN("perziura"),
    REGISTR("registracija"),
    REPAIRS("remontai"),

    VABALS("busenos_pildymas");
    private String viewPath;
    ViewPaths(String viewPath) {
        this.viewPath = viewPath; }
    public String getViewPath(){
        return viewPath; }}
