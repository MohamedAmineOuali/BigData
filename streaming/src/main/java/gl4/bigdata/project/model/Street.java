package gl4.bigdata.project.model;

public class Street {
    private int nbCars;
    private String name;
    private float avgWaiting;
    private float topSpeed;
    private float avgSpeed;
    private float noise;
    private float co2;


    public void process()
    {
        avgWaiting/=nbCars;
        avgSpeed/=nbCars;
    }

    public float getNoise() {
        return noise;
    }

    public void increseNoise(float noise) {
        this.noise += noise;
    }


    public float getAvgWaiting() {
        return avgWaiting;
    }

    public void increaseAvgWaiting(float avgWaiting) {
        this.avgWaiting += avgWaiting;
    }


    public int getNbCars() {
        return nbCars;
    }

    public  void incrementNbCar(){
        this.nbCars++;
    }

    public void setSpeed(float speed)
    {
        this.avgSpeed+=speed;
        this.topSpeed=java.lang.Float.max(this.topSpeed,speed);
    }


    public void increaseCO2(float co2)
    {
        this.co2+=co2;
    }

    public void setNbCars(int nbCars) {
        this.nbCars = nbCars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCo2() {
        return co2;
    }

    public void setCo2(float co2) {
        this.co2 = co2;
    }

    public float getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(float topSpeed) {
        this.topSpeed = topSpeed;
    }

    public float getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(float avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Street(String name) {
        this.nbCars = 0;
        this.name = name;
        this.co2 = 0;
        this.noise=0;
        this.topSpeed = 0;
        this.avgWaiting=0;
        this.avgSpeed = 0;
    }
}
