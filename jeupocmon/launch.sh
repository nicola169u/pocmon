rm -r target
mvn package
cd target
java -cp jeupocmon-1.0.jar main.java.com.exemple.Main
