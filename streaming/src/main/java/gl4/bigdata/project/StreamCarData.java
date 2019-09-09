package gl4.bigdata.project;
import com.google.gson.Gson;
import gl4.bigdata.project.model.Street;
import gl4.bigdata.project.model.Timestep;
import gl4.bigdata.project.model.Vehicle;
import gl4.bigdata.project.utilies.KafkaWriter;
import gl4.bigdata.project.utilies.StreetLocator;
import kafka.serializer.StringDecoder;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.*;


import static org.apache.spark.storage.StorageLevel.MEMORY_ONLY;


public class StreamCarData {

    private static StreetLocator position=new StreetLocator();

    public static Map<String,Street> processtimeStep(Timestep timestep)
    {
        Map<String,Street> streets=new HashMap<String,Street>();


        for(Vehicle v:timestep.getVehicle())
        {

            String cur = position.locate(v);

            Street street = streets.computeIfAbsent(cur, (k) -> new Street(cur,v.getY(),v.getX()));

            street.incrementNbCar();
            street.setSpeed(v.getSpeed());
            street.increaseCO2(v.getCO2());
            street.increseNoise(v.getNoise());
            street.increaseAvgWaiting(v.getWaiting());


        }

        for (Street street: streets.values())
            street.process();
        return streets;

    }


    public static void main(String[] args) throws Exception {

        if (args.length < 5) {
            System.err.println("Usage: Stream Car data <zkQuorum> <group> <topics> <numThreads> <WriteTopic>");
            System.exit(1);
        }

        SparkConf sparkConf = new SparkConf().setAppName("stream cars data");

        final JavaSparkContext jSC = new JavaSparkContext(sparkConf);
        // Creer le contexte avec une taille de batch de 2 secondes
        JavaStreamingContext jssc = new JavaStreamingContext(jSC,new Duration(2000));

        int numThreads = Integer.parseInt(args[3]);
//
      Map<String, Integer> topicMap = new HashMap<>();
        String[] topics = args[2].split(",");

        for (String topic: topics) {
            topicMap.put(topic, numThreads);
        }



        Set<String> topicsSet = new HashSet<>(Arrays.asList(topics));
        Map<String, String> kafkaParams = new HashMap<>();
        kafkaParams.put("metadata.broker.list", args[0]);
        kafkaParams.put("group.id", args[1]);
        kafkaParams.put("zookeeper.connect", args[0]);
        kafkaParams.put("fetch.message.max.bytes", "1100000000");

        KafkaWriter producer=new KafkaWriter(args[4]);

        JavaPairReceiverInputDStream<String, String> messages=KafkaUtils.createStream(jssc,
                String.class,
                String.class,
                StringDecoder.class,
                StringDecoder.class,
                kafkaParams,
                topicMap,MEMORY_ONLY() );


        JavaDStream<String> data = messages.map(Tuple2::_2);


        data.foreachRDD((VoidFunction<JavaRDD<String>>) data1 -> {
            if(!data1.isEmpty()){
                List<String> result= data1.collect();

                if(result.size()>0)
                {
//                    String xmls = String.join("\n", result);
                    Timestep timestep=null;

                    try{
//                        Document doc = Jsoup.parse(xmls);
//                        Elements timesteps= doc.getElementsByTag("timestep");
//                        StringReader xml= new StringReader(timesteps.last().toString());
                        StringReader xml= new StringReader(result.get(result.size() - 1));



                        JAXBContext jaxbContext = JAXBContext.newInstance(Timestep.class);
                        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                        timestep = (Timestep) jaxbUnmarshaller.unmarshal(xml);

                        Map<String,Street> streets=processtimeStep(timestep);
                        Gson gson = new Gson();
                        String json = gson.toJson(streets);

                        System.out.println(json);
                        producer.write("",json);


                    }catch (Exception e)
                    {
                        e.printStackTrace();
                        System.out.println(String.join("\n", result));

                    }
                }

            }else {
                System.out.println("Got no data in this window");
            }

        });

        jssc.start();
        jssc.awaitTermination();
    }

}
