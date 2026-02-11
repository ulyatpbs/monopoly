import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class CommandReader {

		private Player player1 ;
		private Player player2 ;
		private Banker banker;
		private ChanceCards chanceCards;
		private CommunityChestCards communityCards;
		FileWriter output;
		private boolean catched=false;   //for looking up if program enters catch block or not
		
	public CommandReader(Player player1,Player player2, Banker banker,ChanceCards chanceCards,
			CommunityChestCards communityCards,FileWriter output) {
		this.player1=player1;
		this.player2=player2;	
		this.banker=banker;
		this.chanceCards=chanceCards;
		this.communityCards=communityCards;
		this.output=output;
		}

	
	public String show(Player playr1,Player playr2,Banker bankr) {
		String winner;
		if(playr1.getMoney()>=playr2.getMoney()) {
			winner=playr1.getName();
		}
		else {
			winner=playr2.getName();
			
		}
		String showPrint="--------------------------------------------------"
				+ "---------------------------------------------------------\n"+
				"Player 1\t"+playr1.getMoney()+"\thave: "+playr1.properties()+"\n"+
				"Player 2\t"+playr2.getMoney()+"\thave: "+playr2.properties()+"\n"+
				"Banker\t"+bankr.getMoney()+"\nWinner "+ winner+
				"\n--------------------------------------------------"
				+ "---------------------------------------------------------\n"
				;
				
		return showPrint;		
		
	} 

	public void reader(String file) throws IOException {
		
	try(Scanner commands=new Scanner(new FileReader(file))
			){
		while(commands.hasNextLine()) {
			String cline=commands.nextLine();
			
			
			if(cline.startsWith("show()")) {
				
				output.write(show(this.player1,this.player2,this.banker));
				
				}
			
			
			else {
			String[] csplitted = cline.split(";");
			int dice=Integer.parseInt(csplitted[1]);
			String dicePlayer= csplitted[0];
			
			try {
				
			if(csplitted[0].equals("Player 1" )&& player1.getWaitfor()==0) {
				
				player1.setCurrentDice(dice);
				int destinationIndex=player1.getCurrentSquareIndex()+dice;
				if(destinationIndex>39) {
					
					destinationIndex=destinationIndex-40;
				}
				player1.setCurrentSquare(destinationIndex, banker);
				Square newSquare=player1.getCurrentSquare();
				if ( newSquare instanceof Property)
						{
					if (player2.equals(((Property) newSquare).getOwner())) {
						player1.rent((Property)newSquare, player2);
						player1.setProcess("Player 1 paid rent for "+newSquare.getName());
					}
					else if(((Property) newSquare).getOwner()==null){
							player1.own(((Property) newSquare), banker);
							
							player1.setProcess("Player 1 bought "+newSquare.getName());
						}
						else if(player1.equals(((Property) newSquare).getOwner())){
							player1.setProcess("Player 1 has "+newSquare.getName());
						}
					output.write("Player 1\t"+dice+"\t"+player1.getCurrentSquare().getId()+"\t"+player1.getMoney()+
					"\t"+player2.getMoney()+"\t"+player1.getProcess()); //PROCESS EKSİK
					
				
					
						}
				else if(newSquare instanceof Action) {
					output.write("Player 1\t"+dice+"\t");
					if(newSquare instanceof Chance) {
					
						chanceCards.selectCard(player1, player2, banker,output);
						
					}
					else if(newSquare instanceof CommunityChest) {
						communityCards.selectCard(player1, player2, banker,output);
					}
					
					
				}
				else if(newSquare instanceof OtherSquares) {
					
					output.write("Player 1\t"+dice+"\t");
					((OtherSquares) newSquare).move(player1,player2 ,banker,output);
					
						
				}
				
			
				
					
					
			}
			else if(csplitted[0].equals("Player 1" )&& player1.getWaitfor()>0) {
				
				if(player1.getCurrentSquare().getId()==11) {
				output.write("Player 1\t"+dice+"\t"+player1.getCurrentSquare().getId()+"\t"+player1.getMoney()+
						"\t"+player2.getMoney()+"\tPlayer 1 in jail (count="+(4-player1.getWaitfor())+")");
				player1.setWaitfor(player1.getWaitfor()-1);
				}
				else if(player1.getCurrentSquare().getId()==21) {
					output.write("Player 1\t"+dice+"\t"+player1.getCurrentSquare().getId()+"\t"+player1.getMoney()+
							"\t"+player2.getMoney()+"\tPlayer 1 in Free Parking (count=1)");
				}
			}

			
			
			else if (csplitted[0].equals("Player 2")&& player2.getWaitfor()==0) {
				
				player2.setCurrentDice(dice);
				int destinationIndex=player2.getCurrentSquareIndex()+dice;
				if(destinationIndex>39) {
					destinationIndex=destinationIndex-40;
				}
				player2.setCurrentSquare(destinationIndex, banker);
				Square newSquare=player2.getCurrentSquare();
				if ( newSquare instanceof Property)
						{
					
					if (player1.equals(((Property) newSquare).getOwner())) {
						
						player2.rent((Property)newSquare, player1);
						player2.setProcess("Player 2 paid rent for "+newSquare.getName());
					}
					else if(((Property) newSquare).getOwner()==null){
						
							
							player2.own(((Property) newSquare), banker);
							player2.setProcess("Player 2 bought "+newSquare.getName());
						}
						else if(player2.equals(((Property) newSquare).getOwner())){
							player2.setProcess("Player 2 has "+newSquare.getName());
						}
					
					output.write("Player 2\t"+dice+"\t"+player2.getCurrentSquare().getId()+"\t"+player1.getMoney()+
							"\t"+player2.getMoney()+"\t"+player2.getProcess()); ///PROCESS EKSİK
					
						}
				else if(newSquare instanceof Action) {
					output.write("Player 2\t"+dice+"\t");
					if(newSquare instanceof Chance) {
						chanceCards.selectCard(player2, player1, banker,output);
						
					}
					else if(newSquare instanceof CommunityChest) {
						communityCards.selectCard(player2, player1, banker,output);
					}
					
					
				}
				else if(newSquare instanceof OtherSquares) {
					output.write("Player 2\t"+dice+"\t");
					((OtherSquares) newSquare).move(player2,player1, banker,output);
					
					
				}
				
				
				
			}
			else if(csplitted[0].equals("Player 2" )&& player2.getWaitfor()>0) {
				if(player2.getCurrentSquare().getId()==11) {
				output.write("Player 2\t"+dice+"\t"+player2.getCurrentSquare().getId()+"\t"+player1.getMoney()+
						"\t"+player2.getMoney()+"\tPlayer 2 in jail (count="+(4-player2.getWaitfor())+")");
				player2.setWaitfor(player2.getWaitfor()-1);}
				
				else if(player2.getCurrentSquare().getId()==21) {
					output.write("Player 2\t"+dice+"\t"+player2.getCurrentSquare().getId()+"\t"+player1.getMoney()+
							"\t"+player2.getMoney()+"\tPlayer 2 in is in Free Parking (count=1)");
				}
			}
			
			

		output.write("\n");
			}
		
		catch(BankruptException e)	{  //controls if someone bankrupt for ending the game
			
			catched=true;
			if (dicePlayer.equals("Player 1" )) {
				output.write("Player 1\t"+dice+"\t"+player1.getCurrentSquare().getId()+"\t"+player1.getMoney()+
						"\t"+player2.getMoney()+"\tPlayer 1 goes bankrupt\n");
			}
			else if (dicePlayer.equals("Player 2" )) {
				output.write("Player 2\t"+dice+"\t"+player2.getCurrentSquare().getId()+"\t"+player1.getMoney()+
						"\t"+player2.getMoney()+"\tPlayer 2 goes bankrupt\n");
			}
		
			
			output.write(show(this.player1,this.player2,this.banker));
			break;
			
		}
		
		
		
			}
		
	 
	}
		if(!catched) {  // if no one bankrupts program will end by showing result
			output.write(show(this.player1,this.player2,this.banker));
			
		}
	}}}
