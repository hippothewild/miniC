package Absyn;

import java.util.*;

public class EmptyStmt extends Stmt {
    public EmptyStmt() {

    }

    public void printAST() {
        printWriter.println(";");
    }

    public EmptyStmt semanticAnalysis() {
        return this;
    }
}
