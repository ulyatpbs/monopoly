
public class Railroad extends Property  {

	Railroad(int ids, String names, int costs) {
		super(ids, names, costs);
			}

	@Override
	public int rentCost(int numberofRoads) {
		return numberofRoads*25;
		
		
	}

}
