package grailsproject

class Course {
	String name	
	static belongsTo = [professor: Professor]
    static constraints = {
    }
}
