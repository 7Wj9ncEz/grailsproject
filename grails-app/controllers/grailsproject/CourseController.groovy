package grailsproject

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
@Transactional(readOnly = true)
class CourseController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Course.list(params), model:[courseCount: Course.count()]
    }

    def show(Course course) {
        respond course
    }

    def create() {
        [books:Book.list(), professors:Professor.list()]
    }

    @Transactional
    def save() {
        def course = new Course(params.course)
        if (course == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (course.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond course.errors, view:'create'
            return
        }

        course.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect course
            }
            '*' { respond course, [status: CREATED] }
        }
    }

    def edit(Course course) {
        respond course, model:[professors:Professor.list(), books:Book.list()]
    }

    @Transactional
    def update(Course course) {
        if (course == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (course.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond course.errors, view:'edit'
            return
        }

        course.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect course
            }
            '*'{ respond course, [status: OK] }
        }
    }

    @Transactional
    def delete(Course course) {

        if (course == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        course.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
