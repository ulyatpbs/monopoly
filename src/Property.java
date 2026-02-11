
public abstract class  Property extends Square {
	
	private int cost;
	private Player owner=null;
	Property(int ids,String names,int costs){
		super(ids,names);
		cost=costs;
	}
	
	public abstract int rentCost(int parameter);
	
	

	public int getCost() {
		return cost;
	}


	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	

}
