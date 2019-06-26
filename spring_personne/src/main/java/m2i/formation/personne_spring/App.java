package m2i.formation.personne_spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {

		// Lien vers le fichier de configuration
		ApplicationContext appCon = new ClassPathXmlApplicationContext("resources/beans.xml");

		// Récupérer et instancier une classe
		Employe p1 = (Employe) appCon.getBean("personne1");
		Employe p2 = (Employe) appCon.getBean("personne2");
		Employe p3 = (Employe) appCon.getBean("personne3");
		Employe p4 = (Employe) appCon.getBean("personne4");

		// toString
		System.out.println("Instanciation par setters\n" + p1);
		System.out.println("Instanciation par constructeur\n" + p2);
		System.out.println("Instanciation par fabrique statique\n" + p3);
		System.out.println("Instanciation par fabrique non statique\n" + p4);

	}
}
