package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller ) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});	
		}
	@FXML
	public void onMenuItemAbouotAction() {
	loadView("/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

	private synchronized <T>void loadView(String absoluteName, Consumer <T> initializingAction ) { //synchronized garante que nãová ser perder na fila de execução
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVbox = loader.load();
		Scene  mainScene = Main.getMainScene();
		VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
		// Utiliza a hierarquia da Cena para chegar até o Vbox em seguida 
		//seus filhos para poder limpar e substituir pelo conteudo da AboutView
		Node mainMenu = mainVBox.getChildren().get(0);
		mainVBox.getChildren().clear();
		mainVBox.getChildren().add(mainMenu);
		mainVBox.getChildren().addAll(newVbox.getChildren());
		//Essas linhas são responsáveis por implementar o comando passado na função
		T controller = loader.getController();
		initializingAction.accept(controller);
	}catch(IOException e) {
		Alerts.showAlert("IO Exception", null, e.getMessage(), AlertType.ERROR);
	}
	}
	
}
