
public class Room {
	String roomType;
	int deviceCount;
	Device[] devices=new Device[10];
	
	public Room( String roomType) 
	{
		this.roomType = roomType;
		this.deviceCount=0;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(int deviceCount) {
		this.deviceCount = deviceCount;
	}

	public Device[] getDevices() {
		return devices;
	}

	public void setDevices(Device[] devices) {
		this.devices = devices;
	}
	
		
	
	
	
}
