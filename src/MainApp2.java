import java.util.Scanner;

public class MainApp2{

	public static void main2(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hi Creating Lights Object");
		Lights ob=new Lights(1,"Electronic Device","Light1");
		System.out.println("Lights Object Created");
		System.out.println("Details Are:");
System.out.println("ID:"+ob.getId());
System.out.println("Type:"+ob.getType());
System.out.println("Name:"+ob.getName());
System.out.println("Stutus:"+ob.getStatus());
System.out.println("Time:"+ob.getTime());
ob.on();
System.out.println("Now using Setting ON,The Staus is:"+ob.getStatus());
System.out.println("TimeCalculation:");
System.out.println("Again Switching ON");
ob.on();
ob.TimeCalc();
System.out.println("Press Any number");
Scanner sc=new  Scanner(System.in);
System.out.println("Now Switching Off");
ob.off();;
System.out.println("Now using Setting off,The Staus is:"+ob.getStatus());
System.out.println("Time:"+ob.getTime());
System.out.println("TimeCalculation:");
ob.TimeCalc();

		
		
	}

}
