package insat.gl4.Utilities;

import org.apache.hadoop.io.FloatWritable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GeoPosition implements Serializable{


    private final String basicUrl;

    public GeoPosition()
    {
        basicUrl="http://maps.googleapis.com/maps/api/geocode/json?latlng=";
    }

    public GeoPosition(String basicUrl)
    {
        this.basicUrl=basicUrl;
    }
    /**
     *
     * @param lng
     * @param lat
     * @return
     */
    public String getAddressByGpsCoordinates(float lng, float lat)
            throws MalformedURLException, IOException, org.json.simple.parser.ParseException {

        URL url = new URL(basicUrl
                + Float.toString(lng)+ "," + Float.toString(lat) + "&sensor=true");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String formattedAddress = "";

        try {
            InputStream in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String result, line = reader.readLine();
            result = line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }

            JSONParser parser = new JSONParser();
            JSONObject rsp = (JSONObject) parser.parse(result);

            if (rsp.containsKey("results")) {
                /**
                 * {"results":[
                 * {"address_components":[
                 * {"long_name":"1","types":["street_number"],"short_name":"1"},
                 * {"long_name":"Entenplatz","types":["route"],"short_name":"Entenpl."},
                 * {"long_name":"Gries","types":["sublocality","political"],"short_name":"Gries"},
                 * {"long_name":"Graz","types":["locality","political"],"short_name":"Graz"},
                 * {"long_name":"Graz","types":["administrative_area_level_2","political"],"short_name":"Graz"},
                 * {"long_name":"Styria","types":["administrative_area_level_1","political"],"short_name":"Stmk."},
                 * {"long_name":"Austria","types":["country","political"],"short_name":"AT"},
                 * {"long_name":"8020","types":["postal_code"],"short_name":"8020"}],
                 * "formatted_address":"Entenplatz 1, 8020 Graz, Austria",
                 * "types":["street_address"],"geometry":{"viewport":{"southwest":{"lng":15.4327164197085,"lat":47.0663661197085},"northeast":{"lng":15.4354143802915,"lat":47.0690640802915}},"location_type":"ROOFTOP","location":{"lng":15.4340654,"lat":47.0677151}}},
                 *
                 * {"address_components":[{...}]}
                 *
                 * ]}
                 */
                JSONArray matches = (JSONArray) rsp.get("results");
                JSONObject data = (JSONObject) matches.get(0); //TODO: check if idx=0 exists
                formattedAddress = (String) data.get("formatted_address"); /**Brückenkopfgasse 7, 8020 Graz, Austria **/
            }

            return "";
        } finally {
            urlConnection.disconnect();
            return formattedAddress;
        }
    }
}

