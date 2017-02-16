package DataAccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.Date;
import java.util.List;

import domain.*;
import businessLogic.*;
import presentation.*;

public class HouseBooking_objectdbAccess {
	private EntityManager db;
	private EntityManagerFactory emf;
	String fileName = "ruralHousesDB.odb";

	public HouseBooking_objectdbAccess() {
		emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
		db = emf.createEntityManager();
		System.out.println("Base de datos abierta");
	}

	public void close() {
		db.close();
		System.out.println("Base de datos cerrada");
	}
	// como el labo de objectbd

	// Añadir casa a la base de datos

	public RuralHouse storeHouse(String city, String address) {
		db.getTransaction().begin();
		RuralHouse house = new RuralHouse(city, address);
		db.persist(house);
		db.getTransaction().commit();
		System.out.println("Insertado: " + house);
		return house;
	}

	// añadir nueva oferta a la base de datos

	public void storeOffer(Date date, int tripleNumber, int doubleNumber, int singleNumber, RuralHouse rh) {
		db.getTransaction().begin();
		Offer oferta = new Offer(date, tripleNumber, doubleNumber, singleNumber, rh);
		rh.add(oferta);
		db.persist(oferta);
		db.getTransaction().commit();
		System.out.println("Insertado: " + oferta);

	}

	// sacar todas las casas de la base de datos

	public List<RuralHouse> getAllHouses() {
		TypedQuery<RuralHouse> query = db.createQuery("SELECT rh FROM RuralHouse rh", RuralHouse.class);
		List<RuralHouse> rh = query.getResultList();
		System.out.println("Contenido de la base de datos:");
		for (RuralHouse casa : rh)
			System.out.println(casa.toString());

		return rh;
	}

}
