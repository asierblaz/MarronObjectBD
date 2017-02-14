package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class RuralHouse {

private static int numberOfHouses=1;
private Integer houseNumber;
private String city;
private String address;
private Collection<Offer> offers;



public RuralHouse(String city,String address) {
	super();
	houseNumber=RuralHouse.numberOfHouses++;
	this.city = city;
	this.address=address;
	offers = new ArrayList<Offer>();
}

public Integer getHouseNumber() {
	return houseNumber;
}
public void setHouseNumber(Integer houseNumber) {
	this.houseNumber = houseNumber;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

public void add(Offer o){
	offers.add(o);
}

public Collection<Offer> getOffers() {
	return offers;
}

public void setOffers(Collection<Offer> offers) {
	this.offers = offers;
}
public String toString() {return houseNumber+"/"+city+"/"+address;}

public Offer addOffer(Date date, int tripleNumber, int doubleNumber, int singleNumber){
	Offer o=new Offer(date, tripleNumber, doubleNumber, singleNumber, this);
	offers.add(o);
	return o;
}
}
