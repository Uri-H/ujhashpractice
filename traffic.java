import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.*; 
import java.util.*; 
import java.util.ArrayList;
import java.lang.Math; 

public class traffic {
	public static void main(String[] args) {
		int count = 0;
		int max = 0;
		int streetsAndCars;
		int streets;
		int cars;
		String[][] streetsData= new String[0][0];
		String[][] carsData= new String[0][0];
	    try {
	      File myObj = new File("f.txt");
	      Scanner myReader = new Scanner(myObj);
	      String firstLine = myReader.nextLine();
	      //int pizzas = Character.getNumericValue(firstLine.charAt(0));
	      String[] lineOne = firstLine.split("\\s+");
	      int[] firstOneLine = new int[5];
	      for(int i = 0; i<5;i++) {
	    	  firstOneLine[i] = Integer.parseInt(lineOne[i]);
	    	  ////System.out.println(firstOneLine[i]);
	      }
	      ////System.out.println(max);
	      streetsAndCars = firstOneLine[2] + firstOneLine[3];
	      streets = firstOneLine[2];
	      cars = firstOneLine[3];
	      
	      streetsData = new String[streets][4];
	      carsData = new String[cars][streets];
	      while (myReader.hasNextLine()&&count<streets) {
	        String data = myReader.nextLine();
	        String[] splitData = data.split("\\s+");
	        //for(int i=0;i<streetsAndCars;i++) {
	        for(int j=0;j<splitData.length;j++) {
	        	streetsData[count][j] = splitData[j];	
	        //}
	      }
	        count++;
	      }
	      count=0;
	      while (myReader.hasNextLine()&&count<cars) {
		        String data = myReader.nextLine();
		        String[] splitData = data.split("\\s+");
		        //for(int i=0;i<streetsAndCars;i++) {
		        for(int j=0;j<splitData.length;j++) {
		        	carsData[count][j] = splitData[j];	
		        //}
		      }
		        count++;
		      }
	      myReader.close();
	      System.out.println(firstOneLine[1]);
	      HashMap<Integer, ArrayList<String>> v = new HashMap<Integer, ArrayList<String>>(firstOneLine[1]);
	      for(int i=0;i<streets;i++) {
		        //for(int j=0;j<streets;j++) {
	    	  		//if(v.get(Integer.parseInt(streetsData[i][1]))==null)
	    	  		v.put(i,new ArrayList<String>());
		        //}
	      }
	      HashMap<String, Integer> q = new HashMap<String, Integer>(firstOneLine[2]);
	      for(int i=0;i<streets;i++) {
		        //for(int j=0;j<streets;j++) {
	    	  		//if(v.get(Integer.parseInt(streetsData[i][1]))==null)
	    	  		q.put(streetsData[i][2],0);
		        //}
	      }//this was started during the competition but was only completed after
	      for(int i=0;i<cars;i++) {
	    	  for(int j=1;j<(Integer.parseInt(carsData[i][0]))+1;j++) {
	    	  q.put(carsData[i][j],q.get(carsData[i][j])+1);
	    	  //System.out.println(q.get(carsData[i][j]));
	      }
	      }
	      for(int i=0;i<streets;i++) {
	    	  
	      v.get(Integer.parseInt(streetsData[i][1])).add(streetsData[i][2]);
	      //System.out.println("*" + Integer.parseInt(streetsData[i][1]) + v.get(Integer.parseInt(streetsData[i][1])) + "*");
	      }
	      int time;
	    		  try {
	    			  
	    			  FileWriter myWriter = new FileWriter("fAnswer.txt");
	    			  myWriter.write(firstOneLine[1]+ "\n");
	    			  for(int i=0;i<streets;i++) {
	    		    	  if(v.get(i).size()!=0) {	    	          
	    	          myWriter.write(i + "\n" + v.get(i).size() + "\n");
	    	          for(int j=0;j<v.get(i).size();j++) {
	    	        	  if(q.get(v.get(i).get(j))>2){
	    	        		  time = q.get(v.get(i).get(j));
	    	          myWriter.write(v.get(i).get(j) + " " + (int)(Math.log(time) / Math.log(2)) + "\n");
	    	        	  }//writes the street name and uses the log of the number of times it is mentioned as the seconds value
	    	        	  //my original solution from the competition just used a value of 1 which is a really good solution but 
	    	        	  //this strategy is much better for the last data set and netted us almost 600k more points for that data set alone
	    	          else myWriter.write(v.get(i).get(j) + " " + "1" + "\n");
	    	          }
	    		    	  } 
	    			  }
	    			  myWriter.close();
	    	          
	    	          //System.out.println("Successfully wrote to the file.");
	    	        } catch (IOException e) {
	    	          System.out.println("An error occurred.");
	    	          e.printStackTrace();
	    	        }
	      
	      
	      for(int i=0;i<cars;i++) {
		        for(int j=0;j<streets;j++) {
		        	//System.out.print(carsData[i][j]);	
		        }
		        //System.out.print("\n");
		        
	      }
     
	}
	    
	    
	    
	    catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
}
}