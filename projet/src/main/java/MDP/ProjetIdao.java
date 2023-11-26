package MDP;

public interface ProjetIdao extends IDAO<Projet> {

	void add(String projectName, String description, String composants, String etapes);
	 public String chercher(String projectName) ;

}
