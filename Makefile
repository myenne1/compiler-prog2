JFLAGS=-g

Parse/*.class: Parse/*.java Parse/Grm.java Parse/Yylex.java
	javac ${JFLAGS} Parse/*.java

Parse/Yylex.java: Parse/C.lex
	cd Parse; java JLex.Main C.lex; mv C.lex.java Yylex.java

Parse/Grm.java: Parse/Grm.cup
	cd Parse; java java_cup.Main -parser Grm -expect 3 -nonterms -dump_grammar -dump_states <Grm.cup >Grm.out 2>Grm.err

clean:
	cp Parse/Yylex* ./.
	rm -f */*.class Parse/Grm.java Parse/Grm.err Parse/Grm.out Parse/Yylex.java
	mv Yylex* ./Parse/

supercl: 
	cp Parse/Yylex* ./.
	# Remove all class and generated files
	rm -f Parse/Grm.java Parse/sym.java Parse/Yylex.java Parse/parser.java
	rm -f Parse/*.class Absyn/*.class Symbol/*.class
	rm -f Parse/Grm.err Parse/Grm.out *.class
	rm -f Grm_grammar.dump

.PHONY: all clean