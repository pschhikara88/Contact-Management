
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;


public class RecentDemo extends Application{
	
	private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com"));
    
    private ObservableList<Person> filteredData = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Group root = new Group();
		Scene scene = new Scene(root, 850, 950);
		
		
		

    	//borderpane.setTop(tp);
    	//root.getChildren().add(borderpane);
    	//root.getChildren().add(addHBox());
		TitledPane tp = new TitledPane();
		//hbox.setPadding(new Insets(15, 12, 15, 12));
		//tp.minWidth(800);
		//tp.setLayoutX(100);
		//tp.sceneToLocal(200, 300);
		//tp.setText("ADD CONTACT                                                                                                            " +
		//		"                                            " +
		//		"                    ");
		tp.prefWidthProperty().bind(scene.widthProperty());
		tp.setText("Recent Contact");
		//tp.setCollapsible(false);
		table.setEditable(false);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		 TableColumn col_performed = new TableColumn("Action Performed");
		 col_performed.setSortable(true);
	        
		 col_performed.setCellValueFactory(
	                new Callback<TableColumn.CellDataFeatures<Person, Boolean>, 
	                ObservableValue<Boolean>>() {
	 
	            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Person, Boolean> p) {
	                return new SimpleBooleanProperty(p.getValue() != null);
	            }
	        });
	 
		 col_performed.setCellFactory(
	                new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {

	            public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
	                //if(p.getUserData())
	            	return new ActionPerformedCell();
	            }
	         
	        });
	        
		 TableColumn firstNameCol = new TableColumn("First Name");
	        firstNameCol.setMinWidth(100);
	        firstNameCol.setCellValueFactory(
	                new PropertyValueFactory<Person, String>("firstName"));
	 
	        TableColumn lastNameCol = new TableColumn("Last Name");
	        lastNameCol.setMinWidth(100);
	        lastNameCol.setCellValueFactory(
	                new PropertyValueFactory<Person, String>("lastName"));
	 
	        TableColumn emailCol = new TableColumn("Email");
	        emailCol.setMinWidth(200);
	        emailCol.setCellValueFactory(
	                new PropertyValueFactory<Person, String>("email"));

	        TableColumn col_action = new TableColumn("Action");
	        col_action.setSortable(false);
	        
	        col_action.setCellValueFactory(
	                new Callback<TableColumn.CellDataFeatures<Person, Boolean>, 
	                ObservableValue<Boolean>>() {
	 
	            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Person, Boolean> p) {
	                return new SimpleBooleanProperty(p.getValue() != null);
	            }
	        });
	 
	        col_action.setCellFactory(
	                new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {

	            public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
	                return new ActionCell();
	            }
	         
	        });
	        
	        table.setItems(data);
	        table.prefWidthProperty().bind(scene.widthProperty());
	        table.getColumns().addAll(col_performed,firstNameCol, lastNameCol, emailCol,col_action);  
	        tp.setContent(table);
	        ((Group) scene.getRoot()).getChildren().addAll(tp);
    	primaryStage.setScene(scene);
    	primaryStage.show();
	}
	
	public static void main(String args[])
	{
		Application.launch("rgdsgsdgsd");
	}
	
	 public static class Person {
		 
	        private final SimpleStringProperty firstName;
	        private final SimpleStringProperty lastName;
	        private final SimpleStringProperty email;
	 
	        private Person(String fName, String lName, String email) {
	            this.firstName = new SimpleStringProperty(fName);
	            this.lastName = new SimpleStringProperty(lName);
	            this.email = new SimpleStringProperty(email);
	        }
	 
	        public String getFirstName() {
	            return firstName.get();
	        }
	 
	        public void setFirstName(String fName) {
	            firstName.set(fName);
	        }
	 
	        public String getLastName() {
	            return lastName.get();
	        }
	 
	        public void setLastName(String fName) {
	            lastName.set(fName);
	        }
	 
	        public String getEmail() {
	            return email.get();
	        }
	 
	        public void setEmail(String fName) {
	            email.set(fName);
	        }
	    }
	    
	    
	    
	    private class ActionCell extends TableCell<Person,Boolean>
	    {
	    	private HBox hBox;
	    	private Button viewButton;
	    	private Button deleteButton;
	    	private Button editButton;
	    	
	    	public ActionCell()
	    	{
	    		hBox= new HBox();
	    		hBox.setSpacing(2);
	    		viewButton = new Button("View");
	    		deleteButton = new Button("Delete");
	    		viewButton.setOnAction(new EventHandler<ActionEvent>(){
	    			 
	                public void handle(ActionEvent t) {
	                    int selectdIndex = getTableRow().getIndex();
	                   // Person person = null;
	                   // filteredData.add(selectdIndex+1, person);

	                    //TableRow<Person> row = getTableView().getRowFactory().call(getTableView());
	                  
	                    
	                    //delete the selected item in data
	                  /*  Person person2 = data.get(selectdIndex);
	                    data.remove(selectdIndex);
	                    
	                    filteredData.remove(person2);*/
	                }
	            });
	    		deleteButton.setOnAction(new EventHandler<ActionEvent>(){
	    			 
	                public void handle(ActionEvent t) {
	                    int selectdIndex = getTableRow().getIndex();
	                    Person person = data.get(selectdIndex);
	                    //delete the selected item in data
	                    data.remove(selectdIndex);
	                    filteredData.remove(person);
	                }
	            });
	    		editButton = new Button("Edit");
	    		hBox.getChildren().addAll(viewButton,deleteButton,editButton);
	    		setGraphic(hBox);
	    	}
	    	
	    	 @Override
	         protected void updateItem(Boolean t, boolean empty) {
	             super.updateItem(t, empty);
	             if(empty){
	                 setGraphic(null);
	             }
	         }
	    	
	    	
	    }
	    
	    private class ActionPerformedCell extends TableCell<Person,Boolean>
	    {
	    	
	    	public ActionPerformedCell()
	    	{
	    		//Person person2 = data.get(selectdIndex);
	    		Person person = (Person)getUserData();
	    		Label actionLabel = new Label("Added");
	    		setGraphic(actionLabel);
	    	/*	hBox= new HBox();
	    		hBox.setSpacing(2);
	    		viewButton = new Button("View");
	    		deleteButton = new Button("Delete");
	    		viewButton.setOnAction(new EventHandler<ActionEvent>(){
	    			 
	                public void handle(ActionEvent t) {
	                    int selectdIndex = getTableRow().getIndex();
	                   // Person person = null;
	                   // filteredData.add(selectdIndex+1, person);

	                    //TableRow<Person> row = getTableView().getRowFactory().call(getTableView());
	                  
	                    
	                    //delete the selected item in data
	                    Person person2 = data.get(selectdIndex);
	                    data.remove(selectdIndex);
	                    
	                    filteredData.remove(person2);
	                }
	            });
	    		deleteButton.setOnAction(new EventHandler<ActionEvent>(){
	    			 
	                public void handle(ActionEvent t) {
	                    int selectdIndex = getTableRow().getIndex();
	                    Person person = data.get(selectdIndex);
	                    //delete the selected item in data
	                    data.remove(selectdIndex);
	                    filteredData.remove(person);
	                }
	            });
	    		editButton = new Button("Edit");
	    		hBox.getChildren().addAll(viewButton,deleteButton,editButton);
	    		setGraphic(hBox);*/
	    	}
	    	
	    	 @Override
	         protected void updateItem(Boolean t, boolean empty) {
	             super.updateItem(t, empty);
	             if(empty){
	                 setGraphic(null);
	             }
	         }
	    	
	    	
	    }

}
