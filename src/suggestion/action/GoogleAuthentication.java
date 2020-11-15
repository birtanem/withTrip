package suggestion.action;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthentication extends Authenticator {
	PasswordAuthentication passAuth;
    
    public GoogleAuthentication(){
        passAuth = new PasswordAuthentication("lkj0511kr", "cbdqqcubctvdvubk");
    }
 
    public PasswordAuthentication getPasswordAuthentication() {
        return passAuth;
    }
}
