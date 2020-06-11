package mainPackage;

import java.util.ArrayList;

public class User {

	private int premiumLevel;
	private final String username;
	private String password;
	private ArrayList<Artwork> uploadedArtworks;
	private boolean defaultPaymentMeasure;

	public User(String nazwaUz, String haslo) {
		this.username = nazwaUz;
		this.password = haslo;
		premiumLevel = 0;
		uploadedArtworks = new ArrayList<Artwork>();
		defaultPaymentMeasure = true;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPremiumLevel(int premiumLevel) {
		this.premiumLevel = premiumLevel;
	}

	public int getPremiumLevel() {
		return premiumLevel;
	}

	public boolean uploadToGallery(Artwork graf) {
		return uploadedArtworks.add(graf);
	}

	public ArrayList<Artwork> getUploadedArtworks() {
		return this.uploadedArtworks;
	}

}