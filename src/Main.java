import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
	
		PropertyAndCardReader reader=new PropertyAndCardReader(); 
		reader.propertyJsonReader(); 
		reader.listJsonReader();
		reader.otherSquares();
		
		FileWriter output= new FileWriter("output.txt",true);
		
		Player player1 = new Player("Player 1",15000);
		Player player2 = new Player("Player 2",15000);
		Banker banker = new Banker("Banker",100000);
		
		CommunityChestCards communityCard=new CommunityChestCards();
		ChanceCards chanceCard=new ChanceCards(communityCard);
		
		CommandReader commands=new CommandReader(player1,player2,banker,chanceCard,communityCard,output);
		commands.reader(args[0]);
		
		output.close();
		

}}
