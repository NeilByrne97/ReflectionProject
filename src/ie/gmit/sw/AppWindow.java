package ie.gmit.sw;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javafx.application.*;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.*;

public class AppWindow extends Application {
	private ObservableList<Customer> customers; // The Model - a list of observers.
	private TableView<Customer> tv; // The View - a composite of GUI components
	private TextField txtFile; // A control, part of the View and a leaf node.

	@Override
	public void start(Stage stage) throws Exception { // This is a ***Template Method***
		CustomerFactory cf = CustomerFactory.getInstance(); // Get the singleton instance
		customers = cf.getCustomers(); // Get the Model

		stage.setTitle("GMIT - B.Sc. in Computing (Software Development)");
		stage.setWidth(800);
		stage.setHeight(600);
		stage.setOnCloseRequest((e) -> System.exit(0));

		VBox box = new VBox();
		box.setPadding(new Insets(10));
		box.setSpacing(8);

		// **Strategy Pattern**. Configure the Context with a Concrete Strategy
		Scene scene = new Scene(box);
		stage.setScene(scene);

		ToolBar toolBar = new ToolBar(); // A ToolBar is a composite node for Buttons (leaf nodes)

		Button btnQuit = new Button("Quit"); // A Leaf node
		btnQuit.setOnAction(e -> System.exit(0)); // Plant an observer on the button
		toolBar.getItems().add(btnQuit); // Add to the parent node and build the tree

		/*
		 * Add all the sub trees of nodes to the parent node and build the tree
		 */
		box.getChildren().add(getFileChooserPane(stage)); // Add the sub tree to the main tree
		box.getChildren().add(getTableView()); // Add the sub tree to the main tree
		box.getChildren().add(toolBar); // Add the sub tree to the main tree

		// Display the window
		stage.show();
		stage.centerOnScreen();
	}

	private TitledPane getFileChooserPane(Stage stage) throws FileNotFoundException, IOException {
		VBox panel = new VBox(); // ** A concrete strategy ***
		txtFile = new TextField(); // A leaf node

		FileChooser fc = new FileChooser(); // A leaf node
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JAR Files", "*.jar"));

		JarInputStream in = new JarInputStream(new FileInputStream(new File("mylib.jar")));
		JarEntry next = in.getNextJarEntry();
		
		while (next != null) {
			if (next.getName().endsWith(".class")) {
				String name = next.getName().replaceAll("/", "\\.");
				name = name.replaceAll(".class", "");
				if (!name.contains("$"))
					name.substring(0, name.length() - ".class".length());
				System.out.println(name);
			}
			next = in.getNextJarEntry();
		}

		Class reflectClass = Tester.class;
		String className = reflectClass.getName();
		System.out.println(className + "\n");
		
		Button btnOpen = new Button("Select File"); // A leaf node
		btnOpen.setOnAction(e -> { // Plant an observer on the button
			File f = fc.showOpenDialog(stage);
			txtFile.setText(f.getAbsolutePath());
		});

		Button btnProcess = new Button("Process"); // A leaf node
		btnProcess.setOnAction(e -> { // Plant an observer on the button
			File f = new File(txtFile.getText());
			System.out.println("[INFO] Processing file " + f.getName());

		});

		ToolBar tb = new ToolBar(); // A composite node
		tb.getItems().add(btnOpen); // Add to the parent node and build a sub tree
		tb.getItems().add(btnProcess); // Add to the parent node and build a sub tree

		panel.getChildren().add(txtFile); // Add to the parent node and build a sub tree
		panel.getChildren().add(tb); // Add to the parent node and build a sub tree

		TitledPane tp = new TitledPane("Select File to Process", panel); // Add to the parent node and build a sub tree
		tp.setCollapsible(false);
		return tp;
	}

	
	private TableView<Customer> getTableView() {
		
		tv = new TableView<>(customers); // A TableView is a composite node
		tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Stretch columns to fit the window

		 
		TableColumn<Customer, String> name = new TableColumn<>("Name");
		name.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Customer, String> p) {
				return new SimpleStringProperty(p.getValue().name());
			}
		});

		// Creates an observable table column from a String field extracted from the
		// Customer class
		TableColumn<Customer, String> dob = new TableColumn<>("DOB");
		dob.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Customer, String> p) {
				return new SimpleStringProperty(
						DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(p.getValue().dob()));
			}
		});

		// Creates an observable table column from a String field extracted from the
		// Customer class
		TableColumn<Customer, String> status = new TableColumn<>("Status");
		status.setCellValueFactory(new Callback<CellDataFeatures<Customer, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Customer, String> p) {
				return new SimpleStringProperty(p.getValue().status().toString());
			}
		});

		// Creates an observable table column from an Image attribute of the Customer
		// class
		TableColumn<Customer, ImageView> img = new TableColumn<>("Image");
		img.setCellValueFactory(new Callback<CellDataFeatures<Customer, ImageView>, ObservableValue<ImageView>>() {
			public ObservableValue<ImageView> call(CellDataFeatures<Customer, ImageView> p) {
				return new SimpleObjectProperty<>(new ImageView(p.getValue().image()));
			}
		});

		tv.getColumns().add(name); // Add nodes to the tree
		tv.getColumns().add(dob); // Add nodes to the tree
		tv.getColumns().add(status); // Add nodes to the tree
		tv.getColumns().add(img); // Add nodes to the tree
		return tv;
	}
}