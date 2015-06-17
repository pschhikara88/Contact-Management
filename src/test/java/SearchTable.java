import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


public class SearchTable extends Application {

	/**
	 * @param args
	 */

		private TableView<Person> table = new TableView<Person>();
	    private final ObservableList<Person> data =
	            FXCollections.observableArrayList(
	            new Person("Jacob", "Smith", "jacob.smith@example.com"),
	            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
	            new Person("Ethan", "Williams", "ethan.williams@example.com"),
	            new Person("Emma", "Jones", "emma.jones@example.com"),
	            new Person("Michael", "Brown", "michael.brown@example.com"));
	    
	    private ObservableList<Person> filteredData = FXCollections.observableArrayList(); 
	    
	    final HBox hb = new HBox();
	 
	    public static void main(String[] args) {
	        launch(args);
	    }
	 
	    @Override
	    public void start(Stage stage) {
	    	filteredData.addAll(data);

	        Scene scene = new Scene(new Group());
	        stage.setTitle("Table View Sample");
	        stage.setWidth(450);
	        stage.setHeight(550);
	 
	       /* final Label label = new Label("Address Book");
	        label.setFont(new Font("Arial", 20));
	 */
	        HBox hbox = new HBox();
		    //hbox.setPadding(new Insets(5,5, 5, 650));
		    hbox.setAlignment(Pos.CENTER_LEFT); 
		    hbox.setSpacing(10);
		    hbox.setPrefHeight(30);
		    hbox.setStyle("-fx-background-color: #336699;");
		    //Label zipCodeLabel = new Label("Zip Code");
	    	
	    	TextField searchText1 = new TextField();
	    	searchText1.setFocusTraversable(false);
	    	searchText1.setPromptText("Search");
	    	searchText1.setStyle("-fx-focus-color: transparent;");
	    	searchText1.textProperty().addListener(new ChangeListener<String>() {
	    	    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
	    	        
	    	    	if(newValue!=null && !newValue.trim().equals(""))
	    	    	{	Person[] personList=new Person[0];
	    	        	personList= data.toArray(personList);
	    	        	if(filteredData.size()>4)
	    	        		filteredData.remove(3);
	    	        	table.setItems(filteredData);
	    	    	}
	    	    	else
	    	    	{
	    	    		table.setItems(data);
	    	    	}
	    	    }
	    	});
	    	//searchText.setAlignment(Pos.BASELINE_CENTER);
	    	//addressLine2Text.setPrefColumnCount(5);
	    	//GridPane.setColumnSpan(zipCodeText, 2);
		   /* Button buttonCurrent = new Button("Current");
		    buttonCurrent.setPrefSize(100, 20);

		    Button buttonProjected = new Button("Projected");
		    buttonProjected.setPrefSize(100, 20);*/
		    hbox.getChildren().addAll(searchText1);
	        table.setEditable(false);
	 
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
	        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol,col_action);
	 
	        final TextField addFirstName = new TextField();
	        addFirstName.setPromptText("First Name");
	        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
	        final TextField addLastName = new TextField();
	        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
	        addLastName.setPromptText("Last Name");
	        final TextField addEmail = new TextField();
	        addEmail.setMaxWidth(emailCol.getPrefWidth());
	        addEmail.setPromptText("Email");
	 
	        final Button addButton = new Button("Add");
	        addButton.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent e) {
	                data.add(new Person(
	                        addFirstName.getText(),
	                        addLastName.getText(),
	                        addEmail.getText()));
	                addFirstName.clear();
	                addLastName.clear();
	                addEmail.clear();
	            }
	        });
	 
	        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
	        hb.setSpacing(3);
	 
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll( hbox , table);
	 
	        ((Group) scene.getRoot()).getChildren().addAll(vbox);
	 
	        stage.setScene(scene);
	        stage.show();
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


	}


