package grailsproject

class BootStrap {

	def springSecurityService

    def init = { servletContext ->
    	def role = new Role(authority: 'ROLE_ADMIN', description: 'Admin role').save(flush: true)
    	def userRole = new Role(authority: 'ROLE_USER', description: 'User role').save(flush: true)
    	def studentRole = new Role(authority: 'ROLE_STUDENT', description: 'Student role').save(flush: true)
    	def professorRole = new Role(authority: 'ROLE_PROFESSOR', description: 'Professor role').save(flush: true)
    	def staffRole = new Role(authority: 'ROLE_STAFF', description: 'Staff role').save(flush: true)
    	
    	def publisher1 = new Publisher(name:"publisher1").save()
    	def publisher2 = new Publisher(name:"publisher2").save()    	

    	def book1 = new Book(name:"Book 1",area:"Humanas",year:2014,numberPages:201,edition:"1")
    	book1.publisher = publisher1
    	book1.save()

    	def book2 = new Book(name:"Book 2",area:"Humanas",year:2014,numberPages:201,edition:"1")
    	book2.publisher = publisher2
    	book2.save()

    	def book3 = new Book(name:"Book 3",area:"Humanas",year:2014,numberPages:201,edition:"1")
    	book3.publisher = publisher1
    	book3.save()

    	def book4 = new Book(name:"Book 4",area:"Humanas",year:2014,numberPages:201,edition:"1")
    	book4.publisher = publisher2
    	book4.save()
    }
    def destroy = {
    }
}
