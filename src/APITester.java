
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONArray;

public class APITester {

    public static final String ORIGIN = "14350+Farm+to+Market+Rd+1488+Magnolia+TX+77345";
    public static void main(String[] args)throws Exception {
        String destination = "42202+FM+1774+Magnolia+TX+77345";
        URL mapsAPI = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" + ORIGIN + "&destination=" + destination + "&units=metric&key=AIzaSyCNr9dwHHYdkSARVY7z9ic1YqFJy00OgsI");
        URLConnection con = mapsAPI.openConnection();
        Scanner rd = new Scanner(con.getInputStream());
        StringBuilder bob = new StringBuilder();
        while(rd.hasNext()){
            bob.append(rd.next());
        }
        JSONObject obj = new JSONObject(bob.toString());
        obj = (JSONObject)((JSONArray) obj.get("route")).get(0);
        obj = (JSONObject)((JSONArray) obj.get("legs")).get(0);
        Integer distance = (Integer)((JSONObject)obj.get("distance")).get("value");
        System.out.println(distance);

    }
}
