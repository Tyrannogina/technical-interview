package technical.interview

import grails.gorm.transactions.Transactional

@Transactional
class CalculationsService {
    public static final short TIMES_GENERATED_RANDOM = 100

    Integer addResult

    def calculationsSequence(Integer firstNumber, Integer secondNumber) {
        addResult = addNumbers(firstNumber, secondNumber)

        generateRandoms()
    }

    Integer addNumbers (Integer firstNumber, Integer secondNumber) {
        return firstNumber + secondNumber
    }

    def generateRandoms () {
        ArrayList randomList = []
        Random rand = new Random(addResult)
        TIMES_GENERATED_RANDOM.times {
            randomList << rand.nextInt()
        }
        println(randomList)
        randomList
    }

    DbAccessService db = new DbAccessService()



}
