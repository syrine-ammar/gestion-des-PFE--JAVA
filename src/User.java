package trial;

public class User {
    private String password;
    private String nom;
    private String prenom;
    private String carteCin;
    private String mail;

    public User(String carteCin, String nom, String prenom , String mail,String password) {
        this.setPassword(password);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setCarteCin(carteCin);
        this.setMail(mail);
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCarteCin() {
		return carteCin;
	}

	public void setCarteCin(String carteCin) {
		this.carteCin = carteCin;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}