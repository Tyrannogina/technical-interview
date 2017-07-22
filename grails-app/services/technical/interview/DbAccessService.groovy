package technical.interview

import grails.gorm.transactions.Transactional

@Transactional
class DbAccessService {

    def saveRandoms (randomsList, addResult) {
        randomsList.each{
            RandomNumber rand = new RandomNumber(randomNum: it, sumResult: addResult)
            rand.save(flush:true)
        }
    }
}
