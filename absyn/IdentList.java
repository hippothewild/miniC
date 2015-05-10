package Absyn;

import java.util.*;

public class IdentList extends Absyn {
	ArrayList<Identifier> identList;

	public IdentList(Identifier i) {
		identList = new ArrayList<Identifier>();
		identList.add(i);
	}

	public void add(Identifier i) {
		identList.add(i);
	}

	public void printAST() {
        for (Identifier i : identList.subList(0, identList.size()-1)) {
            i.printAST();
			printWriter.print(", ");
        }
		identList.get(identList.size() - 1).printAST();
    }
}
