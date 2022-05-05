package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Product;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.sun.xml.bind.v2.schemagen.Util.equal;


public class PrimaryController {
	static boolean returnedFromSecondaryController = false;
	boolean firstRun = true;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView flower_button1;

	@FXML
	private ImageView flower_button2;

	@FXML
	private ImageView flower_button3;

	@FXML
	private ImageView flower_button4;

	@FXML
	private ImageView flower_button5;


	@FXML
	private ImageView flower_button6;

	@FXML
	public DialogPane flower_name1;

	@FXML
	public DialogPane flower_name2;

	@FXML
	public DialogPane flower_name3;

	@FXML
	public DialogPane flower_name4;

	@FXML
	public DialogPane flower_name5;

	@FXML
	public DialogPane flower_name6;

	@FXML
	public DialogPane flower_price1;

	@FXML
	public DialogPane flower_price2;

	@FXML
	public DialogPane flower_price3;

	@FXML
	public DialogPane flower_price4;

	@FXML
	public DialogPane flower_price5;

	@FXML
	public DialogPane flower_price6;

	public static String current_button;
	public void updateFields(List<Product> allProducts)
	{
		flower_name1.setContentText(allProducts.get(0).getName());
		flower_name2.setContentText(allProducts.get(1).getName());
		flower_name3.setContentText(allProducts.get(2).getName());
		flower_name4.setContentText(allProducts.get(3).getName());
		flower_name5.setContentText(allProducts.get(4).getName());
		flower_name6.setContentText(allProducts.get(5).getName());

		flower_price1.setContentText(allProducts.get(0).getPrice());
		flower_price2.setContentText(allProducts.get(1).getPrice());
		flower_price3.setContentText(allProducts.get(2).getPrice());
		flower_price4.setContentText(allProducts.get(3).getPrice());
		flower_price5.setContentText(allProducts.get(4).getPrice());
		flower_price6.setContentText(allProducts.get(5).getPrice());
	}
	static List<Product> allProducts = new ArrayList<>();
	@FXML
	void product_clicked(javafx.scene.input.MouseEvent event ) throws IOException {
		current_button = ((ImageView)event.getSource()).getId();
		App.setRoot("secondary");
	}


	@FXML
	void initialize() throws MalformedURLException {
		assert flower_button1 != null : "fx:id=\"flower_button1\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button2 != null : "fx:id=\"flower_button2\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button3 != null : "fx:id=\"flower_button3\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button4 != null : "fx:id=\"flower_button4\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button5 != null : "fx:id=\"flower_button5\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button6 != null : "fx:id=\"flower_button6\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name1 != null : "fx:id=\"flower_name1\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name2 != null : "fx:id=\"flower_name2\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name3 != null : "fx:id=\"flower_name3\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name4 != null : "fx:id=\"flower_name4\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name5 != null : "fx:id=\"flower_name5\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name6 != null : "fx:id=\"flower_name6\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price1 != null : "fx:id=\"flower_price1\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price2 != null : "fx:id=\"flower_price2\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price3 != null : "fx:id=\"flower_price3\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price4 != null : "fx:id=\"flower_price4\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price5 != null : "fx:id=\"flower_price5\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price6 != null : "fx:id=\"flower_price6\" was not injected: check your FXML file 'primary.fxml'.";

		initializeData();
		if(returnedFromSecondaryController)
		{
			updateFields(allProducts);
		}
	}


	void initializeData(){
		if(!returnedFromSecondaryController){

			Product flower1 = new Product(1,flower_button1.getId(),flower_name1.getContentText(),"",flower_price1.getContentText());
			allProducts.add(flower1);
			Product flower2 = new Product(2,flower_button2.getId(),flower_name2.getContentText(),"",flower_price2.getContentText());
			allProducts.add(flower2);
			Product flower3 = new Product(3,flower_button3.getId(),flower_name3.getContentText(),"",flower_price3.getContentText());
			allProducts.add(flower3);
			Product flower4 = new Product(4,flower_button4.getId(),flower_name4.getContentText(),"",flower_price4.getContentText());
			allProducts.add(flower4);
			Product flower5 = new Product(5,flower_button5.getId(),flower_name5.getContentText(),"",flower_price5.getContentText());
			allProducts.add(flower5);
			Product flower6 = new Product(6,flower_button6.getId(),flower_name6.getContentText(),"",flower_price6.getContentText());
			allProducts.add(flower6);

			List<Product> productList = new ArrayList<Product>() ;
			productList.add(flower1);
			productList.add(flower2);
			productList.add(flower3);
			productList.add(flower4);
			productList.add(flower5);
			productList.add(flower6);
			try {
				SimpleClient.getClient().sendToServer(productList); // sends the updated product to the server class
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

	}

	static Product getCurrent_button()
	{
		for (int i = 0; i < allProducts.size(); i++) {
			if (equal(allProducts.get(i).getButton(),current_button)) {
				return allProducts.get(i);
			}
		}
		return allProducts.get(0); // need to change this
	}

	static void setReturnedFromSecondaryController(boolean retFromSecond) {
		returnedFromSecondaryController = retFromSecond;
	}

	static boolean getReturnedFromSecondaryController(){
		return returnedFromSecondaryController ;
	}
}
