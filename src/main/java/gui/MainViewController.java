package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import aplication.Main;
import gui.util.Alerts;
import gui.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.services.ThreadSendLog;

public class MainViewController implements Initializable {

	public static boolean statusThread = false;

	private ThreadSendLog sendLog;

	@FXML
	private TextField txtIpServer;

	@FXML
	private TextField txtTimeout;

	@FXML
	private Button btConectar;

	@FXML
	private Button btDesconectar;

	@FXML
	public void onBtConectarAction(ActionEvent event) {
		Long timeout = Long.parseLong(txtTimeout.getText());
		String ipServer = txtIpServer.getText();

		sendLog = new ThreadSendLog(Main.getArgsSigar(), timeout, ipServer);
		sendLog.start();
		
		try {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Aguarde!");
			alert.setContentText("Procurando servidor ...");
			alert.show();
			TimeUnit.MILLISECONDS.sleep(3500);
			alert.close();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
		if (statusThread == true) {
			Alerts.showAlert("Status", null, "Cliente conectado com sucesso!", AlertType.INFORMATION);
			btConectar.setDisable(true);
			txtTimeout.setDisable(true);
			txtIpServer.setDisable(true);
			btDesconectar.setDisable(false);
		}
	}

	@FXML
	public void onBtDesconectarAction(ActionEvent event) {
		System.exit(0);
	}

	// System.exit(0);

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtTimeout);

	}

	protected synchronized <T> void loadView(String absoluteName, Consumer<T> initializeAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());

			T controller = loader.getController();
			initializeAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IOException", "Erro ao carregar a página", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

}
