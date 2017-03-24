package grailsproject

class Book {

	String name;
  String area;
  Integer year;
  Integer numberPages;
  String edition;

	static belongsTo = [publisher: Publisher]

	static constraints = {
    name blank: false, maxSize: 200
    area blank: false, maxSize: 40, inList:["Humanas", "Exatas", "Biológicas"]
    year blank: false
    edition blank: false

  }

}
