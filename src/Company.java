

public class Company extends Property  {

	Company(int ids, String names, int costs) {
		super(ids, names, costs);
		
	}

	@Override
	public int rentCost(int dice) {
	
		return dice*4;
	}

	

	

}
