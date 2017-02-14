package businessLogic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.RuralHouse;
import domain.Offer;

@WebService(endpointInterface = "businessLogic.OfferManager")
public class MyOfferManager implements OfferManager{
ArrayList<RuralHouse> ruralHousessDB;

public MyOfferManager () {
	
	ruralHousessDB = new ArrayList<RuralHouse>();
	
	RuralHouse rh1 = new RuralHouse("Donostia","Avenida, 7");

	rh1.addOffer(newDate(2017,1,1),3,3,0);  
	rh1.addOffer(newDate(2017,1,2),3,3,0);
	rh1.addOffer(newDate(2017,1,3),3,3,0);
	rh1.addOffer(newDate(2017,1,4),3,3,0);
	rh1.addOffer(newDate(2017,1,5),3,3,0);
	rh1.addOffer(newDate(2017,1,6),3,3,0);
	rh1.addOffer(newDate(2017,1,7),3,3,0);
	rh1.addOffer(newDate(2017,1,8),0,0,3);
	rh1.addOffer(newDate(2017,1,9),0,0,3);
	rh1.addOffer(newDate(2017,1,10),0,0,3);
	rh1.addOffer(newDate(2017,1,11),0,0,3);
	rh1.addOffer(newDate(2017,1,12),0,1,3);
	rh1.addOffer(newDate(2017,1,13),1,1,1);
	rh1.addOffer(newDate(2017,1,14),0,1,3);
	
	RuralHouse rh2 = new RuralHouse("Donostia","San Martin, 13");
	rh2.addOffer(newDate(2017,1,2),1,1,1);
		
	RuralHouse rh3 = new RuralHouse("Bilbo","Zabalburu, 6");
	rh3.addOffer(newDate(2017,1,1),1,1,1);
	rh3.addOffer(newDate(2017,1,2),0,1,0);
	rh3.addOffer(newDate(2017,1,3),1,0,1);
	
	ruralHousessDB.add(rh1);	 
	ruralHousessDB.add(rh2);		
	ruralHousessDB.add(rh3);		

	
}
		@WebMethod
		public Collection<Offer> getConcreteOffers(String city, Date date) {
			
				ArrayList<Offer> res = new ArrayList<Offer>();
				for (RuralHouse rh : ruralHousessDB) 
					if ((rh.getCity().equals(city)))
						for (Offer off : rh.getOffers()) 
							if ((off.getDate().compareTo(date))==0) 
								res.add(off);
				return res;	
		}
		
		private Date newDate(int year,int month,int day) {

		     Calendar calendar = Calendar.getInstance();
		     calendar.set(year, month, day,0,0,0);
		     calendar.set(Calendar.MILLISECOND, 0);

		     return calendar.getTime();
		}
}