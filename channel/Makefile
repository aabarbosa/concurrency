#JAVA = ""

run:
	java -classpath ./target City
class:
	mkdir -p "./target"
	javac City.java -Xdiags:verbose -d ./target

clean:
	rm -f *.class
	rm -rdf ./target/*

help:
	@echo 'Cleaning targets:'
	@echo '  run    -   Execute the compiled source code'
	@echo '  class  -   Compile the source code to target'
	@echo '  clean  -   Remove all generated files'
	@echo ''