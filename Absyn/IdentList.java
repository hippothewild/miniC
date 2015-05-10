package Absyn;

import java.util.*;

public class IdentList {
	ArrayList<Identifier> identList;

	public IdentList(Identifier i) {
		identList = new ArrayList<Identifier>();
		identList.add(i);
	}

	public void add(Identifier i) {
		identList.add(i);
	}
}
