package insat.gl4.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}vehicle" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="time" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "vehicle"
})
@XmlRootElement(name = "timestep")
public class TimeStep {

    protected List<Vehicle> vehicle;
    @XmlAttribute(name = "time", required = true)
    protected float time;

    /**
     * Gets the value of the vehicle property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vehicle property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVehicle().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Vehicle }
     *
     *
     */
    public List<Vehicle> getVehicle() {
        if (vehicle == null) {
            vehicle = new ArrayList<Vehicle>();
        }
        return this.vehicle;
    }

    /**
     * Gets the value of the time property.
     *
     */
    public float getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     *
     */
    public void setTime(float value) {
        this.time = value;
    }

    @Override
    public String toString() {
        return "TimeStep{" +
                "vehicle=" + vehicle +
                ", time=" + time +
                '}';
    }
}
