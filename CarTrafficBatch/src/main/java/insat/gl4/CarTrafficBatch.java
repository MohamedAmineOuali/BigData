package insat.gl4;

import com.google.gson.Gson;
import insat.gl4.Utilities.GeoPosition;
import insat.gl4.model.Street;
import insat.gl4.model.TimeStep;
import insat.gl4.model.Vehicle;
import org.apache.spark.HashPartitioner;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.spark_project.guava.base.Preconditions.checkArgument;




public class CarTrafficBatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarTrafficBatch.class);
    private static Map<String,Street> streets=new HashMap<String,Street>();


    public static void main(String[] args) {
//        checkArgument(args.length > 1, "Please provide the path of input file and output dir as parameters.");
        String input = "/tmp/kafka/Hello-Kafka/*";
        String output = "/tmp/kafka/out-traffic-batch";

//        new CarTrafficBatch().run(args[0], args[1]);
        new CarTrafficBatch().run(input, output);
    }







    public void run(String inputFilePath, String outputDir) {

        SparkConf conf = new SparkConf()
                .setAppName(CarTrafficBatch.class.getName());

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> textFile = sc.textFile(inputFilePath);

        textFile.foreach( s -> {

            if(s!=null && !s.isEmpty() &&
                    !s.contains("export xmlns:xsi="))
                    /*!s.contains("emission-export xmlns:xsi="))*/
            {


                /*
                System.out.println("*******************************************");
                System.out.println(s);
                System.out.println("*******************************************");
                */


                TimeStep timeStep=null;

                try{
                    Document doc = Jsoup.parse(s);
                    Elements TimeSteps= doc.getElementsByTag("TimeStep");
                    StringReader xml= new StringReader(TimeSteps.last().toString());

                    JAXBContext jaxbContext = JAXBContext.newInstance(TimeStep.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    timeStep = (TimeStep) jaxbUnmarshaller.unmarshal(xml);

                    System.out.println("TimeStep = " + timeStep.toString());

                    batchProcess(timeStep);

                    Gson gson = new Gson();
                    String json = gson.toJson(streets);

                    System.out.println(json);

                }catch (Exception e)
                {
                    e.printStackTrace();
                    System.out.println(s);

                }
            }
            else
            {
                System.out.println("First Emission OR No Data Found");
            }

        });


        JavaRDD hashMapRdd = sc.parallelize(Arrays.asList(streets.keySet().toArray()));
        JavaPairRDD hashMapPairRdd = hashMapRdd.mapToPair(s -> new Tuple2(s, streets.get(s)))
                .partitionBy(new HashPartitioner(100)).persist(StorageLevel.MEMORY_ONLY());


        hashMapPairRdd.saveAsTextFile(outputDir);


//        JavaPairRDD<String, Integer> counts = textFile
//                .flatMap(s -> Arrays.asList(s.split("\t")).iterator())
//                .mapToPair(word -> new Tuple2<>(word, 1))
//                .reduceByKey((a, b) -> a + b);
//        counts.saveAsTextFile(outputDir);
    }








    public static void batchProcess(TimeStep TimeStep)
    {


        GeoPosition position=new GeoPosition();
        for(Vehicle v:TimeStep.getVehicle())
        {
            try {
                String cur=position.getAddressByGpsCoordinates(v.getY(),v.getX());

                Street street= streets.computeIfAbsent(cur, (k) -> new Street(cur));

                street.incrementNbCar(); //detect the most crowded streets (pour y mettre des polices de circulation-et trouver des solutions)
                street.setSpeed(v.getSpeed()); //calculate the average speed in every street
                street.increaseCO2(v.getCO2()); //calculate the total CO2 in every street
                street.increseNoise(v.getNoise()); //calculate the total noise in every street
                street.increaseAvgWaiting(v.getWaiting()); //traffic jam (pour y mettre des polices de circulation)

            } catch (IOException e) {
                e.printStackTrace();
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }

        for (Street street: streets.values())
            street.process();


    }













}