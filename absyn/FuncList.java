package Absyn;

import java.util.*;

public class FuncList extends Absyn {
	ArrayList<FunctionDec> funcList;

	public FuncList(FunctionDec f) {
		funcList = new ArrayList<FunctionDec>();
		if (f != null) {
			funcList.add(f);
		}
	}

	public void add(FunctionDec f) {
		if (f != null) {
			funcList.add(f);
		}
	}

	public void printAST() {
        for (FunctionDec f : funcList) {
            f.printAST();
        }
    }

	public void printSymTable() {
        for (FunctionDec f : funcList) {
            f.printSymTable();
        }
    }
}
