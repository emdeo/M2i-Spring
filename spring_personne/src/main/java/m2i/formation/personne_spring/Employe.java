package m2i.formation.personne_spring;

import java.io.Serializable;

public class Employe implements Serializable, IEmploye {

	private static final long serialVersionUID = -3800708334897652115L;

	private String _nom;
	private String _prenom;
	private float _salaire;
	private int _nbEnfants;

	public Employe() {
		super();
	}

	public Employe(String _nom, String _prenom, float _salaire, int _nbEnfants) {
		super();
		this._nom = _nom;
		this._prenom = _prenom;
		this._salaire = _salaire;
		this._nbEnfants = _nbEnfants;
	}

	public float primeSalaire() {

		float prime = this._salaire * 0.1f;

		if (this._salaire < 1200) {
			prime = this._salaire * 0.6f;
		} else if (this._salaire < 2500) {
			prime = this._salaire * 0.2f;
		}

		return Math.round(prime * 100) / 100f;
	}

	public short primeEnfants() {
		if (this._nbEnfants == 0) {
			return 0;
		} else if (this._nbEnfants < 3) {
			return (short) (this._nbEnfants * 400);
		}
		return (short) (this._nbEnfants * 700);
	}

	public float primeTotale() {
		return Math.round((primeSalaire() + primeEnfants()) * 100) / 100f;
	}

	public String get_nom() {
		return _nom;
	}

	public void set_nom(String _nom) {
		this._nom = _nom;
	}

	public String get_prenom() {
		return _prenom;
	}

	public void set_prenom(String _prenom) {
		this._prenom = _prenom;
	}

	public float get_salaire() {
		return _salaire;
	}

	public void set_salaire(float _salaire) {
		this._salaire = _salaire;
	}

	public int get_nbEnfants() {
		return _nbEnfants;
	}

	public void set_nbEnfants(int _nbEnfants) {
		this._nbEnfants = _nbEnfants;
	}

	@Override
	public String toString() {
		return "Personne :" + "\n  " + _prenom + " " + _nom + "\n  " + _nbEnfants + " enfant(s)\t(prime = "
				+ primeEnfants() + "€)" + "\n  " + _salaire + "€\t(prime = " + primeSalaire() + "€)"
				+ "\n  Prime totale = " + primeTotale() + "€" + "\n";
	}

}
