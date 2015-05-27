package Absyn;

import java.util.*;

public class Type extends Absyn {
    TypeName typeName;

    public Type(TypeName t) {
        this.typeName = t;
    }

    public void printAST() {
        switch (typeName) {
        case INT:
            printWriter.print("int");
            break;
        case FLOAT:
            printWriter.print("float");
            break;
        case INT_ARRAY:
            printWriter.print("int[]");
            break;
        case FLOAT_ARRAY:
            printWriter.print("float[]");
            break;
        }
    }

    public Type toArrayType() {
        switch (this.typeName) {
        case INT:
            return new Type(TypeName.INT_ARRAY);
        case FLOAT:
            return new Type(TypeName.FLOAT_ARRAY);
        case INT_ARRAY:
            return new Type(TypeName.INT_ARRAY);
        case FLOAT_ARRAY:
            return new Type(TypeName.FLOAT_ARRAY);
        }
        raiseError(TYPE_ERR, "FATAL - Type not specified");
        return null;
    }

    public Type toSingleType() {
        switch (this.typeName) {
        case INT:
            return new Type(TypeName.INT);
        case FLOAT:
            return new Type(TypeName.FLOAT);
        case INT_ARRAY:
            return new Type(TypeName.INT);
        case FLOAT_ARRAY:
            return new Type(TypeName.FLOAT);
        }
        raiseError(TYPE_ERR, "FATAL - Type not specified");
        return null;
    }

    public Type semanticAnalysis() {
        return this;
    }
}
