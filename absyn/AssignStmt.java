package Absyn;

import java.util.*;

public class AssignStmt extends Stmt {
    Assign assign;

    public AssignStmt(Assign a) {
        assign = a;
    }

    public void printAST() {
        assign.printAST();
        printWriter.println(";");
    }

    public AssignStmt semanticAnalysis() {
        AssignStmt a = new AssignStmt(null);
        a.assign = this.assign.semanticAnalysis();
        return a;
    }
}
