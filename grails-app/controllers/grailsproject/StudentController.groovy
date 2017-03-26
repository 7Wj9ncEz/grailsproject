package grailsproject

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class StudentController{

    def springSecurityService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_STAFF', 'IS_AUTHENTICATED_REMEMBERED'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Student.list(params), model:[studentCount: Student.count()]
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def show(Student student) {
        if(student?.id == springSecurityService.currentUser.id ){
            respond student
        }else{
            respond springSecurityService.currentUser
        }
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def create() {
        respond new Student(params)
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    @Transactional
    def save(Student student) {
        if (student == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (student.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond student.errors, view:'create'
            return
        }

        def studentt = student.save flush:true
        UserRole.create studentt, Role.findByAuthority('ROLE_STUDENT')

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*' { respond student, [status: CREATED] }
        }
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def edit(Student student) {
        if(student?.id == springSecurityService.currentUser.id ){
            respond student
        }else{
            respond springSecurityService.currentUser
        }
    }

    @Transactional
    @Secured(['ROLE_USER', 'IS_AUTHENTICATED_REMEMBERED'])
    def update(Student student) {
        if (student == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (student.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond student.errors, view:'edit'
            return
        }

        def studentt = student.save flush:true
        UserRole.create studentt, Role.findByAuthority('ROLE_STUDENT')

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*'{ respond student, [status: OK] }
        }
    }

    @Transactional
    @Secured(['ROLE_USER', 'IS_AUTHENTICATED_REMEMBERED'])
    def delete(Student student) {

        if (student == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        student.addToAuthorities(studentRole)
        student.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
