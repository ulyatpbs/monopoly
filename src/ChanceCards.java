import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class ChanceCards implements Cards {
	private int order=0;  //for the order of cards
	private CommunityChestCards communityCards;
	
	public ChanceCards(CommunityChestCards communityCards) {
		this.communityCards=communityCards;
				
	}
	

	@Override
	public void selectCard(Player thePlayer, Player rival, Banker banker,FileWriter output) throws IOException, BankruptException {
		LinkedList<Square> SquareList=PropertyAndCardReader.Squares;
		LinkedList<String> ChanceCards=PropertyAndCardReader.ChanceList;
		String card=ChanceCards.get(order);
		String processDraft=thePlayer.getName();   //for writing processing part step by step
		if(card.equals("Go back 3 spaces"))
		 {	
			processDraft+=" draw Go back 3 spaces "+thePlayer.getName();
			 
			
			int destination= (thePlayer.getCurrentSquareIndex()-3);
			Square theSquare=SquareList.get(destination);
			thePlayer.jump(theSquare);
			
			
			if((theSquare.getId()==5)) {
				thePlayer.payMoney(100, banker);
				output.write(thePlayer.getCurrentSquare().getId()+"\t"+thePlayer.getMoney()+
						"\t"+rival.getMoney()+"\t"+processDraft+" paid Tax");
			}
			
			else if((SquareList.get(destination).getId()==20))
			{
				if (rival.equals((((Property) theSquare).getOwner()))) {
					thePlayer.rent((Property)theSquare, rival);
					processDraft+=(" paid rent for "+theSquare.getName());
					
				}
				else if(((Property) theSquare).getOwner()==null){
					if(thePlayer.getMoney()>=((Property) theSquare).getCost()) {
						thePlayer.own(((Property) theSquare), banker);
						processDraft+=(" bought "+theSquare.getName());
					}}
				else if(thePlayer.equals(((Property) theSquare).getOwner())){
					processDraft+=(" has "+theSquare.getName());
					
				}
				
					if(thePlayer.getName().equals("Player 1")) {
				output.write(thePlayer.getCurrentSquare().getId()+"\t"+thePlayer.getMoney()+
					"\t"+rival.getMoney()+"\t"+processDraft);}
					
					else if(thePlayer.getName().equals("Player 2")) {
						
						output.write(thePlayer.getCurrentSquare().getId()+"\t"+rival.getMoney()+
							"\t"+thePlayer.getMoney()+"\t"+processDraft);}
					
			}
			else if (SquareList.get(destination).getId()==34) {
				communityCards.selectCard(thePlayer, rival, banker,output);
				
			}
		 }
		else {
			
		switch(card) {
		case "Advance to Go (Collect $200)":
			thePlayer.setCurrentSquare(0,banker);
			processDraft+=" draw Advance to Go (Collect $200)";
			break;
		case "Advance to Leicester Square":
			thePlayer.setCurrentSquare(26,banker);
			processDraft+=" draw Advance to Leicester Square";
			if (rival.equals((((Property) thePlayer.getCurrentSquare()).getOwner()))) {	
				thePlayer.rent((Property) thePlayer.getCurrentSquare(), rival);
				processDraft+=(thePlayer.getName()+" paid rent for "+thePlayer.getCurrentSquare().getName());
			}
			else if((((Property) thePlayer.getCurrentSquare()).getOwner()==null)){
				if(thePlayer.getMoney()>=((Property) thePlayer.getCurrentSquare()).getCost()) {
					thePlayer.own(((Property) thePlayer.getCurrentSquare()), banker);
					processDraft+=(thePlayer.getName()+" bought "+thePlayer.getCurrentSquare().getName());
				}}
			else if(thePlayer.equals(((Property) thePlayer.getCurrentSquare()).getOwner())){
				processDraft+=(thePlayer.getName()+" has "+thePlayer.getCurrentSquare().getName());
				
			}
			break;
		
		case "Pay poor tax of $15":
			processDraft+=(" draw Pay poor tax of $15 "+thePlayer.getName()+" paid Tax");
			thePlayer.payMoney(15, banker);
			break;
		case "Your building loan matures - collect $150":
			banker.payMoney(150, thePlayer);
			processDraft+=(" Your building loan matures - collect $150 "+thePlayer.getName()+" collected $150");
			break;
		case "You have won a crossword competition - collect $100 ":
			banker.payMoney(100, thePlayer);
			processDraft+=(" You have won a crossword competition - collect $100 "+thePlayer.getName()+" collected $100");
			break;
			}
	
		if(thePlayer.getName().equals("Player 1")) {
			output.write(thePlayer.getCurrentSquare().getId()+"\t"+thePlayer.getMoney()+
				"\t"+rival.getMoney()+"\t"+processDraft);}
		else if(thePlayer.getName().equals("Player 2")) {
			output.write(thePlayer.getCurrentSquare().getId()+"\t"+rival.getMoney()+
				"\t"+thePlayer.getMoney()+"\t"+processDraft);}
	
		}
	
		
		if (order != ((ChanceCards.size()-1))){
		order++;                                  //for getting next card
		}
		else if(order == ((ChanceCards.size()-1))){    //continue with first card after last card
			order=0;
		}
		
	

}



	}
	
		
	


