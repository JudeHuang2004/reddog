package pojo;

public enum CardNum implements Comparable<CardNum>{
	_2(2), 
	_3(3),
	_4(4),
	_5(5),
	_6(6),
	_7(7),
	_8(8),
	_9(9),
	_10(10),
	_J(11),
	_Q(12),
	_K(13),
	_A(14);
	
	private Integer value;

	private CardNum(Integer value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}
	
}
