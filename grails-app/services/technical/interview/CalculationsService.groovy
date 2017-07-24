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
        String pathName = 'temp'
        def path = createPath(pathName)
        File tempFile = new File("${pathName}/random_numbers${addResult}.json")
        tempFile.write(data.join(";")) // As a string
        tempFile
    }

    def createPath(pathName) {
        if (!(new File(pathName).exists())){
            new File(pathName).mkdirs()
        }
    }

    def readFromFile(file) {
        ArrayList line = file.readLines()
        line[0]
    }
}
