package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Entities.Department;
import model.Services.DepartmentService;

public class DepartmentListController implements Initializable {

	private DepartmentService service;// deverá ser instanciado de forma desacoplada através do metodo
										// setDepartmentService para garantir desacoplamento e boa pratica

	@FXML
	TableView<Department> tableViewDepartment;
	@FXML
	TableColumn<Department, Integer> tableColumnId;
	@FXML
	TableColumn<Department, Integer> tableColumnName;
	@FXML
	private Button btNew;

	private ObservableList<Department> obsList;// esse objeto é que vai receber a lista para carregar a
												// tableViewdepartment e será povoado no método UpdateTableView

	@FXML
	public void onBtNewAction() {
		System.out.println("botão novo clicado");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	public void setDepartmentService(DepartmentService service) {// aqui será instanciado o objeto service para esta
																	// classe
		this.service = service;
	}

	private void initializeNodes() {
		// para iniciar os campos
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Ajuste tela
		// para fazer com que o tamanho da tabela seja do tamanho da tela
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service nulo");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList);
		// para que os valores sejam carregados será preciso alterar a Main incluindo um
		// metodo de carregamento
	}

}
