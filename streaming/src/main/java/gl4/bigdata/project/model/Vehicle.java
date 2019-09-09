
package gl4.bigdata.project.model;

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
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="eclass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CO" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="CO2" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="HC" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="PMx" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="NOx" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="fuel" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="electricity" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="noise" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="route" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="waiting" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="lane" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pos" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="speed" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="angle" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="x" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="y" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="z" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "vehicle")
public class Vehicle {

    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "eclass", required = true)
    protected String eclass;
    @XmlAttribute(name = "CO", required = true)
    protected float co;
    @XmlAttribute(name = "CO2", required = true)
    protected float co2;
    @XmlAttribute(name = "HC", required = true)
    protected float hc;
    @XmlAttribute(name = "PMx", required = true)
    protected float pMx;
    @XmlAttribute(name = "NOx", required = true)
    protected float nOx;
    @XmlAttribute(name = "fuel", required = true)
    protected float fuel;
    @XmlAttribute(name = "electricity", required = true)
    protected float electricity;
    @XmlAttribute(name = "noise", required = true)
    protected float noise;
    @XmlAttribute(name = "route", required = true)
    protected String route;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "waiting")
    protected Float waiting;
    @XmlAttribute(name = "lane")
    protected String lane;
    @XmlAttribute(name = "pos", required = true)
    protected float pos;
    @XmlAttribute(name = "speed", required = true)
    protected float speed;
    @XmlAttribute(name = "angle", required = true)
    protected float angle;
    @XmlAttribute(name = "x", required = true)
    protected float x;
    @XmlAttribute(name = "y", required = true)
    protected float y;
    @XmlAttribute(name = "z")
    protected Float z;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the eclass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEclass() {
        return eclass;
    }

    /**
     * Sets the value of the eclass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEclass(String value) {
        this.eclass = value;
    }

    /**
     * Gets the value of the co property.
     * 
     */
    public float getCO() {
        return co;
    }

    /**
     * Sets the value of the co property.
     * 
     */
    public void setCO(float value) {
        this.co = value;
    }

    /**
     * Gets the value of the co2 property.
     * 
     */
    public float getCO2() {
        return co2;
    }

    /**
     * Sets the value of the co2 property.
     * 
     */
    public void setCO2(float value) {
        this.co2 = value;
    }

    /**
     * Gets the value of the hc property.
     * 
     */
    public float getHC() {
        return hc;
    }

    /**
     * Sets the value of the hc property.
     * 
     */
    public void setHC(float value) {
        this.hc = value;
    }

    /**
     * Gets the value of the pMx property.
     * 
     */
    public float getPMx() {
        return pMx;
    }

    /**
     * Sets the value of the pMx property.
     * 
     */
    public void setPMx(float value) {
        this.pMx = value;
    }

    /**
     * Gets the value of the nOx property.
     * 
     */
    public float getNOx() {
        return nOx;
    }

    /**
     * Sets the value of the nOx property.
     * 
     */
    public void setNOx(float value) {
        this.nOx = value;
    }

    /**
     * Gets the value of the fuel property.
     * 
     */
    public float getFuel() {
        return fuel;
    }

    /**
     * Sets the value of the fuel property.
     * 
     */
    public void setFuel(float value) {
        this.fuel = value;
    }

    /**
     * Gets the value of the electricity property.
     * 
     */
    public float getElectricity() {
        return electricity;
    }

    /**
     * Sets the value of the electricity property.
     * 
     */
    public void setElectricity(float value) {
        this.electricity = value;
    }

    /**
     * Gets the value of the noise property.
     * 
     */
    public float getNoise() {
        return noise;
    }

    /**
     * Sets the value of the noise property.
     * 
     */
    public void setNoise(float value) {
        this.noise = value;
    }

    /**
     * Gets the value of the route property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoute() {
        return route;
    }

    /**
     * Sets the value of the route property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoute(String value) {
        this.route = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the waiting property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getWaiting() {
        return waiting;
    }

    /**
     * Sets the value of the waiting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setWaiting(Float value) {
        this.waiting = value;
    }

    /**
     * Gets the value of the lane property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLane() {
        return lane;
    }

    /**
     * Sets the value of the lane property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLane(String value) {
        this.lane = value;
    }

    /**
     * Gets the value of the pos property.
     * 
     */
    public float getPos() {
        return pos;
    }

    /**
     * Sets the value of the pos property.
     * 
     */
    public void setPos(float value) {
        this.pos = value;
    }

    /**
     * Gets the value of the speed property.
     * 
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets the value of the speed property.
     * 
     */
    public void setSpeed(float value) {
        this.speed = value;
    }

    /**
     * Gets the value of the angle property.
     * 
     */
    public float getAngle() {
        return angle;
    }

    /**
     * Sets the value of the angle property.
     * 
     */
    public void setAngle(float value) {
        this.angle = value;
    }

    /**
     * Gets the value of the x property.
     * 
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     */
    public void setX(float value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     */
    public void setY(float value) {
        this.y = value;
    }

    /**
     * Gets the value of the z property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getZ() {
        return z;
    }

    /**
     * Sets the value of the z property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setZ(Float value) {
        this.z = value;
    }

}
