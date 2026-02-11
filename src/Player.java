import java.util.ArrayList;
import java.util.LinkedList;

public class Player  extends People{
	private int currentDice;
	private Square currentSquare =PropertyAndCardReader.Squares.get(0) ; 
	private int railroadNumber=0;
	private int waitfor=0;
	private String process="";
	
	private LinkedList<Property> HisProperties = new LinkedList<Property>();
    private LinkedList<Property> HisRents= new LinkedList<Property>();
    
	public Player(String names, int money) {
		super(names, money);
		
	}
	public void own(Property place, Banker banker) throws BankruptException { //for buying a property
		if (place instanceof Railroad) {
			HisProperties.add(place);
			this.payMoney(place.getCost(), banker);
			railroadNumber++;
		}
		else {
			HisProperties.add(place);
			this.payMoney(place.getCost(), banker);
		}
		place.setOwner(this);
		
		
	}
	
	
	public void rent(Property place,Player player) throws BankruptException {//for renting a property
		if (!(HisRents.contains(place))){
			
			if (place instanceof Company) {
				this.payMoney(place.rentCost(currentDice), player);
				
			}
			else if (place instanceof Land){
				this.payMoney(place.rentCost(place.getCost()), player);
			}
			else if (place instanceof Railroad){
				int railroadNu =player.railroadNumber;
				this.payMoney(place.rentCost(railroadNu), player);
			}
			HisRents.add(place);
			
			
		}
		
		
	}
    
	
	public int getCurrentDice() {
		return currentDice;
	}
	public void setCurrentDice(int currentDice) {
		this.currentDice = currentDice;
	}
	public Square getCurrentSquare() {
		return currentSquare;
	}
	public int getCurrentSquareIndex() {
		return  PropertyAndCardReader.Squares.indexOf(currentSquare);
	}
	
	public void setCurrentSquare(int SquareIndex,Banker banker) throws BankruptException { //moving step by step
		if(this.currentSquare.getId() > SquareIndex ) {
			banker.payMoney(200, this);
		}
		this.currentSquare = PropertyAndCardReader.Squares.get(SquareIndex);
	}
	public void jump(Square jumpto) { //jumping to a square (bc of the rule about passing go square)
		this.currentSquare = jumpto;
		
	}
	public int getWaitfor() {
		return waitfor;
	}
	public void setWaitfor(int waitfor) {
		this.waitfor = waitfor;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	
	public String properties() {  //returns players properties
		String hisPlaces;
		ArrayList<String> proplist = new ArrayList<String>();
       
		for(Property place:HisProperties) {
			proplist.add(place.getName());
						
		}
		 hisPlaces = String.join(",", proplist);
		 return hisPlaces;
	}}

    

