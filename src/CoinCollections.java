import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CoinCollections {
	//creating arrayList of Coins
	int count=0;
	List<Coin> coinList= new ArrayList<Coin>();
	List<String> data= new ArrayList<String>();
	Scanner sc=new Scanner(System.in);

	public void coinMenu()
	{
		
		fetchFromDataBase();  //IMPLICITE CALL TO A FUNCTION TO LOAD DATA FROM DATABASE INTO SYSTEM
		
		
		      System.out.println("+------------------------------------------------------+");
		      System.out.println("|              WELCOME TO COIN COLLECTIONS             |");
		      System.out.println("+------------------------------------------------------+");
		      System.out.println("********************************************************");
		  int choice=-1;
		  do {//Menu For Operations - add,bulkupload , search,delete, persist...
			  System.out.println("+------------------------------------------------------+"); 
			  System.out.println("|                         MENU                         |");
			  System.out.println("+------+-----------------------------------------------+");
			  System.out.println("|  1   |    INSERT COINS                               |");
			  System.out.println("|  2   |    INSERT  BULK DATA FROM FILE                |");
			  System.out.println("|  3   |    SEARCH COIN BASED ON COMMON ATTRIBUTES     |");
			  System.out.println("|  4   |    SEARCH A SPECIFIC COIN                     |");
			  System.out.println("|  5   |    UPDATE COIN DETAILS                        |");
			  System.out.println("|  6   |    SAVE CHANGES                               |");
			  System.out.println("|  7   |    DISPLAY ALL COINS IN COLLECTION            |");
			  System.out.println("|  8   |    SORT COINS BASED ON CURRENT VALUE          |");
			  System.out.println("|  9   |    DELETE COIN                                |");
			  System.out.println("|  0   |    EXIT                                       |");
			  System.out.println("+------+-----------------------------------------------+");
			  System.out.println("Please Enter Your Choice:");
			  choice=sc.nextInt();
			  switch(choice)
			  {
			  case 1:
			  {
				  insertCoins();  
			  }
			  break;
			  case 2:
			  {
				 bulkUpload();
			  }
			  break;
			  case 3:
			  {
				 searchCoins();
			  }
			  break;
			  case 4:
			  {
				 specifiedSearch(); 
			  }
			  break;
			  case 5:
			  {
				  updateCoins();
			  }
			  break;
			  case 6:
			  {
				  saveData();
			  }
			  break;
			  case 7:
			  {
				  displayAll(); 
			  }
			  break;
			  case 8:
			  {
				  sortByValue();
			  }
			  break;
			  case 9:
			  {
				  deleteCoin();
			  }
			  break;
			  
			  default:
			  {   
				  System.out.println("OOPS.........Invalid Choice......please Your Choice again:");
				  
			  }
			  break;
			  case 0:
			  {//here we will give call to save data implicitely so that data will be added to database
				  System.out.println("Saving Data Implicitely before exit...");
				  saveData();
				  System.out.println("Data Saved Successfully...............");
			  }
			  
			  }//end of switch cases
			   
			  }while(choice!=0);
		 
	}//End Of COINMENU
	

	 private void deleteCoin() {
		// TODO Auto-generated method stub
		
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|                 DELETE  COIN                         |");
		  System.out.println("+------------------------------------------------------+");
		  System.out.println("Enter Coin Id You Want to Delete:");
		  int coinIdToDelete=sc.nextInt();
		  boolean found=false;
		  int indexOfCoin=-1;
			for(int i=0;i<coinList.size();i++)
			{
				if(coinList.get(i).getId()==coinIdToDelete)
				{    found=true;
				    indexOfCoin=i;
				     System.out.println("Coin Found");
				     System.out.println(coinList.get(i).toString());
				     break;
				    
				}
				
			}
			System.out.println("Do You Really Want to delete This Coin:?  1)YES | 2) NO):");
			int yesno=sc.nextInt();
			if(yesno==1)
			{
				data.set(indexOfCoin, "DEL");
				System.out.println("DELETION SUCCESSFULL...CHANGES WILL BE SAVED TO DATABASE AFTER EXIT");
			}
			else if(yesno==2)
			{
				System.out.println("DELETION ABORTED");
			}
			
	}


	private void sortByValue() {
		// TODO Auto-generated method stub
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|                     SORTED LIST                      |");
		  System.out.println("+------------------------------------------------------+");
		 System.out.println("THE SORTED LIST OF COINS ACCORDING TO CURRENT VALUE:");
		 
		 List<Coin> CoinList1=new ArrayList();
			for(Coin x:coinList)
			{
				CoinList1.add(x);
			}
			Collections.sort(CoinList1, new Comparator<Coin>()
					{

						public int compare(Coin o1, Coin o2) {
							// TODO Auto-generated method stub
							return Double.compare(o1.getCurrentValue(), o2.getCurrentValue());
						}
				
					});
			for(Coin x:CoinList1)
			{
				System.out.println(x.toString());
			}
			
		}
	


	private void fetchFromDataBase() {
		try (// TODO Auto-generated method stub
		Connection con = dbConnect()) {
			String query="select * from coins";
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery(query);
			
			while(result.next())
			{
				int id=result.getInt(1);
			    String country=result.getString(2);
			    String denomination=result.getString(3);
			    int yearOfMinting=result.getInt(4);
			    double currentValue=result.getDouble(5);
			    String acquiredDate=result.getDate(6).toString()	;//tostring method Formats a date in the date escape format yyyy-mm-dd
			
			    //now creating anonymous object of coin and adding to the database,simultaneously adding insert string in data list
			    coinList.add(new Coin(id,country,denomination,yearOfMinting,currentValue,acquiredDate));
		        data.add("UC");
		        
		       // System.out.println(coinList.get(0));
		        
			}
			System.out.println("Coins Added successfully from database");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Connection dbConnect() {
		// TODO Auto-generated method stub
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coincollections","root","firstbit");
			if(connection!=null)
			{
				System.out.println("Database Connection Successful..");
	            return connection;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Database Connection Problem");
			e.printStackTrace();
		}
				
				
				return null;
		
	
	}


	
	
	
	
   public void displayAll() {
        System.out.println("+---------+--------------+--------------+-----------------+---------------+---------------+");
        System.out.println("| COIN_ID | COUNTRY      | DENOMINATION | YEAR OF MINTING | CURRENT VALUE | ACQUIRED DATE |");
        System.out.println("+---------+--------------+--------------+-----------------+---------------+---------------+");
        for (int ind=0;ind<coinList.size();ind++) {
        	if(!data.get(ind).equalsIgnoreCase("DEL"))
        	{
                System.out.printf("|%8d | %-12s | %-12s | %15d | %13.2f | %-13s |\n",
                coinList.get(ind).getId(), coinList.get(ind).getCountry(), coinList.get(ind).getDenomination(),
                coinList.get(ind).getYearOfMinting(), coinList.get(ind).getCurrentValue(), coinList.get(ind).getAcquiredDate());
        	}
        }
        System.out.println("+---------+--------------+--------------+-----------------+---------------+---------------+");
    }
	

	 void saveData() {
		try (// TODO Auto-generated method stub
		Connection connection = dbConnect()) {
			int addrow=0,updaterow=0,delrow=0;
			for(int index=0;index<data.size();index++)
			{
				if(data.get(index).equalsIgnoreCase("INS"))
				{
					//CODE FOR INSERT OPERATION
					String query="insert into coins Values(?,?,?,?,?,?)";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setInt(1, coinList.get(index).getId() );
					pst.setString(2,coinList.get(index).getCountry());
					pst.setString(3, coinList.get(index).getDenomination());
					pst.setInt(4, coinList.get(index).getYearOfMinting());
					pst.setDouble(5, coinList.get(index).getCurrentValue());
					Date date = Date.valueOf(coinList.get(index).getAcquiredDate());
		            pst.setDate(6, date);
				    addrow=addrow+pst.executeUpdate();
					
				}
				else if(data.get(index).equalsIgnoreCase("UP"))
				{//Code For Update Operation
					String query="update coins set country=?,denomination=?,year_of_minting=?,current_value=?,acquired_date=? where coin_id=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,coinList.get(index).getCountry());
					pst.setString(2, coinList.get(index).getDenomination());
					pst.setInt(3, coinList.get(index).getYearOfMinting());
					pst.setDouble(4, coinList.get(index).getCurrentValue());
					Date date = Date.valueOf(coinList.get(index).getAcquiredDate());
		            pst.setDate(5, date);
		            pst.setInt(6, coinList.get(index).getId() );
		            updaterow=updaterow + pst.executeUpdate();
				}
				else if(data.get(index).equalsIgnoreCase("DEL"))
				{
					//CODE FOR DELETE operation
					String query="delete from coins where coin_id=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setInt(1, coinList.get(index).getId() );
				   delrow = delrow+pst.executeUpdate();

					
				}
						
			}
			System.out.println("Changes Saved to database");
			System.out.println(addrow + " rows Added successfully");
			System.out.println(updaterow + " rows Updated successfully");
			System.out.println(delrow + " rows Deleted successfully");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void updateCoins() {
		// TODO Auto-generated method stub
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|                 UPDATE COIN  DETAILS                 |");
		  System.out.println("+------------------------------------------------------+");
		  System.out.println("Enter Coin Id You Want to Update:");
		  int coinIdToUpdate=sc.nextInt();
		  boolean found=false;
		  int indexOfCoin=-1;
			for(int i=0;i<coinList.size();i++)
			{
				if(coinList.get(i).getId()==coinIdToUpdate)
				{    found=true;
				    indexOfCoin=i;
				     System.out.println(coinList.get(i).toString());
				     break;
				    
				}
			}
			update(indexOfCoin);
			if(!found)
				System.out.println("Sorry.. No Match Found...");
			
		
	}

	 void update(int indexOfCoin) {
		// TODO Auto-generated method stub
		  boolean updateStatus=false;
		  int updateChoice=-1;
		  do {
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|                 UPDATE DETAILS MENU                  |");
		  System.out.println("+------+-----------------------------------------------+");
		  System.out.println("|  1   |    UPDATE COUNTRY                             |");
		  System.out.println("|  2   |    UPDATE DENOMINATION                        |");
		  System.out.println("|  3   |    UPDATE YEAR OF MINTING                     |");
		  System.out.println("|  4   |    UPDATE CURRENT VALUE                       |");
		  System.out.println("|  5   |    UPDATE ACQUIRED DATE                       |");
		  System.out.println("|  0   |    EXIT                                       |");
		  System.out.println("+------+-----------------------------------------------+");
		  System.out.println("Enter Your Choice:");
		  updateChoice=sc.nextInt();
		  switch(updateChoice)
		  {
		  case 1:
		  {  sc.nextLine();
			 System.out.println("Enter Updated Country Name:"); 
			 String str=sc.nextLine();
			 coinList.get(indexOfCoin).setCountry(str);
			 if(data.get(indexOfCoin).equalsIgnoreCase("INS"))
					 {updateStatus=true;
				       }
			 else
			 {   updateStatus=true;
				 data.set(indexOfCoin, "UP");
			 }
		  }
		  break;
		  case 2:
		  {   sc.nextLine();
			  System.out.println("Enter Updated Denomination:");
			  String str=sc.next();
			  coinList.get(indexOfCoin).setDenomination(str);
				 if(data.get(indexOfCoin).equalsIgnoreCase("INS"))
						 {updateStatus=true;
					       }
				 else
				 {   updateStatus=true;
					 data.set(indexOfCoin, "UP");
				 }
			  
		  }
		  break;
		  case 3: 
		  {
			  System.out.println("Enter Updated Year Of Minting:");
			  int year=sc.nextInt();
			  coinList.get(indexOfCoin).setYearOfMinting(year);
				 if(data.get(indexOfCoin).equalsIgnoreCase("INS"))
						 {updateStatus=true;
					       }
				 else
				 {   updateStatus=true;
					 data.set(indexOfCoin, "UP");
				 }
		  }
		  break;
		  case 4:
		  {
			  System.out.println("Enter Updated Current Value:");
			  double value=sc.nextDouble();
			  coinList.get(indexOfCoin).setCurrentValue(value);
				 if(data.get(indexOfCoin).equalsIgnoreCase("INS"))
						 {updateStatus=true;
					       }
				 else
				 {   updateStatus=true;
					 data.set(indexOfCoin, "UP");
				 }
		  }
		  break;
		  case 5:
		  {
			  System.out.println("Enter Updated Acquired Date(yyyy-mm-df)");
			  String str=sc.next();
			  coinList.get(indexOfCoin).setAcquiredDate(LocalDate.parse(str));
				 if(data.get(indexOfCoin).equalsIgnoreCase("INS"))
						 {updateStatus=true;
					       }
				 else
				 {   updateStatus=true;
					 data.set(indexOfCoin, "UP");
				 }
		  }
		  break;
		  default:
		  {
			  System.out.println("Invalid Choice......");
		  }
		  break;
		  }//switch of updatechoice
		  }while(updateChoice!=0);
         if(updateStatus==true)
         {
        	 System.out.println("Data Updated Successfully");
        	 System.out.println("The Updated Values Are:");
        	 System.out.println(coinList.get(indexOfCoin).toString());
         }
         else
         {
        	 System.out.println("Update Operation Failed");
         }
		  
		
	}


	 void specifiedSearch() {
		// TODO Auto-generated method stub
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|                 SEARCH SPECIFIC COIN                 |");
		  System.out.println("+-------+----------------------------------------------+");
		  System.out.println("|   1   | BY COUNTRY + DENOMINATION                    |");
		  System.out.println("|   2   | BY COUNTRY + YEAROFMINTING                   |");
		  System.out.println("|   3   | BY COUNTRY + DENOMINATION + YEAR OF MINTING  |");
		  System.out.println("+-------+----------------------------------------------+");
		  System.out.println("Please Enter Your Choice:");
		  int searchChoice=sc.nextInt();
		  switch(searchChoice)
		  {
		  case 1:
		  {
		     searchByCountryDenomination();
		  }
		  break;
		  case 2:
		  {
			  searchByCountryYearOfMinting();
		  }
		  break;
			
		  case 3:
		  {
			  searchByCountryDenominationYearOfMinting();
		  }
		  
		  }//switch ends here
		  
		  
	}

	private void searchByCountryDenomination() {
		// TODO Auto-generated method stub
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|         SEARCH BY COUNTRY + DENOMINATION             |");
		  System.out.println("+------------------------------------------------------+");
		  boolean found=false;
		System.out.println("Enter Country :");
		String country=sc.next();
		System.out.println("Enter Denomination:");
		String denomination=sc.next();
		for(int i=0;i<coinList.size();i++)
		{
			if(coinList.get(i).getCountry().equalsIgnoreCase(country) && coinList.get(i).getDenomination().equalsIgnoreCase(denomination) )
			{   found=true;
				System.out.println(coinList.get(i).toString());
			}
		}
		if(!found)
			System.out.println("Sorry.. No Match Found...");
	}


	private void searchByCountryYearOfMinting() {
		// TODO Auto-generated method stub
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|       SEARCH BY COUNTRY + YEAR OF MINTING            |");
		  System.out.println("+------------------------------------------------------+");
		  boolean found=false;
		System.out.println("Enter Country Name:");
		String country=sc.next();
		System.out.println("Enter Minting year of coin:");
		int year=sc.nextInt();
		for(int i=0;i<coinList.size();i++)
		{
			if(coinList.get(i).getCountry().equalsIgnoreCase(country) && coinList.get(i).getYearOfMinting()==year )
			{   found=true;
				System.out.println(coinList.get(i).toString());
			}
		}
		if(!found)
			System.out.println("Sorry.. No Match Found...");
	}


	private void searchByCountryDenominationYearOfMinting() {
		// TODO Auto-generated method stub
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|  SEARCH BY COUNTRY + DENOMINATION + YEAR OF MINTING  |");
		  System.out.println("+------------------------------------------------------+");
		  boolean found=false;
		System.out.println("Enter Country Name");
		String country=sc.next();
		System.out.println("Enter Denomination:");
		String denomination=sc.next();
		System.out.println("Enter Minting year of coin");
		int year=sc.nextInt();
		for(int i=0;i<coinList.size();i++)
		{
			if(coinList.get(i).getCountry().equalsIgnoreCase(country) && coinList.get(i).getDenomination().equalsIgnoreCase(denomination) && coinList.get(i).getYearOfMinting()==year)
			{   found=true;
				System.out.println(coinList.get(i).toString());
			}
		}
		if(!found)
			System.out.println("Sorry.. No Match Found...");
	}


	private void searchCoins() {
		// TODO Auto-generated method stub
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|                     SEARCH COINS                     |");
		  System.out.println("+-------+----------------------------------------------+");
		  System.out.println("|   1   |     SEARCH BY COUNTRY                        |");
		  System.out.println("|   2   |     SEARCH BY YEAR OF MINTING                |");
		  System.out.println("|   3   |     SEARCH BY CURRENT VALUE                  |");
		  System.out.println("+-------+----------------------------------------------+");
		  System.out.println("Please Enter Your Choice:");
		  int searchChoice=sc.nextInt();
		  switch(searchChoice)
		  {
		  case 1:
		  {
		     searchByCountry();
		  }
		  break;
		  case 2:
		  {
			  searchByYearOfMinting();
		  }
		  break;
			
		  case 3:
		  {
			  searchBYCurrentValue();
		  }
		  
		  }//switch ends here
		  
		  }
		  
		
	private void searchBYCurrentValue() {
		// TODO Auto-generated method stub
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|             SEARCH BY CURRENT VALUE                  |");
		  System.out.println("+------------------------------------------------------+");
		sc.nextLine();
		
		System.out.println("Enter Current Value:");
		Double value=sc.nextDouble();
		boolean found=false;
		for(int i=0;i<coinList.size();i++)
		{
			if(coinList.get(i).getCurrentValue()==value)
			{    found=true;
				System.out.println(coinList.get(i).toString());
			}
		}
		if(!found)
			System.out.println("Sorry.. No Match Found...");
		
	}
		
	


	private void searchByYearOfMinting() {
		// TODO Auto-generated method stub
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|           SEARCH BY YEAR OF MINTING                  |");
		  System.out.println("+------------------------------------------------------+");
		System.out.println("Enter Year Of Minting:");
		boolean found=false;
		int year=sc.nextInt();
		for(int i=0;i<coinList.size();i++)
		{
			if(coinList.get(i).getYearOfMinting()==year)
			{    found=true;
				System.out.println(coinList.get(i));
			}
		}
		if(!found)
			System.out.println("Sorry.. No Match Found...");
		
	}


	private void searchByCountry() {
		// TODO Auto-generated method stub
		
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|               SEARCH BY COUNTRY NAME                 |");
		  System.out.println("+------------------------------------------------------+");
		  sc.nextLine();
		System.out.println("Enter Country Name:");
		String str=sc.nextLine();
		System.out.println("Searching.....");
		boolean found=false;
		for(int i=0;i<coinList.size();i++)
		{
			if(coinList.get(i).getCountry().equalsIgnoreCase(str))
			{ found=true;
				System.out.println(coinList.get(i));
			}
		}
		if(!found)
		System.out.println("Sorry..No Match Found...");
		


	}

	public void bulkUpload() {
		try (// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(new File("Coins.csv"))) {
			int count=0;
			while(scanner.hasNext())
			{
				String row=scanner.nextLine();
				String[] attributes=row.split(",");
				int id=Integer.parseInt(attributes[0]);
				   
			  //to prevent duplicate values
				Iterator<Coin> iterator = coinList.iterator();    //iterator
			    boolean coinExists = false;                       // to check status
			    while (iterator.hasNext()) {
			        Coin x = iterator.next();
			        if (x.getId() == id) {
			            System.out.println("Coin With Id:" + id + " is already present, so skipping this entry");
			            coinExists = true;
			            break;
			        }
			    }
			    
			    if (!coinExists) {
			        
			        String country=attributes[1];
				    String denomination=attributes[2];
				    int yearOfMinting=Integer.parseInt(attributes[3]);
				    double currentValue=Double.parseDouble(attributes[4]);
				    String acquiredDate=attributes[5].toString(); 
				    coinList.add(new Coin(id, country, denomination, yearOfMinting, currentValue, acquiredDate));
			        data.add("Ins");
			        count++;
			    }
				
				
			    
			}
			System.out.println("BULK UPLOAD SUCCESSFULL");
			System.out.println("\t"+count+ " Coins Added With Bulk Upload Method");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	 void insertCoins(){
		// TODO Auto-generated method stub
		  System.out.println("+------------------------------------------------------+"); 
		  System.out.println("|                   INSERT NEW COIN                    |");
		  System.out.println("+------------------------------------------------------+");
		  System.out.println("Enter Coin ID(Keep it Unique):");
		  int id=sc.nextInt();
		//to prevent duplicate values
			Iterator<Coin> iterator = coinList.iterator();    //iterator
		    boolean coinExists = false;                       // to check status
		    while (iterator.hasNext()) {
		        Coin x = iterator.next();
		        if (x.getId() == id) {
		            System.out.println("*****************************************\nCoin With Id:" + id + " already Exists...Cant Accept Further Values.\n");
		            coinExists = true;
		            break;
		        }
		    }
		    if (!coinExists) {
		    	
		    sc.nextLine();
          System.out.println("Enter Country:");
          String country=sc.nextLine();
          System.out.println("Enter Denomination:");
          String denomination=sc.nextLine();
          System.out.println("Enter Year Of Minting:");
          int yearOfMinting=sc.nextInt();
          System.out.println("Enter Current Value:");
          double currentValue=sc.nextDouble();
          System.out.println("Enter Aquired Date(yyyy-mm-dd)");
          String acquiredDate=sc.next();
         // acquiredDate=LocalDate.parse(dateEntry);
          
		coinList.add(new Coin(id,country,denomination,yearOfMinting,currentValue,acquiredDate));
        data.add("INS");
        System.out.println("Coin Added successfully");
       // System.out.println(coinList.get(0));
		    }
        
	}
	

	
	
	/*
	 */
	 
}
