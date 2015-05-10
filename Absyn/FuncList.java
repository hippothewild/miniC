package Absyn;

import java.util.*;
import Symbol.Symbol;

public class FuncList {
	 public FunctionDec head;
	 public FuncList tail;
	 public FuncList(FunctionDec h, FuncList t) {head=h; tail=t;}
}
