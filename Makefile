# Makefile for Assignment1
# david

JAVAC = /usr/bin/javac
.SUFFIXES: .java .class
SRCDIR = src
BINDIR = bin
TESTDIR = testing
OUTPUTDIR = out/data

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES = MedianFilter.class SerialMedianFilter.class ParallelMedianFilter.class
CLASS_FILES = $(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	 rm $(BINDIR)/*.class

run-ser: $(CLASS_FILES)
	java -cp $(BINDIR) SerialMedianFilter $(in) $(width) $(out) >> $(OUTPUTDIR)/serial/$(out)

run-par: $(CLASS_FILES)
	java -cp $(BINDIR) ParallelMedianFilter $(in) $(width) $(cutoff) $(out) >> $(OUTPUTDIR)/parallel/$(out)
