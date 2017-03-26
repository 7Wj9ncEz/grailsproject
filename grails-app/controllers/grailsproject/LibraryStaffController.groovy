package grailsproject

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class LibraryStaffController {

    def springSecurityService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_STAFF', 'IS_AUTHENTICATED_REMEMBERED'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond LibraryStaff.list(params), model:[libraryStaffCount: LibraryStaff.count()]
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def show(LibraryStaff libraryStaff) {
        if(libraryStaff?.id == springSecurityService.currentUser.id ){
            respond libraryStaff
        }else{
            respond springSecurityService.currentUser
        }  
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def create() {
        respond new LibraryStaff(params)
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def save(LibraryStaff libraryStaff) {
        if (libraryStaff == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (libraryStaff.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond libraryStaff.errors, view:'create'
            return
        }

        def staff = libraryStaff.save flush:true
        UserRole.create staff, Role.findByAuthority('ROLE_STAFF')

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'libraryStaff.label', default: 'LibraryStaff'), libraryStaff.id])
                redirect libraryStaff
            }
            '*' { respond libraryStaff, [status: CREATED] }
        }
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def edit(LibraryStaff libraryStaff) {
        if(libraryStaff?.id == springSecurityService.currentUser.id ){
            respond libraryStaff
        }else{
            respond springSecurityService.currentUser
        }
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def update(LibraryStaff libraryStaff) {
        if (libraryStaff == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (libraryStaff.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond libraryStaff.errors, view:'edit'
            return
        }

        def staff = libraryStaff.save flush:true
        UserRole.create staff, Role.findByAuthority('ROLE_STAFF')

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'libraryStaff.label', default: 'LibraryStaff'), libraryStaff.id])
                redirect libraryStaff
            }
            '*'{ respond libraryStaff, [status: OK] }
        }
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def delete(LibraryStaff libraryStaff) {

        if (libraryStaff == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        libraryStaff.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'libraryStaff.label', default: 'LibraryStaff'), libraryStaff.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'libraryStaff.label', default: 'LibraryStaff'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
