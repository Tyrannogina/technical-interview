package technical.interview

import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional
class RandomNumbersStorageController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        render (view: '/randomNumbersStorage/calculate')
    }

    def graph(ValidateNumberCommand command) {
        if (!request.post) {
            return
        }

        if (!command.validate()) {
            render view: "/error"
            return
        }

        def calculations = new CalculationsService()
        def randomMap = calculations.calculationsSequence(command.firstNumber.toInteger(), command.secondNumber.toInteger())

        save(new RandomNumbersStorage(randomMap as Object))
        /*
        def potato = RandomNumbersStorage.findAll()
        potato.each{
            println it.randomNumbersList
            println it.sumResult
        }
        */

        render view: 'graph', model: [typeList: randomMap]
    }

    def list (Integer max) {
        params.max = Math.min(max ?: 10, 100)
        println RandomNumbersStorage.list(params)

        respond RandomNumbersStorage.list(params), model:[randomNumbersStorageCount: RandomNumbersStorage.count()]
    }

    def show(RandomNumbersStorage randomNumbersStorage) {
        respond randomNumbersStorage
    }

    def create() {
        respond new RandomNumbersStorage(params)
    }

    @Transactional
    def save(RandomNumbersStorage randomNumbersStorage) {
        if (randomNumbersStorage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        if (randomNumbersStorage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond randomNumbersStorage.errors, view:'create'
            return
        }

        randomNumbersStorage.save flush:true
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'randomNumbersStorage.label', default: 'RandomNumbersStorage'), randomNumbersStorage.id])
                //redirect randomNumbersStorage
            }
            '*' { /*respond randomNumbersStorage, [status: CREATED] */}
        }
    }

    def edit(RandomNumbersStorage randomNumbersStorage) {
        respond randomNumbersStorage
    }

    @Transactional
    def update(RandomNumbersStorage randomNumbersStorage) {
        if (randomNumbersStorage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (randomNumbersStorage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond randomNumbersStorage.errors, view:'edit'
            return
        }

        randomNumbersStorage.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'randomNumbersStorage.label', default: 'RandomNumbersStorage'), randomNumbersStorage.id])
                redirect randomNumbersStorage
            }
            '*'{ respond randomNumbersStorage, [status: OK] }
        }
    }

    @Transactional
    def delete(RandomNumbersStorage randomNumbersStorage) {

        if (randomNumbersStorage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        randomNumbersStorage.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'randomNumbersStorage.label', default: 'RandomNumbersStorage'), randomNumbersStorage.id])
                redirect action:"list", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'randomNumbersStorage.label', default: 'RandomNumbersStorage'), params.id])
                redirect action: "list", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

//Duplicated in src/main/groovy until I find a way of importing that one successfully
class ValidateNumberCommand {
    Integer firstNumber, secondNumber

    static constraints = {
        firstNumber blank: false, validator: { val ->
            if (!val || val < 0) {
                return 'basiccontroller.msg.invalid'
            }
        }
        secondNumber blank: false, validator: { val ->
            if (!val || val < 0) {
                return 'basiccontroller.msg.invalid'
            }
        }
    }
}