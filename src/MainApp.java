import java.util.Scanner;

public class MainApp {
	static House h1=new House();

//static int ctrd=0;
static Scanner sc=new Scanner(System.in);

public static void main(String[] args) {
	// TODO Auto-generated method stub
	automation();
}

public static void automation()
{
	System.out.println("Enter House no");
	int hno=sc.nextInt();	
	System.out.println("Enter House Owner name");
	String hname=sc.next();
	
	h1.setHouseNo(hno);
	h1.setOwnerName(hname);
	
    int ch;
    do
    {
    	System.out.println("1.add Room \n2.add Device \n3.Access Device\n4.dispay\n0. Exit");
    	System.out.println("Enter your choice:");
    	ch=sc.nextInt();
    	switch(ch)
    	{
    		case 1:
    		{
    			addRoom();
    			
    		}
    		break;
    		case 2:
    		{
    			addDevice();
    		}
    		break;
    		case 3:
    		{
    			accessDevice();	
    		}
    		break;
    		case 4:
    		{
    			display();
    		}
    		break;
    	}
    	
    }while(ch!=0);
	}
public static void addRoom()
{
	int ch1;
	do
	{
		System.out.println("|  1     |        Kitchen                   |");
		System.out.println("|  2     |        Living area               |");
		System.out.println("|  3     |        Dining area               |");
		System.out.println("|  4     |        Bedroom                   |");
		System.out.println("|  5     |        WashRoom                  |");
		System.out.println("|  6     |        Corridors                 |");
		System.out.println("|  0     |        Exit                      |");
		System.out.println("Enter your choice:");
		ch1=sc.nextInt();
		switch(ch1)
		{
		case 1:
		{
			//Room r1=new Room("Kitchen");
			//System.out.println(ctrroom);
			h1.room[h1.getRoomCount()]=new Room("Kitchen");
			h1.setRoomCount(h1.getRoomCount()+1);
			System.out.println("room created successfully");
			break;
		}
		case 2:
		{
			Room r1=new Room("Living");
			h1.room[h1.getRoomCount()]=r1;
			h1.setRoomCount(h1.getRoomCount()+1);
			System.out.println("room created successfully");
			break;
		}
		case 3:
		{
			Room r1=new Room("Dining");
			h1.rooms[ctrroom++]=r1;
			System.out.println("room created successfully");

			//++ctrroom;
			break;
		}
		case 4:
		{
			Room r1=new Room("Bedroom");
			h1.rooms[ctrroom++]=r1;
			System.out.println("room created successfully");

			//++ctrroom;
			break;
		}
		case 5:
		{
			Room r1=new Room("WashRoom");
			h1.rooms[ctrroom++]=r1;
			System.out.println("room created successfully");

			//++ctrroom;
			break;
		}
		case 6:
		{
			Room r1=new Room("Corridors");
			h1.rooms[ctrroom++]=r1;
			System.out.println("room created successfully");

			//++ctrroom;
			break;
		}
	}
		
	}while(ch1!=0);
}


//////////////////////////////////////////////
public static void addDevice()
{
	
	int ch1;
	System.out.println("enter room name");
	String rm=sc.next();
	do
	{
		
		int i;
		for(i=0;i<ctrroom;i++)
		{  
		   if(h1.rooms[i].roomType.equalsIgnoreCase(rm))
		   {
			   break;
		   }
		}
		System.out.println("1.Lights\n2.Ac\n3.Tv\n4.Oven\n0.Exit");
		System.out.println("Enter your choice:");
    	ch1=sc.nextInt();
    	switch(ch1)
    	{
    	case 1:
    	{
    		System.out.println("Enter device id");
			int id=sc.nextInt();
			h1.rooms[i].devices[h1.rooms[i].getCtrd()]=new  Devices(id,"Electronic Device","Light");
			//h1.rooms[i].setCtrd(h1.rooms[i].getCtrd()+1);
			System.out.println("Device add successfully");
    	}
    	break;
    	case 2:
    	{
    		System.out.println("Enter device id");
			int id=sc.nextInt();
			h1.rooms[i].devices[h1.rooms[i].getCtrd()]=new  Devices(id,"Electronic Device","AirConditioners");
			//h1.rooms[i].setCtrd(h1.rooms[i].getCtrd()+1);
			System.out.println("Device add successfully");
    	}
    	break;
    	case 3:
    	{
    		System.out.println("Enter device id");
			int id=sc.nextInt();
			h1.rooms[i].devices[h1.rooms[i].getCtrd()]=new  Devices(id,"Electronic Device","Tv");
			//h1.rooms[i].setCtrd(h1.rooms[i].getCtrd()+1);
			System.out.println("Device add successfully");
    	}
    	case 4:
    	{
    		System.out.println("Enter device id");
			int id=sc.nextInt();
			h1.rooms[i].devices[h1.rooms[i].getCtrd()]=new  Devices(id,"Electronic Device","Oven");
			//h1.rooms[i].setCtrd(h1.rooms[i].getCtrd()+1);
			System.out.println("Device add successfully");
    	}
    	break;	
    	}
	}while(ch1!=0);

}
public static void accessDevice2()
{  
	//this cn be a funtion named selectRoomToAccess
	System.out.println("Please Select The Room:");
	//int indexValue=0;
	int choice=0;
	for(int i=0;i<h1.roomCount;i++)   // change roomcount variable here
	{ int srNO=i+1
		System.out.println(srNo+" ) "+h1.room[i].getName());
	}
	System.out.println("-------------------------------");
	System.out.println("Please Enter Your Choice: ");
	choice=sc.nextInt();//choice accepted
	int roomIndex=choice-1;    //the rooms index value is srNO-1;
	// to fetch the selected room, just access room at 'roomIndex' position.
	
	// now we have to access the device of room at ' roomIndex Position'
	//selectRoomToAccess ends Here
	///this can be a separate function calle as Accessgadgets
	System.out.println("Please Select the Device :");
   //loop to fetch the Names Of THe devices in the room At 'roomIndex'
	for(int j=0;j<h1.room[roomIndex].getDeviceCount();j++)
	{
		int srNo=0;
		System.out.println(srNo+" ) "+h1.room[roomIndex].devices[j].getName());
	}
	System.out.println("-------------------------------");
	System.out.println("Please Enter Your Choice: ");
	int devChoice=sc.nextInt();    //srNo of device Accepred here , thw device is present at srNo-1 index
    int deviceIndex=devChoice-1;
    //now the device is selected
    //roomIndex represents the index at which we have selected the roo in array of rooms
    //deviceIndex represent the device we are Going to control
    //no lets see how can we access methods of device
    //firstly we have to poi nt towards the device stored in the index and then we can show methods
	
	
	
}

public static void accessDevice()
{
	int ch1; 
	System.out.println("enter room name");
	String rm=sc.next();
	int i;
	i=search(rm);
	if(i==-1)
	{
		System.out.println("Room not found");
	}
	else
	{
		System.out.println("Enter device name");
		String dname=sc.next();
		
		do
		{
		
		System.out.println("1.Device on\n2.Device off\n3.check status\n4.check time\n0.exit");
		System.out.println("Enter your choice:");
    	ch1=sc.nextInt();
    	switch(ch1)
    	{
    	case 1:
    	{
    		//h1.rooms[i].devices[h1.rooms[i].getCtrd()]=new  Devices(id,"Electronic Device","Oven");
    		int j;
    		for(j=0;j<h1.rooms[i].getCtrd();j++)
			{
			   if(h1.rooms[i].devices[j].name.equalsIgnoreCase(dname))
			   {
				   break;
			   }
			}
    		System.out.println("light on j="+j);
    		//h1.rooms[i].Devices[j]
    		if(h1.rooms[i].devices[j] instanceof Lights)
    		{
    			Lights l1=(Lights)h1.rooms[i].devices[j];
    			l1.turnOn();
    			
    		}
    		else if(h1.rooms[i].devices[j] instanceof AirConditioners)
	    	{
	    			//Device d1=new Lights();
    			AirConditioners l1=(AirConditioners)h1.rooms[i].devices[j];
	    			l1.turnOn();
	    	}
    			
    		}
    	case 2:
    	{
    		int j;
    		for(j=0;j<h1.rooms[i].getCtrd();j++)
			{
			   if(h1.rooms[i].devices[j].name.equalsIgnoreCase(dname))
			   {
				   break;
			   }
			}
    		//h1.rooms[i].Devices[j]
    		if(h1.rooms[i].devices[j] instanceof Lights)
    		{
    			//Device d1=new Lights();
    			Lights l1=(Lights)h1.rooms[i].devices[j];
    			l1.turnOff();
    			
    		}
    		else if(h1.rooms[i].devices[j] instanceof AirConditioners)
	    	{
	    			//Device d1=new Lights();
    			AirConditioners l1=(AirConditioners)h1.rooms[i].devices[j];
	    			l1.turnOff();
	    	}
    		
    			
    		}
    	}
   }while(ch1!=0);

	}
	
}
public static void display()
{
	int ch1;
//	do
//	{
		System.out.println("enter room name");
		String rm=sc.next();
		int i;
		for(i=0;i<ctrroom;i++)
		{  
			
		   if(h1.rooms[i].roomType.equalsIgnoreCase(rm))
		   {
			   break;
		   }
		}
		
		for(int k=0;k<ctrd;k++)
		{
			System.out.println(h1.rooms[i].Devices[k]);
		}
	//}while(ch1!=0);
}
public static int search(String rm)
{
	for(int i=0;i<ctrroom;i++)
	{  
		
	   if(h1.rooms[i].roomType.equalsIgnoreCase(rm))
	   {
		   return i;
	   }
	}
	return -1;
}

}
