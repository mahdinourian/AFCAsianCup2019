package desertstars.afcasiancup2019;


public class SearchItems {

	private String rank;
	private String name;
	private Integer icon;

	public SearchItems(String rank, String name, Integer icon)  {
		this.rank = rank;
		this.name= name;
		this.icon = icon;
	}

	public String getRank() {
		return this.rank;
	}

	public String getName() {
		return this.name;
	}

	public int getIcon() {
		return this.icon;
	}

}
