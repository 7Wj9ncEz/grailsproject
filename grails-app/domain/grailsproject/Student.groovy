package grailsproject

class Student extends User{

	String matriculation;
	Integer semester;
	String degree;

	static constraints = {
		matriculation blank:false, unique:true, minSize:9, maxSize:9 ;
		semester blank:false, inList:[1,2,3,4,5,6,7,8,9,10,11,12,13,14];
		degree blank:false, inList:["Engenharia - Ciclo Básico ", "Engenharia Eletrônica", "Engenharia de Software" , "Engenharia de Energia", "Engenharia Automotiva", "Engenharia Aeroespacial"];
	}

}