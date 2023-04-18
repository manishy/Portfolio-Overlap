gradle clean build -x test --no-daemon
echo ""
echo ""
echo "SAMPLE INPUT-OUTPUT 1"
echo ""
java -jar build/libs/geektrust.jar sample_input/input1.txt
echo ""
echo ""
echo "SAMPLE INPUT-OUTPUT 2"
echo ""
java -jar build/libs/geektrust.jar sample_input/input2.txt