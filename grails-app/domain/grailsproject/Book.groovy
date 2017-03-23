package grailsproject

class Book {

	String name;
  String area;
  Integer year;
  Integer numberPages;
  String edition;

	static constraints = {
    name blank: false, maxSize: 200
    area blank: false, maxSize: 40
    year blank: false
    edition blank: false
  }

}
