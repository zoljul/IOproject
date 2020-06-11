package mainPackage;

import java.util.ArrayList;

public class Artwork {

	private String title;
	private String description;
	private ArrayList<String> tags;
	private String URL;

	public Artwork(String title, String desc, ArrayList<String> tags, String URL) {
		this.title = title;
		this.description = desc;
		this.tags = tags;
		this.URL = URL;
	}

	public String getURL() {
		return URL;
	}
}