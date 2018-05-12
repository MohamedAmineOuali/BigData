package insat.gl4.Utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import insat.gl4.model.Street;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtilities {


    public static String convertObjectToJSON(Street s)
    {

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }

    }


    public static String convertStreetsMapToJSON(Map<String,Street> streets)
    {
        Gson gson = new Gson();
        String jsonStudents = gson.toJson(streets.values());
        return jsonStudents;
    }


    public static  void main(String[] args)
    {

//        Street s = new Street("rue-0");
//
//        System.out.println(convertObjectToJSON(s));

        Map<String,Street> streets=new HashMap<String,Street>();
        streets.put("0", new Street("rue-1"));
        streets.put("1", new Street("rue-2"));


        /*Gson gson = new Gson();
        String jsonStreets = gson.toJson(streets.values());
        System.out.println(jsonStreets);*/

        System.out.println(convertStreetsMapToJSON(streets));

        StreetsRepository sr = new StreetsRepository();

        sr.insertStreets(streets);


        System.out.println("END.");


    }

}
