
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import org.json.simple.JSONArray;

public class PropertyAndCardReader {

    
	 public static LinkedList<Square> Squares = new LinkedList<Square>();
	 public static LinkedList<String>  ChanceList = new LinkedList<String>();
	 public static LinkedList<String>  CommunityChestList= new LinkedList<String>();
     
	 
     public void propertyJsonReader(){
         JSONParser processor = new JSONParser();
         try (Reader file = new FileReader("property.json")) {
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray Land = (JSONArray) jsonfile.get("1");
            for (Object i: Land) {
                Squares.add(new Land(Integer.parseInt((String)((JSONObject) i).get("id")), (String)((JSONObject) i).get("name"), Integer.parseInt((String)((JSONObject) i).get("cost"))));
            }
            JSONArray RailRoad = (JSONArray) jsonfile.get("2");
            for (Object i: RailRoad) {
                Squares.add(new Railroad(Integer.parseInt((String)((JSONObject) i).get("id")), (String)((JSONObject) i).get("name"), Integer.parseInt((String)((JSONObject) i).get("cost"))));
            }
            JSONArray Company = (JSONArray) jsonfile.get("3");
            for (Object i: Company) {
                Squares.add(new Company(Integer.parseInt((String)((JSONObject) i).get("id")), (String)((JSONObject) i).get("name"), Integer.parseInt((String)((JSONObject) i).get("cost"))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
          
     }
     public void listJsonReader(){
    
         JSONParser processor = new JSONParser();
         try (Reader file = new FileReader("list.json")){
             JSONObject jsonfile = (JSONObject) processor.parse(file);
             JSONArray chanceList = (JSONArray) jsonfile.get("chanceList");
             
             for(Object i:chanceList){
                 ChanceList.add((String)((JSONObject)i).get("item"));
             }
             JSONArray communityChestList = (JSONArray) jsonfile.get("communityChestList");
             for(Object i:communityChestList){
                 CommunityChestList.add((String)((JSONObject)i).get("item"));
             }

         }catch (IOException e){
             e.printStackTrace();
         }catch (ParseException e){
             e.printStackTrace();
         }
         
      }
     
     public void otherSquares() {
         Squares.add(new OtherSquares(1,"GO"));            //  THATS WHERE I USED POLYMORPHİSM
         Squares.add(new OtherSquares(5,"Income Tax"));    // the list references square class 
         Squares.add(new OtherSquares(11,"Jail"));         // but i created property(land, company etc.) objects in this list above
         Squares.add(new OtherSquares(21,"Free Parking"));  // and here i created OtehrSquares objects
         Squares.add(new OtherSquares(31,"Go to Jail"));
         Squares.add(new OtherSquares(39,"Super Tax")); 
         Squares.add(new CommunityChest(3,"CommunityChest"));
         Squares.add(new CommunityChest(18,"CommunityChest"));
         Squares.add(new CommunityChest(34,"CommunityChest"));
         Squares.add(new Chance(8,"Chance"));
         Squares.add(new Chance(23,"Chance"));
         Squares.add(new Chance(37,"Chance"));
         
         Collections.sort(Squares,Comparator.comparing(Square::getId));
         
     }
   
}


