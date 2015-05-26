package Absyn;

import java.util.*;

public class Type extends Absyn {
    TypeName typeName;

    public Type(TypeName t) {
        typeName = t;
    }

    public void printAST() {
        switch (typeName) {
            case INT:
                printWriter.print("int");
                break;
            case FLOAT:
                printWriter.print("float");
                break;
        }
    }

    public Type semanticAnalysis() {
        return this;
    }
}
