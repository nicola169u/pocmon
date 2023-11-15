package main.java.com.exemple.View;

import main.java.com.exemple.Model.Case;

import javax.swing.*;

public class LabyrintheView{
    private CaseView[][] casesView;

    public LabyrintheView(int size) {
        casesView = new CaseView[size][size];
    }

    public void setCase(int i, int j, CaseView c){
        casesView[i][j] = c;
    }

    public CaseView getCase(int i, int j){
        return casesView[i][j];
    }
}
