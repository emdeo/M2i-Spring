package m2i.formation.personne_spring;

public class NonStaticEmployeFactory {

	public IEmploye getPersonne(String _nom, String _prenom, float _salaire, int _nbEnfants) {
		return new Employe(_nom, _prenom, _salaire, _nbEnfants);
	}

}
