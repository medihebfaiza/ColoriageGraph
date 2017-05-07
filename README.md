# ColoriageGraph
A Graph Coloring Algorithm 

## Compiling
javac -d bin src/coloriage/*.java 

## Running 
java -classpath bin coloriage.Test 

## Generating Doc
javadoc -d doc -encoding ISO-8859-1 -docencoding UTF-8 -charset UTF-8 src/coloriage/*.java

## Packaging 
jar cvfm coloriage.jar manifest.txt -C bin .

## Running jar 
java -jar coloriage.jar

## Updating package
jar uf coloriage.jar doc

