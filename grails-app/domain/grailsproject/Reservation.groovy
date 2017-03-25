package grailsproject

class Reservation {

	Date beginDate;
	Date endDate;
	String observations;
	//static hasMany = [books:Book];
	//static belongsTo = [user: User];
	Book book;
	static belongsTo = [user: User];
    static constraints = {
    }
}
