import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class CommunityChestCards implements Cards {
	   private int order2=0; //for card orders
	
	@Override
	public void selectCard(Player thePlayer, Player rival, Banker banker,FileWriter output) throws IOException, BankruptException {
			
			
			LinkedList<String> ComChestCards=PropertyAndCardReader.CommunityChestList;
			String card=ComChestCards.get(order2);
			String processDraft=thePlayer.getName()+" draw Community Chest"; //writing procesiing part step by step
			
			switch(card) {
			case "Advance to Go (Collect $200)":
				thePlayer.setCurrentSquare(0,banker);
				processDraft+="-advance to go ";
				break;
			case "Bank error in your favor - collect $75":
				processDraft+="-Bank error in your favor - collect $75 ";
				banker.payMoney(75,thePlayer);
				break;
			case "Doctor's fees - Pay $50":
				processDraft+="-Doctor's fees - Pay $50 ";
				thePlayer.payMoney(50, banker);
				break;
			case "It is your birthday Collect $10 from each player":
				processDraft+="-It is your birthday Collect $10 from each player ";
				rival.payMoney(10, thePlayer);
				break;
			case "Grand Opera Night - collect $50 from every player for opening night seats":
				processDraft+="-Grand Opera Night - collect $50 from every player for opening night seats ";
				rival.payMoney(10, thePlayer);
				break;
			case "Income Tax refund - collect $20":
				processDraft+="-Income Tax refund - collect $20 ";
				banker.payMoney(20,thePlayer);
				break;
			case "Life Insurance Matures - collect $100":
				processDraft+="-Life Insurance Matures - collect $100";
				banker.payMoney(100,thePlayer);
				break;
			case "Pay Hospital Fees of $100":
				processDraft+="-Pay Hospital Fees of $100";
				thePlayer.payMoney(100, banker);
				break;
			case "Pay School Fees of $50":
				processDraft+="-Pay School Fees of $50";
				thePlayer.payMoney(50, banker);
				break;
			case "You inherit $100":
				processDraft+="-You inherit $100";
				banker.payMoney(100,thePlayer);
				break;
			case "From sale of stock you get $50":
				processDraft+="-From sale of stock you get $50";
				banker.payMoney(50,thePlayer);
				break;
			
			
			}
			if(thePlayer.getName().equals("Player 1")) {
				output.write(thePlayer.getCurrentSquare().getId()+"\t"+thePlayer.getMoney()+
					"\t"+rival.getMoney()+"\t"+processDraft);}
			else if(thePlayer.getName().equals("Player 2")) {
				output.write(thePlayer.getCurrentSquare().getId()+"\t"+rival.getMoney()+
					"\t"+thePlayer.getMoney()+"\t"+processDraft);}
		

			if (order2 != (ComChestCards.size()-1)){
				order2++;  //for getting next card
				}
			else{
				order2=0; //for getting first card after last card
				}
			
		
	}

}
