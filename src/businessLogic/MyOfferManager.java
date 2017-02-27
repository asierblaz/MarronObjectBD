package businessLogic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import DataAccess.*;
import domain.*;


@WebService(endpointInterface = "businessLogic.OfferManager")
public class MyOfferManager implements OfferManager{
	private HouseBooking_objectdbAccess RuralHousesBD;
	
	Collection<Offer> res;
	
public MyOfferManager () {
	RuralHousesBD = new HouseBooking_objectdbAccess();
	try{
	RuralHouse rh1 = RuralHousesBD.storeHouse("Donostia","Avenida, 7", 6);

	RuralHousesBD.storeOffer(newDate(2017,1,1),3,3,0,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,2),3,3,0,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,3),3,3,0,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,2),3,3,0,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,4),3,3,0,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,5),3,3,0,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,6),3,3,0,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,7),3,3,0,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,8),0,0,3,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,9),0,0,3,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,10),0,0,3,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,11),0,0,3,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,12),0,1,3,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,13),1,1,1,rh1);
	RuralHousesBD.storeOffer(newDate(2017,1,14),0,1,3,rh1);
	RuralHousesBD.lookOffers(rh1);
		
	RuralHousesBD.storeHouse("Donostia", "San Martin, 13",2);
	RuralHouse rh2= RuralHousesBD.getHouseId(2);
	RuralHousesBD.storeOffer(newDate(2017,1,2),1,1,1,rh2);
	
	RuralHousesBD.storeHouse("Donostia", "San Martin, 13",3);
	RuralHouse rh3= RuralHousesBD.getHouseId(3);
	RuralHousesBD.storeOffer(newDate(2017,1,1),1,1,1,rh3);
	RuralHousesBD.storeOffer(newDate(2017,1,2),0,1,0,rh3);
	RuralHousesBD.storeOffer(newDate(2017,1,3),1,0,1,rh3);		
	}catch(Exception e){
	}
	RuralHousesBD.close();
	
}
		@WebMethod
		public Collection<Offer> getConcreteOffers(String city, Date date) {
				RuralHousesBD = new HouseBooking_objectdbAccess();
				ArrayList<Offer> res = new ArrayList<Offer>();
				ArrayList<RuralHouse> RuralHousesBDa = (ArrayList<RuralHouse>) RuralHousesBD.getAllHouses();
				for (RuralHouse rh : RuralHousesBDa) 
					if ((rh.getCity().compareToIgnoreCase(city)==0))
						for (Offer off : rh.getOffers()) 
							if ((off.getDate().compareTo(date))==0) 
								res.add(off);
				RuralHousesBD.close();
				return res;	
		}
		
		private Date newDate(int year,int month,int day) {

		     Calendar calendar = Calendar.getInstance();
		     calendar.set(year, month, day,0,0,0);
		     calendar.set(Calendar.MILLISECOND, 0);

		     return calendar.getTime();
		}
		  @WebMethod
		    @Override
		    public void modifyRoom(int n, int hab){
		        RuralHousesBD = new HouseBooking_objectdbAccess();
		        ArrayList<RuralHouse> RuralHousesBDa = (ArrayList<RuralHouse>) RuralHousesBD.getAllHouses();
		        for (RuralHouse rh : RuralHousesBDa) 
		            for (Offer o : rh.getOffers()) 
		                if ((o.getNumOffer())==n){
		                        RuralHousesBD.setRoom(o, hab);
		                }

		        RuralHousesBD.close();
		    }
		    //@WebMethod

		    @Override
		    public void removeOffer(){
		        RuralHousesBD = new HouseBooking_objectdbAccess();
		        RuralHousesBD.deleteHouses();
		        RuralHousesBD.close();

		    }
}