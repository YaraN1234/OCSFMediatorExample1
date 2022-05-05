package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Product;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
//import il.cshaifasweng.OCSFMediatorExample.server.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class SimpleServer extends AbstractServer {

	private static Session session;
	private static Session session1;
	private List<Product> productGeneralList = new ArrayList<Product>();

	public SimpleServer(int port) {
		super(port);

	}

	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();

		// Add ALL of your entities here. You can also try adding a whole package.
		configuration.addAnnotatedClass(Product.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();

		return configuration.buildSessionFactory(serviceRegistry);
	}

	public static void generateProducts()  {
		System.out.println("arrived to generate products function");
		Product product = new Product(5,"btn","flower1","someDetails","5000");
		System.out.println("finisehd creating the product");
		session.save(product);

		/** The call to session.flush() updates the DB immediately without ending the transaction.
		 * Recommended to do after an arbitrary unit of work.
		 * MANDATORY to do if you are saving a large amount of data - otherwise you may get
		 cache errors.*/

		session.flush();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {

		if(msg instanceof ArrayList ){ // arrived from the initializing of the program, so we initialize the database
			// with the starting Products
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx1 = session.beginTransaction();

			List<Product> resultList = (List<Product>) msg;
			for(int i=0;i<resultList.size();i++){
				//productGeneralList.set(i,resultList.get(i));
				session.save(resultList.get(i)); // save the Product in the database
				session.flush();
			}
			tx1.commit();



			for(int i=0;i<resultList.size();i++){
				productGeneralList.add(resultList.get(i));

			}


			session.close();

		}

		else{ // if we arrived to the else it means we reached here from the event handler of the "Apply Changes" button
			// so what we do is take the changes on the product and update the database

			Product recievedProduct = (Product) msg;
			int recievedProductID = recievedProduct.getID();

			String recievedProductName = recievedProduct.getName();
			String recievedProductDetails = recievedProduct.getDetails();
			String recievedProductButton = recievedProduct.getButton();
			String recievedProductPrice = recievedProduct.getPrice();
			String recievedProductImage = recievedProduct.getImage();

			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			try {

				Product updatedProduct = new Product(recievedProductID,recievedProductButton,recievedProductName,recievedProductDetails,recievedProductPrice);


				for(int i = 0 ; i< productGeneralList.size();i++){
					if(productGeneralList.get(i).getID() == recievedProductID){
						productGeneralList.set(i,updatedProduct);
					}
				}


				/* USE UPDATE METHOD IN THE FUTURE */
				for(int i=0;i<productGeneralList.size();i++){

					session.save(productGeneralList.get(i)); // save the Product in the database
					session.flush();
				}
				tx.commit();




			}catch (Exception exception) {
				if (session != null) {
					session.getTransaction().rollback();
				}
				System.err.println("An error occured, changes have been rolled back.");
				exception.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}

		}

	}



}


