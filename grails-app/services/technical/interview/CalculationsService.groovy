package technical.interview

import grails.gorm.transactions.Transactional

@Transactional
class CalculationsService {
    public static final short TIMES_GENERATE_RANDOM = 200

    Integer addResult

    def calculationsSequence(Integer firstNumber, Integer secondNumber) {
        // Calculate stuff
        addResult = addNumbers(firstNumber, secondNumber)
        ArrayList randoms = generateRandoms()
        // Save into file
        def tempFile = saveIntoFile(randoms)
        // Read from file
        String savedRandoms = readFromFile(tempFile)
        // Make it back to an array
        ArrayList savedRandomsArr = savedRandoms.split(";").collect{it as Float}
        ['randomNumbersList': savedRandomsArr, 'sumResult': addResult]
    }

    Integer addNumbers (Integer firstNumber, Integer secondNumber) {
        return firstNumber + secondNumber
    }

    def generateRandoms () {
        ArrayList randomList = []
        Random rand = new Random(addResult)
        TIMES_GENERATE_RANDOM.times {
            randomList << rand.nextGaussian()
        }
        randomList
    }

    /******** File manager ***************/
    def saveIntoFile(ArrayList data) {
        // Save into file
        File tempFile = new File("${createPath()}/random_numbers${addResult}.json")
        tempFile.write(data.join(";")) // As a string
        tempFile
    }

    def createPath() {
        def tempPath = new File("temp")
        if (!tempPath.exists()){
            tempPath = new File("temp").mkdirs()
        }
        tempPath
    }

    def readFromFile(file) {
        ArrayList line = file.readLines()
        line[0]
    }
}
