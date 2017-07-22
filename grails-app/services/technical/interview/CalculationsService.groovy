package technical.interview

import grails.gorm.transactions.Transactional

@Transactional
class CalculationsService {
    public static final short TIMES_GENERATE_RANDOM = 100

    Integer addResult

    def calculationsSequence(Integer firstNumber, Integer secondNumber) {
        addResult = addNumbers(firstNumber, secondNumber)
        generateRandoms()
        return true
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
        saveRandoms(randomList, addResult)
        return randomList
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
