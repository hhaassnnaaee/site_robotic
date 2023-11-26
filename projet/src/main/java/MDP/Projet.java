package MDP;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
@ManagedBean
@Named
@RequestScoped
@SessionScoped

public class Projet {

    String projectName;
    String description;
    String composants;
    String etapes;
    
    

    public Projet(String projectName, String description, String composants, String etapes) {
		this.projectName = projectName;
		this.description = description;
		this.composants = composants;
		this.etapes = etapes;
	}

	public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComposants() {
		return composants;
	}

	public void setComposants(String composants) {
		this.composants = composants;
	}

	public String getEtapes() {
		return etapes;
	}

	public void setEtapes(String etapes) {
		this.etapes = etapes;
	}

	@Override
	public String toString() {
		return "AjoutProjet [projectName=" + projectName + ", description=" + description + ", composants=" + composants
				+ ", etapes=" + etapes + "]";
	}
	
	
}
