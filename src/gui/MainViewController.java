package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable{
	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuDepartment;
	@FXML
	private MenuItem about;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("Menu seller");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		System.out.println("Menu Department");
	}
	@FXML
	public void onMenuItemAbouotAction() {
		System.out.println("Menu About");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

}