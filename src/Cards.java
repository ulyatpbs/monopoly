import java.io.FileWriter;
import java.io.IOException;

public interface Cards {  //interface for chance and community chest cards 
	
	public void selectCard(Player thePlayer,Player rival,
			Banker banker,FileWriter output) throws IOException, BankruptException;
}
