JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
	SnakeControl.java \
	Board.java \
	State.java \
	snake.java

MAIN = snake

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	java $(MAIN) 30 5

clean:
	$(RM) *.class
