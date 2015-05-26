package Absyn;

import java.util.*;

public class DeclList extends Absyn {
    ArrayList<Declaration> declList;

    public DeclList(Declaration d) {
        declList = new ArrayList<Declaration>();
        if (d != null) {
            declList.add(d);
        }
    }

    public void add(Declaration d) {
        declList.add(d);
    }

    public void printAST() {
        for (Declaration d : declList) {
            d.printAST();
        }
    }

    public void printSymTable() {
        for (Declaration d : declList) {
            d.printSymTable();
        }
    }

    public DeclList semanticAnalysis() {
        DeclList dl = new DeclList(null);
        for (Declaration d : this.declList) {
            dl.add(d.semanticAnalysis());
        }
        return dl;
    }
}
