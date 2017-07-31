package pojo;

public enum Suit implements Comparable<Suit>{
	Diamond ("D"),
	Heart ("H"),
	Club ("C"),
	Spade ("S");
	
	private String value;

	private Suit(String value) {
		this.value = value;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
