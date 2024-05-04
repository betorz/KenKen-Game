CLASS_OUTPUT = ../../EXE/out
JAR_OUTPUT = ../../EXE

CLASS_INPUT = ./main/domain/classes/*.java \
			  ./main/domain/controllers/*.java \
			  ./main/persistence/controllers/*.java \
			  ./main/domain/classes/exceptions/*.java




DRIVER_GAME_INPUT = ./drivers/DriverGame.java

DRIVER_RANKING_INPUT = ./drivers/DriverRanking.java

DRIVER_USER_INPUT = ./drivers/DriverUser.java

JUNIT_JARS = ../../lib/junit-4.13.2.jar:../../lib/hamcrest-core-1.3.jar

JUNIT_TESTS = ./test/*.java

all:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

allGame: compileGame jarGame game

allRanking: compileRanking jarRanking ranking

allUser: compileUser jarUser user

compileRanking:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(DRIVER_RANKING_INPUT)

compileGame:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(DRIVER_GAME_INPUT)

compileDomini:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(DRIVER_DOMINI_INPUT)

compileUser:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(DRIVER_USER_INPUT)

jarDomini:
	jar cfe $(JAR_OUTPUT)/DriverDomini.jar drivers.DriverDomini -C $(CLASS_OUTPUT) .

jarGame:
	jar cfe $(JAR_OUTPUT)/DriverGame.jar drivers.DriverGame -C $(CLASS_OUTPUT) .

jarRanking:
	jar cfe $(JAR_OUTPUT)/DriverRanking.jar drivers.DriverRanking -C $(CLASS_OUTPUT) .

jarUser:
	jar cfe $(JAR_OUTPUT)/DriverUser.jar drivers.DriverUser -C $(CLASS_OUTPUT) .

game:
	java -jar $(JAR_OUTPUT)/DriverGame.jar

ranking:
	java -jar $(JAR_OUTPUT)/DriverRanking.jar

user:
	java -jar $(JAR_OUTPUT)/DriverUser.jar

allTest: boardTest solverTest cellTest regionTest userTest rankingTest ctrlUserTest ctrlRankingTest

boardTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.BoardTest

solverTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.SolverTest

cellTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.CellTest

regionTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.RegionTest

userTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.UserTest

rankingTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.RankingTest

ctrlUserTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.CtrlUserTest

ctrlRankingTest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.CtrlRankingTest





clean:
	rm -rf $(CLASS_OUTPUT)/*
	rm -f $(JAR_OUTPUT)/*jar

cleanGerard:
	powershell -Command "Remove-Item -Recurse -Force ../../EXE/out/*"
	powershell -Command "Remove-Item -Recurse -Force ../../EXE/*jar"