package Absyn;

import java.util.*;

public class FuncList {
	ArrayList<FunctionDec> funcList;

	public FuncList(FunctionDec f) {
		funcList = new ArrayList<FunctionDec>();
		funcList.add(f);
	}

	public void add(FunctionDec f) {
		funcList.add(f);
	}
}
