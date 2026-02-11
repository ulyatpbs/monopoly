

public class Land extends Property {

	Land(int ids, String names, int costs) {
		super(ids, names, costs);
	}

	@Override
	public int rentCost(int landsCost) {
		int rent = 0;
		if(landsCost<=2000) {
			
			rent=(int)(landsCost*0.4); 
		}
		else if(landsCost<=3000) {
			rent=(int) (landsCost*0.3);
		}
		else if(landsCost<=4000) {
			rent=(int) (landsCost*0.35);
		}
		return rent;
	
		
	
	}
}
