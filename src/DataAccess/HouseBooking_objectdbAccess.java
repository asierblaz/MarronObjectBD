package DataAccess;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import domain.Offer;
import domain.RuralHouse;

public class HouseBooking_objectdbAccess {

	private EntityManager db;
	private EntityManagerFactory emf;
	String fileName = "RuralHouses.odb";

	public HouseBooking_objectdbAccess() {

		emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
		db = emf.createEntityManager();
	}

	public void close() {
		db.close();
	}

	public RuralHouse getHouseId(int id) {
		TypedQuery<RuralHouse> query = db.createQuery("SELECT p FROM RuralHouse p WHERE p.houseNumber=" + id,
				RuralHouse.class);

		RuralHouse rh = query.getSingleResult();

		return rh;
	}

	public void deleteHouses() {
		TypedQuery<RuralHouse> query = db.createQuery("DELETE rh FROM RuralHouse rh", RuralHouse.class);
	}

	public void lookOffers(RuralHouse rb) {
		List<Offer> of = (List<Offer>) rb.getOffers();
		for (Offer o : of) {
			System.out.println(o.toString());
		}
	}

	public void storeOffer(Date date, int tripleNumber, int doubleNumber, int singleNumber, RuralHouse rh) {
		db.getTransaction().begin();
		Offer o = new Offer(date, tripleNumber, doubleNumber, singleNumber, rh);
		rh.add(o);
		db.persist(o);
		db.getTransaction().commit();
	}

	public List<RuralHouse> getAllHouses() {
		TypedQuery<RuralHouse> query = db.createQuery("SELECT rh FROM RuralHouse rh", RuralHouse.class);
		List<RuralHouse> rh = query.getResultList();
		System.out.println("Contenido de la base de datos:");
		for (RuralHouse r : rh)
			System.out.println(r.toString());
		return rh;
	}

	public void setRoom(Offer idO, int valor) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("idO.getNumOffer: " + idO.getNumOffer());
		Offer o = db.find(Offer.class, idO.getNumOffer());
		System.out.println("o.getNumOffer: " + o.getNumOffer());
		db.getTransaction().begin();
		int cont = 0;
		switch (valor) {
		case 1: {
			System.out.println("o.getsingleNumber: " + o.getSingleNumber());
			cont = idO.getSingleNumber();
			o.setSingleNumber(cont - 1);

			break;
		}
		case 2: {
			cont = idO.getDoubleNumber();
			idO.setDoubleNumber(cont - 1);
			break;
		}
		case 3: {
			cont = idO.getTripleNumber();
			idO.setTripleNumber(cont - 1);
			break;
		}
		default:
			break;
		}
		db.getTransaction().commit();
		o = db.find(Offer.class, idO.getNumOffer());

	}

	public RuralHouse storeHouse(String city, String address, int n) {
		db.getTransaction().begin();
		RuralHouse rh = new RuralHouse(city, address, n);
		db.persist(rh);
		db.getTransaction().commit();
		System.out.println("stored:" + rh);
		return rh;
	}
}
