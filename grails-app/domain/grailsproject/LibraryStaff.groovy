package grailsproject

class LibraryStaff extends User{

	String employeeID;
	String jobTitle;
    static constraints = {
    	employeeID unique:true;
    	jobTitle inList:["Atendente", "Balconista", "Auxiliar"];
    }
}
