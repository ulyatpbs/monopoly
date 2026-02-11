
public abstract class People {
	private String name;
	private int money;
	
	public  People(String names,int money) {
		name=names;
		this.setMoney(money);
	}
	
	public void payMoney(int amount,People person) throws BankruptException { //method for trade controls bankrupt
		if((this.money - amount) < 0){
            throw new BankruptException();
        }
		else {
		this.setMoney(this.getMoney() - amount);
		person.setMoney(person.getMoney()+ amount);
		}
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}
	
}
