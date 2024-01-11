import org.json.JSONArray;
import org.json.JSONObject;

public class DroneMapper {

    public static void load_drones(){

    }
    private static final String drones = "drones";
    public static void main(String[] args) {
        ApiAdapter.apiReader(drones);

    }
    public static void test(String input) {
        JSONObject wholeFile = new JSONObject(input);
        JSONArray jsonFile = wholeFile.getJSONArray("results");
        for (int i = 0; i < jsonFile.length(); i++) {
            JSONObject o = jsonFile.getJSONObject(i);
            if(o.has("carriage_type") && o.has("carriage_weight")){
                String a = o.getString("carriage_type");
                int b = o.getInt("carriage_weight");
                int id = o.getInt("id");
                System.out.println("Drone " + id + ": carriage type " + a + " (weight: " + b + "g)");
            }
        }
    }


    public static void mapDrone(JSONObject drone_json){
        Drone drone = new Drone();
        drone.set_id(drone_json.getInt("id"));
        drone.set_drone_type(drone_json.getString("drone_type"));
        drone.set_created(drone_json.getString("created"));
        drone.set_serial_number(drone_json.getString("serial_number"));
        drone.set_carriage_weight(drone_json.getInt("carriage_weight"));
        drone.set_carriage_type(drone_json.getString("carriage_type"));
    }
}
