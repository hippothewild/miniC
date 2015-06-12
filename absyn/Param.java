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

    public Param semanticAnalysis(int address) {
        if (getCurrentSymbolScope().contains(this.identifier.name)) {
            raiseError(SEMANTIC_ERR, "Parameter " + this.identifier.name + " is already declared.");
        }

        if (this.identifier.size > 0) {
            getCurrentSymbolScope().addSymbol(this.identifier.name, this.type.toArrayType(), this.identifier.size, address);
            getCurrentFunctionScope().addSymbol(this.type.toArrayType(), this.identifier.size);
        } else {
            getCurrentSymbolScope().addSymbol(this.identifier.name, this.type.toSingleType(), this.identifier.size, address);
            getCurrentFunctionScope().addSymbol(this.type.toSingleType(), this.identifier.size);
        }


        return this;
    }
}
