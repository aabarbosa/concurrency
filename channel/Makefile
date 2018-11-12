#JAVA = ""

run:
	java -classpath ./target channel.ChannelImpl

pc:
	javac ProducerConsumerProblem.java -Xdiags:verbose -d ./target
runpc:
	java -classpath ./target ProducerConsumerProblem
class:
	mkdir -p "./target"
	javac ChannelImpl.java Channel.java -Xdiags:verbose -d ./target

clean:
	rm -f *.class
	rm -rdf ./target/*

help:
	@echo 'Cleaning targets:'
	@echo '  run    -   Execute the compiled source code'
	@echo '  class  -   Compile the source code to target'
	@echo '  clean  -   Remove all generated files'
	@echo ''