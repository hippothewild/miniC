# Referred from https://github.com/gmh33/Java-CUP-jLex-Example

JAVAC = javac
JAVA = java
CLASSPATH = .

init:
	$(JAVAC) JLex/*.java
	$(JAVAC) java_cup/runtime/*.java
	$(JAVAC) java_cup/*.java

parse: minic.cup
	$(JAVA) -classpath $(CLASSPATH) java_cup.Main minic.cup

lex: minic.lex
	$(JAVA) JLex.Main minic.lex
	mv minic.lex.java Yylex.java

build: sym.java parser.java Yylex.java
	$(JAVAC) Program.java
	$(JAVAC) sym.java parser.java Yylex.java

all:
	$(JAVA) -classpath $(CLASSPATH) java_cup.Main minic.cup
	$(JAVA) JLex.Main minic.lex
	mv minic.lex.java Yylex.java
	$(JAVAC) Program.java
	$(JAVAC) sym.java parser.java Yylex.java

run:
	$(JAVA) -classpath $(CLASSPATH) parser

test:
	$(JAVA) -classpath $(CLASSPATH) parser < sample.c

clean:
	-rm *.class
	-rm Yylex.java
	-rm parser.java
	-rm sym.java

vclean:
	-rm *.class
	-rm Yylex.java
	-rm parser.java
	-rm sym.java
	-rm java_cup/*.class
	-rm java_cup/runtime/*.class
