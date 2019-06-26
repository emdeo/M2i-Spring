# M2i-Spring

## Projet 1 : spring_personne

Objectif : enregistrer des objets **Employe** dont on souhaite connaître le montant de la prime salaire, de la prime enfants et de la prime totale.

### Fichier pom.xml

Fichier de configuration. On ajoute 2 dépendances depuis le site <a href="https://mvnrepository.com/artifact/org.springframework">Maven Repository</a> afin de pouvoir utiliser les bibliothèques **Context** et **Core**.

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.1.8.RELEASE</version>
    </dependency>
    
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.1.8.RELEASE</version>
    </dependency>
    
### Fichier beans.xml

Conteneur (fichier de configuration), permet d'instancier les objets créés dans **App.java** (voir plus bas) en allant récupérer les beans enregistrés.

Il y a plusieurs façons d'injecter une dépendance *via* le conteneur. La 1ère passe **par les setters** :

    <bean id="personne1"
    class="m2i.formation.personne_spring.Personne">
        <property name="_nom" value="Alpha" />
        <property name="_prenom" value="Alice" />
        <property name="_salaire" value="1164.97" />
        <property name="_nbEnfants" value="1" />
    </bean>

Injection **par constructeur** (notez qu'il n'y a pas besoin de compléter les paramètres dans l'ordre fixé par le constructeur. On peut les mettre dans n'importe quel ordre, tant qu'on précise la position du paramètre avec l'attribut **index**) :

    <bean id="personne2"
    class="m2i.formation.personne_spring.StaticPersonneFactory"
    factory-method="getPersonne">
        <constructor-arg index="3" value="0" />
        <constructor-arg index="0" value="Bravo" />
        <constructor-arg index="1" value="Bernard" />
        <constructor-arg index="2" value="2106.7" />
    </bean>

Injection **par la fabrique statique** (*object factory*). Cette méthode sollicite la classe *StaticEmployeFactory* (voir plus bas) :

    <bean id="personne3"
    class="m2i.formation.personne_spring.StaticEmployeFactory"
    factory-method="getPersonne">
        <constructor-arg value="Charly" />
        <constructor-arg value="Carole" />
        <constructor-arg value="3470.81" />
        <constructor-arg value="4" />
    </bean>

Injection **par fabrique non statique** (sollicite la classe *NonStaticEmployeFactory*, voir plus bas) :

    <bean id="personneFactory"
    class="m2i.formation.personne_spring.NonStaticEmployeFactory" />

    <bean id="personne4"
    class="m2i.formation.personne_spring.StaticPersonneFactory"
    factory-method="getPersonne" factory-bean="personneFactory">
        <constructor-arg value="Delta" />
        <constructor-arg value="Didier" />
        <constructor-arg value="4257.3" />
        <constructor-arg value="3" />
    </bean>


### Fichier App.java

C'est la classe publique du projet. On commence par créer le lien vers le conteneur :

    ApplicationContext appCon = new ClassPathXmlApplicationContext("resources/beans.xml");

Récupérer et instancier les objets **Employe** :

    Employe p1 = (Employe) appCon.getBean("personne1");
    Employe p2 = (Employe) appCon.getBean("personne2");
    Employe p3 = (Employe) appCon.getBean("personne3");
    Employe p4 = (Employe) appCon.getBean("personne4");

Enfin, un toString pour vérifier le résultat :

    System.out.println("Instanciation par setters\n" + p1);
    System.out.println("Instanciation par constructeur\n" + p2);
    System.out.println("Instanciation par fabrique statique\n" + p3);
    System.out.println("Instanciation par fabrique non statique\n" + p4);

### Fichier IEmploye.java

Liste des méthodes que la classe **Employe** doit implémenter :

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

### Fichier Employe.java

Cette classe réalise les interfaces *Serializable* et *IEmploye*. Voici ses attributs :

    private static final long serialVersionUID = -3800708334897652115L;

    private String _nom;
    private String _prenom;
    private float _salaire;
    private int _nbEnfants;

Méthode calculant la prime salaire (résultat arrondi au centième) :

    public float primeSalaire() {

        float prime = this._salaire * 0.1f;

        if (this._salaire < 1200) {
            prime = this._salaire * 0.6f;
        } else if (this._salaire < 2500) {
            prime = this._salaire * 0.2f;
        }

        return Math.round(prime * 100) / 100f;
    }
  
Méthode calculant la prime enfants :

    public short primeEnfants() {
        if (this._nbEnfants == 0) {
            return 0;
        } else if (this._nbEnfants < 3) {
            return (short) (this._nbEnfants * 400);
        }
        return (short) (this._nbEnfants * 700);
    }

Méthode calculant la prime totale (résultat arrondi au centième) :

    public float primeTotale() {
        return Math.round((primeSalaire() + primeEnfants()) * 100) / 100f;
    }

### Fichier StaticEmployeFactory.java

    public class StaticEmployeFactory {

        public static IEmploye getPersonne(String _nom, String _prenom, float _salaire, int _nbEnfants) {
            return new Employe(_nom, _prenom, _salaire, _nbEnfants);
        }

    }

### Fichier NonStaticEmployeFactory.java

    public class NonStaticEmployeFactory {

        public IEmploye getPersonne(String _nom, String _prenom, float _salaire, int _nbEnfants) {
            return new Employe(_nom, _prenom, _salaire, _nbEnfants);
        }

    }
