#JAVA = ""

run:
	java -classpath ./target channel.ChannelImpl

class:
	mkdir -p "./target"
	javac ChannelImpl.java -d ./target

clean:
	rm -f *.class
	rm -rdf ./target 

help:
	@echo 'Cleaning targets:'
	@echo '  clean - Remove most generated files but keep the source code'
	@echo '  class - Compile the source code to target'
	@echo '  clean - Remove all generated files'
	@echo ''
	@echo 'Configuration targets:'
	@echo ''