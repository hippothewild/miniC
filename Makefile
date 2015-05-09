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
	$(JAVAC) Absyn/*.java
	$(JAVAC) sym.java parser.java Yylex.java

all: init
	$(JAVA) -classpath $(CLASSPATH) java_cup.Main minic.cup
	$(JAVA) JLex.Main minic.lex
	mv minic.lex.java Yylex.java
	$(JAVAC) Absyn/*.java
	$(JAVAC) -d $(CLASSPATH) sym.java parser.java Yylex.java

run:
	$(JAVA) -classpath $(CLASSPATH) Example.parser

test:
	$(JAVA) -classpath $(CLASSPATH) Example.parser < sample.c

vclean:
	-rm *~
	-rm Absyn/*.class
	-rm *.class
	-rm JLex/*.class
	-rm Yylex.java
	-rm parser.java
	-rm sym.java
	-rm java_cup/*.class
	-rm java_cup/runtime/*.class
	-rm Example/*.class
	-rm -r Example/
