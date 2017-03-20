package grailsproject

class Professor extends User{

	String professorIdentifier;
	boolean canBorrow = true;

	static constraints = {
		professorIdentifier blank:false, unique:true, minSize:9, maxSize: 9;
	}

}