
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.json.JSONObject;

public class APITester {

    public static final String ORIGIN = "14350+Farm+to+Market+Rd+1488+Magnolia+TX+77345";
    public static void main(String[] args)throws Exception {
        String apiKey ="";
        try{
            Scanner sc = new Scanner(new File("apikey"));
            apiKey = sc.nextLine();
            sc.close();
        }catch(IOException e){
            e.printStackTrace();
        }


        String destination = "42202+FM+1774+Magnolia+TX+77345";
        URL mapsAPI = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=" + ORIGIN + "&destination=" + destination + "&units=metric&key=AIzaSyCNr9dwHHYdkSARVY7z9ic1YqFJy00OgsI");
        URLConnection con = mapsAPI.openConnection();
        Scanner rd = new Scanner(con.getInputStream());
        StringBuilder bob = new StringBuilder();
        while(rd.hasNext()){
            bob.append(rd.next());
        }
        JSONObject obj = new JSONObject(bob.toString());
        int distance = ((JSONObject)obj.query("/routes/0/legs/0/distance")).getInt("value");

    }
}
