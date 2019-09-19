package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.Services.DepartmentService;


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
		loadViewDepartment("/gui/DepartmentList.fxml");	
		}
	@FXML
	public void onMenuItemAbouotAction() {
	loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

	private synchronized void loadView(String absoluteName) { //synchronized garante que n�ov� ser perder na fila de execu��o
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVbox = loader.load();
		Scene  mainScene = Main.getMainScene();
		VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
		// Utiliza a hierarquia da Cena para chegar at� o Vbox em seguida seus filhos para poder limpar e substituir pelo conteudo da AboutView
		Node mainMenu = mainVBox.getChildren().get(0);
		mainVBox.getChildren().clear();
		mainVBox.getChildren().add(mainMenu);
		mainVBox.getChildren().addAll(newVbox.getChildren());
	}catch(IOException e) {
		Alerts.showAlert("IO Exception", null, e.getMessage(), AlertType.ERROR);
	}
	}
		private synchronized void loadViewDepartment(String absoluteName) { //synchronized garante que n�ov� ser perder na fila de execu��o
			try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();
			Scene  mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			// Utiliza a hierarquia da Cena para chegar at� o Vbox em seguida seus filhos para poder limpar e substituir pelo conteudo da AboutView
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());
			
			// igual ao anterior, mas agora com a implementa��o do povoamento da tabela
			DepartmentListController controller = loader.getController();
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
			
		}catch(IOException e) {
			Alerts.showAlert("IO Exception", null, e.getMessage(), AlertType.ERROR);
		}
		
		}
}
