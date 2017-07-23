package technical.interview

import grails.gorm.transactions.Transactional

@Transactional
class CalculationsService {
    public static final short TIMES_GENERATE_RANDOM = 100

    Integer addResult

    def calculationsSequence(Integer firstNumber, Integer secondNumber) {
        // Calculate stuff
        addResult = addNumbers(firstNumber, secondNumber)
        def randoms = generateRandoms()

        // Save into file
        def tempPath = new File("temp")
        if (!tempPath.exists()){
            tempPath = new File("temp").mkdirs()
        }

        File tempFile = new File("${tempPath}/random_numbers${addResult}.json")
        tempFile.write(randoms.join(";"))

        // Read from file
        def savedRandoms = tempFile.readLines()

        return savedRandoms[0].split(";").collect{it as Integer}
    }

    Integer addNumbers (Integer firstNumber, Integer secondNumber) {
        return firstNumber + secondNumber
    }

    def generateRandoms () {
        ArrayList randomList = []
        Random rand = new Random(addResult)
        TIMES_GENERATE_RANDOM.times {
            randomList << rand.nextInt()
        }
        randomList
    }

    def saveRandoms(randomsList, addResult) {
        RandomNumbersStorage rand = new RandomNumbersStorage(randomNumbersList: randomsList, sumResult: addResult)
        rand.save(flush:true)

        def values = rand.findAll()
        values.each {
            println(it.randomNumbersList)
            println(it.sumResult)
        }
    }
}
