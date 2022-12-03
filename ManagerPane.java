package application;


import javafx.scene.layout.BorderPane;

public class ManagerPane extends BorderPane {

	private ManagerLoginPane loginPane;
	private ManagerPortalPane portalPane;
	
	public ManagerPane()
	{
		loginPane = new ManagerLoginPane();
		portalPane = new ManagerPortalPane();
		this.setCenter(loginPane);
	}
	
}//ManagerPane
	