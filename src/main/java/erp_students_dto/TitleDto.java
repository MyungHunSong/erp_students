package erp_students_dto;

public class TitleDto {

	private int tNo;
	private String tName;
	
	public TitleDto() {
		
	}
	
	public TitleDto(int tNo) {
		this.tNo = tNo;
	}

	public TitleDto(int tNo, String tName) {
		this.tNo = tNo;
		this.tName = tName;
	}
	public int gettNo() {
		return tNo;
	}
	public void settNo(int tNo) {
		this.tNo = tNo;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	@Override
	public String toString() {
		return String.format("직책번호 = %s, 직책명 = %s", tNo, tName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TitleDto other = (TitleDto) obj;
		if (tNo != other.tNo)
			return false;
		return true;
	}
	
	
	
}
