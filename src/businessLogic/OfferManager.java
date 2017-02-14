package businessLogic;

import java.util.Collection;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Offer;
@WebService
public interface OfferManager {
	@WebMethod
	Collection<Offer> getConcreteOffers(String city, Date date);
}
