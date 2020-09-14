package clock;
import java.time.LocalDateTime;
public class Display{
	public static void main(String[] args){
		try{
			String fileName = "../data/clock.txt";
			FileUtility util = new FileUtility(fileName);
			LocalDateTime myObj = LocalDateTime.now();
    		util.write(myObj.toString());
		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
		
	}
}