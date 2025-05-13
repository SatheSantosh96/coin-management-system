
import java.time.LocalDate;
public class Coin {
	int id;
    String country;
    String denomination;
    int yearOfMinting;
    double currentValue;
    LocalDate acquiredDate;
	public Coin() {
		// TODO Auto-generated constructor stub
	}
	public Coin(int id ,String country, String denomination, int yearOfMinting, double currentValue,
			String acquiredDate) {
	
		this.id = id;
		this.country = country;
		this.denomination = denomination;
		this.yearOfMinting = yearOfMinting;
		this.currentValue = currentValue;
		this.acquiredDate = LocalDate.parse(acquiredDate);   //this function converts string into localdate
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public int getYearOfMinting() {
		return yearOfMinting;
	}
	public void setYearOfMinting(int yearOfMinting) {
		this.yearOfMinting = yearOfMinting;
	}
	public double getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}
	public LocalDate getAcquiredDate() {
		return acquiredDate;
	}
	public void setAcquiredDate(LocalDate acquiredDate) {
		this.acquiredDate = acquiredDate;
	}
//	@Override
//	public String toString() {
//		
//	   
//	    
//	    String a=  "\n+----------------------------------------------------------------------------------------+";
//	    String b="\n|  Coin Id  | Country   | Denomination  | Year Of Minting | Current Value | Acquired Date|";
//	    String c="\n|"+this.getId()+" | "+this.getCountry()+" | "+this.getDenomination()+" | "+this.getYearOfMinting()+" |"+this.getCurrentValue()+" | "this.getAquiredDate()+"|";
//	    String d="\n+-----------------------------------------------------------------------------------------+";
//	    String result= a+b+c+d;
//		return result;
	@Override
	public String toString() {
		return String.format("-----------------------------------------------------------------------------------------------------------------------------------------\nId : %-5d Country: %-15s Denomination: %-10s Year of Minting:  %-5d  CurrentValue %-6.2f  Acquired Date: %-8s",
                id, country, denomination, yearOfMinting, currentValue, acquiredDate.toString());
		//return "id=" + id + ", country=" + country + ", denomination=" + denomination + ", yearOfMinting="
			//	+ yearOfMinting + ", currentValue=" + currentValue + ", acquiredDate=" + acquiredDate;
	}
	
	
}
