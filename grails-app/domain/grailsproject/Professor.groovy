package grailsproject

class Professor extends User{

	String professorIdentifier;
	
	static hasMany = [courses:Course]

	static constraints = {
		professorIdentifier blank:false, unique:true, minSize:9, maxSize: 9;
	}

	String toString() {
		return this.firstName + " " + this.lastName
	}

}