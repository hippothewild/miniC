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

    public Declaration semanticAnalysis() {
        SymbolScope ss = getCurrentSymbolScope();

        for (Identifier id : this.identList.identList) {
            if (ss != null && ss.contains(id.name)) {
                raiseError(SEMANTIC_ERR, "Variable " + id.name + " is already declared before.");
            }
            ss.addSymbol(id.name, this.type, id.size);
        }

        Declaration d = new Declaration(this.type, this.identList);
        return d;
    }
}
