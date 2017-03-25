package grailsproject

class BootStrap {

    def init = { servletContext ->
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
