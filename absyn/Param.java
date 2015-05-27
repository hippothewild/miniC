package Absyn;

import java.util.*;

public class Param extends Absyn {
    Type type;
    Identifier identifier;

    public Param(Type t, Identifier i) {
        type = t;
        identifier = i;
    }

    public void printAST() {
        type.printAST();
        printWriter.print(" ");
        identifier.printAST();
    }

    public Param semanticAnalysis() {
        if (getCurrentSymbolScope().contains(this.identifier.name)) {
            raiseError(SEMANTIC_ERR, "Parameter " + this.identifier.name + " is already declared.");
        }
        getCurrentSymbolScope().addSymbol(this.identifier.name, this.type, this.identifier.size);
        getCurrentFunctionScope().addSymbol(this.type, this.identifier.size);

        return this;
    }
}
