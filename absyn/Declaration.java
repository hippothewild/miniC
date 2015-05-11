package Absyn;

import java.util.*;

public class Declaration extends Absyn {
    Type type;
    IdentList identList;

    public Declaration(Type t, IdentList i) {
        type = t;
        identList = i;
    }

    public void printAST() {
        type.printAST();
        printWriter.print(" ");
        identList.printAST();
        printWriter.println(";");
    }

    public void printSymTable() {
        // Symbol got : count symbolCnt or each declaration and print it.
        String typeStr = "NULL";
        switch (type.typeName) {
            case INT:
                typeStr = "int";
                break;
            case FLOAT:
                typeStr = "float";
                break;
        }
        for (Identifier i : identList.identList) {
            printSymTableRow(typeStr, i.name, i.size, "variable");
        }
    }
}
