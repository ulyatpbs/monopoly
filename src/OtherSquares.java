import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class OtherSquares extends Square  {

	OtherSquares(int ids, String names) {
		super(ids, names);
	   
	}
	public void move(Player player,Player rival,Banker banker,FileWriter output) throws IOException, BankruptException {
		LinkedList<Square> SquareList=PropertyAndCardReader.Squares;
		String processDraft=player.getName();
		switch (this.getId()) {
		case 5:  //income tax
			player.payMoney(100, banker);
			processDraft+=" paid Tax";
			break;
		case 39:   //super tax
			processDraft+=" paid Tax";
			player.payMoney(100, banker);
			break;	
		case 11:   //jail
			processDraft+=" went to jail";
			player.setWaitfor(3);
			break;	
		case 31:   //go to jail
			player.jump(SquareList.get(10));
			player.setWaitfor(3);
			processDraft+=" went to jail";
			break;
		case 1 :  //go
			processDraft+=" is in GO square";
			break;
		 case 21:    //free parking
			player.setWaitfor(1);
			processDraft+=" is in Free Parking";
			break;
			
		}
		if(player.getName().equals("Player 1")) {
			output.write(player.getCurrentSquare().getId()+"\t"+player.getMoney()+
				"\t"+rival.getMoney()+"\t"+processDraft);}
		else if(player.getName().equals("Player 2")) {
			output.write(player.getCurrentSquare().getId()+"\t"+rival.getMoney()+
				"\t"+player.getMoney()+"\t"+processDraft);}
	
	}

}
