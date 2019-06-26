package m2i.formation.personne_spring;

public interface IEmploye {

	float primeSalaire();

	short primeEnfants();

	float primeTotale();

	String get_nom();

	void set_nom(String _nom);

	String get_prenom();

	void set_prenom(String _prenom);

	float get_salaire();

	void set_salaire(float _salaire);

	int get_nbEnfants();

	void set_nbEnfants(int _nbEnfants);

}