package grailsproject

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured


@Transactional(readOnly = true)
class ReservationController {

    def springSecurityService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_STAFF', 'IS_AUTHENTICATED_REMEMBERED'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Reservation.list(params), model:[reservationCount: Reservation.count()]
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def show(Reservation reservation) {
        if(reservation?.user.id == springSecurityService.currentUser.id ){
            respond reservation
        }else{
            userId = springSecurityService.currentUser.id
            respond reservation.user.userId
        }
    }

    @Secured(['ROLE_STAFF', 'IS_AUTHENTICATED_REMEMBERED'])
    def create() {
        respond new Reservation(params)
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def save(Reservation reservation) {
        if (reservation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reservation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reservation.errors, view:'create'
            return
        }

        reservation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservation.id])
                redirect reservation
            }
            '*' { respond reservation, [status: CREATED] }
        }
    }

    @Secured(['ROLE_STAFF', 'ROLE_STUDENT', 'IS_AUTHENTICATED_REMEMBERED'])
    def edit(Reservation reservation) {
        if(reservation?.user.id == springSecurityService.currentUser.id ){
            respond reservation
        }else{
            userId = springSecurityService.currentUser.id
            respond reservation.user.userId
        }
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def update(Reservation reservation) {
        if (reservation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reservation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reservation.errors, view:'edit'
            return
        }

        reservation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservation.id])
                redirect reservation
            }
            '*'{ respond reservation, [status: OK] }
        }
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def delete(Reservation reservation) {

        if (reservation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        reservation.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservation.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
