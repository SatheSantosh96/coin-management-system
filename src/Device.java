import java.time.LocalTime;
public class Device {
	int id;
    String type;
    String name;// ac,cooler,tv 
    String status;   //on off
    LocalTime Time;
	public Device(int id, String type, String name) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.status = "OFF";
		Time =LocalTime.now();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the time
	 */
	public LocalTime getTime() {
		return Time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(LocalTime time) {
		Time = time;
	}
	
	
     
    
	
}
