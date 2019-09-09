package gl4.bigdata.project.utilies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import gl4.bigdata.project.model.Vehicle;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StreetLocator {

    private Map<String,String> street;
    private GeoPosition position;
    private String filename="../../../street_name.txt";
    public StreetLocator()
    {

        this.position=new GeoPosition();


        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            street = (HashMap<String, String>) ois.readObject();
            ois.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
            this.street=new HashMap<>();
        }


        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(filename);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(street);
                    oos.close();
                    fos.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }


    public String locate(Vehicle v)
    {
        // TODO: 4/15/18 verify if the street id is this
        String idStreet=v.getLane().split("#")[0];

        String  value = street.get(idStreet);
        if(value!=null)
            return value;

        try {
            value=position.getAddressByGpsCoordinates(v.getY(),v.getX());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(value=="" || value==null)
            value="Location not known";

        street.put(idStreet,value);
        return value;

    }



}
