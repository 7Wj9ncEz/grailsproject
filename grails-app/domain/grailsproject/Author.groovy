package grailsproject

class Author {

	String firstName;
  String lastName;

	static constraints = {
    firstName blank: false, maxSize: 40;
    lastName blank: false, maxSize: 40;
  }

}
