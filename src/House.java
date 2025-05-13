
public class House {
    int houseNo;
    String ownerName;
    int roomCount;
    Room[] room=new Room[10];
    public House() {
		// TODO Auto-generated constructor stub
	}
	public House(int houseNo, String ownerName) {
		super();
		this.houseNo = houseNo;
		this.ownerName = ownerName;
		this.roomCount=0;
		//this.room = room;
	}
	/**
	 * @return the houseNo
	 */
	/**
	 * @return the houseNo
	 */
	public int getHouseNo() {
		return houseNo;
	}
	/**
	 * @param houseNo the houseNo to set
	 */
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}
	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	/**
	 * @return the room
	 */
	public Room[] getRoom() {
		return room;
	}
	/**
	 * @param room the room to set
	 */
	public void setRoom(Room[] room) {
		this.room = room;
	}
	/**
	 * @return the roomCount
	 */
	public int getRoomCount() {
		return roomCount;
	}
	/**
	 * @param roomCount the roomCount to set
	 * @return 
	 */
	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}
	
		
}
