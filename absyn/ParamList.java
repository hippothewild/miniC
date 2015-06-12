package Absyn;

import java.util.*;

public class ParamList extends Absyn {
    ArrayList<Param> paramList;

    public ParamList(Param p) {
        paramList = new ArrayList<Param>();
        if (p != null) {
            paramList.add(p);
        }
    }

    public void add(Param p) {
        if (p != null) {
            paramList.add(p);
        }
    }

    public void printAST() {
        for (Param p : paramList.subList(0, paramList.size()-1)) {
            p.printAST();
			printWriter.print(", ");
        }
        paramList.get(paramList.size() - 1).printAST();
    }

    public void printSymTable() {
        for (Param p : paramList) {
            String typeStr = "NULL";
            switch (p.type.typeName) {
                case INT:
                    typeStr = "int";
                    break;
                case FLOAT:
                    typeStr = "float";
                    break;
            }
            printSymTableRow(typeStr, p.identifier.name, p.identifier.size, "parameter");
        }
    }

    public ParamList semanticAnalysis() {
        ParamList pl = new ParamList(null);
        int paramPos = -this.paramList.size() - 1;
        for (Param p : this.paramList) {
            pl.add(p.semanticAnalysis(paramPos++));
        }
        return pl;
    }
}
