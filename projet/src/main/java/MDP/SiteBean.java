package MDP;

import jakarta.faces.annotation.RequestMap;
import jakarta.faces.event.NamedEvent;

@NamedEvent
@RequestMap
public class SiteBean {

    public String navigateToHome() {
        // Navigation logic to the home page
        return "HomePage.xhtml"; // "home" corresponds to the logical name of the home page
    }

    public String navigateToSignIn() {
        // Navigation logic to the sign-in page
        return "Signin.xhtml"; // "signin" corresponds to the logical name of the sign-in page
    }

    public String navigateToLogin() {
        // Navigation logic to the login page
        return "Authentificatin.xhtml"; // "login" corresponds to the logical name of the login page
    }
}