package grailsproject

class BootStrap {

	def springSecurityService

    def init = { servletContext ->
    	def role = new Role(authority: 'ROLE_ADMIN', description: 'Admin role').save(flush: true)
    	def userRole = new Role(authority: 'ROLE_USER', description: 'User role').save(flush: true)
    	def studentRole = new Role(authority: 'ROLE_STUDENT', description: 'Student role').save(flush: true)
    	def professorRole = new Role(authority: 'ROLE_PROFESSOR', description: 'Professor role').save(flush: true)
    	def staffRole = new Role(authority: 'ROLE_STAFF', description: 'Staff role').save(flush: true)
    }
    def destroy = {
    }
}
