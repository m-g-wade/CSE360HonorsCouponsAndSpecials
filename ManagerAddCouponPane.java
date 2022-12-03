/*
 * ASU CSE 360: Intro to Software Engineering Fall 2022
 * Honors Project: Coupons and Specials
 * 
 * Date File Created: 11/9/2022
 * Date Last Edited: 11/9/2022
 * 
 * Margaret Wade
 * 
 * TODO: Description
 */

package application;

import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import java.time.LocalDate;
import java.util.*;
import javafx.stage.*;


public class ManagerAddCouponPane extends HBox{
	CouponCode coupon;
	
	//Specify Coupon VBox (Left Half)
	VBox couponSpecifyVBox;
	
	//Type Specification
	HBox typeHBox;
	Label typeLabel;
	
	ToggleGroup typeToggleGroup;
	RadioButton applyOne;
	RadioButton applyAll;
	
	//Date Specification	
	GridPane dateGridPane;
	
	Label startDateLabel;
	
	Label startDayLabel;
	Label startMonthLabel;
	Label startYearLabel;
	
	ComboBox<Integer> startDayCombo;
	ComboBox<Integer> startMonthCombo;
	ComboBox<Integer> startYearCombo;
	
	Label endDateLabel;
	
	Label endDayLabel;
	Label endMonthLabel;
	Label endYearLabel;
	
	ComboBox<Integer> endDayCombo;
	ComboBox<Integer> endMonthCombo;
	ComboBox<Integer> endYearCombo;
	
	Label dateErrorMessage;
	
	LocalDate startDate;
	LocalDate endDate;
	
	//Discount Percentage and Coupon Text Specification
	HBox discountHBox;
	
	Label discountLabel;
	TextField discountTF;
	Label discountErrorMessage;
	
	HBox couponCodeHBox;
	
	Label couponCodeLabel;
	TextField couponCodeTF;
	
	//Summary VBox (Right Half)
	VBox couponSumVBox;
	
	Label couponSumLabel;
	TextArea couponSumTextArea;
	Button confirmButton;
	
	
	ManagerAddCouponPane()
	{
		startDate = LocalDate.now();
		endDate = LocalDate.now();
		
		coupon = new CouponCode("Apply to one item", startDate, endDate, 0, "", "Active");
		
		//Coupon Specify Pane (Left Half)
		typeLabel = new Label("Coupon Code Type:");
		
		//Type RadioButton ToggleGroup
		typeToggleGroup = new ToggleGroup();
		
		applyOne = new RadioButton("Apply to one item");
		applyOne.setToggleGroup(typeToggleGroup);
		applyOne.setSelected(true);
		
		applyAll = new RadioButton("Apply to whole order");
		applyAll.setToggleGroup(typeToggleGroup);	
		
		//bind RadioButtons to Handler
		applyOne.setOnAction(new ManagerAddCouponHandler());
		applyAll.setOnAction(new ManagerAddCouponHandler());
		
		typeHBox = new HBox();
		typeHBox.getChildren().addAll(typeLabel, applyOne, applyAll);
		typeHBox.setSpacing(5);
		typeHBox.setPadding(new Insets(2, 2, 2, 2));
		
		//Start Date Select
		startDateLabel = new Label("Start Date: ");
		
		startDayLabel = new Label("Day");
		startMonthLabel = new Label("Month");
		startYearLabel = new Label("Year");
		
		startDayCombo = new ComboBox<Integer>();
		startMonthCombo = new ComboBox<Integer>();
		startYearCombo = new ComboBox<Integer>();
		
		//End Date Select
		endDateLabel = new Label("End Date: ");
		
		endDayLabel = new Label("Day");
		endMonthLabel = new Label("Month");
		endYearLabel = new Label("Year");
		
		endDayCombo = new ComboBox<Integer>();
		endMonthCombo = new ComboBox<Integer>();
		endYearCombo = new ComboBox<Integer>();
		
		for(int i = 1; i <= 31; i++)
		{
			startDayCombo.getItems().add(i);
			endDayCombo.getItems().add(i);
			
			if(i <= 12)
			{
				startMonthCombo.getItems().add(i);
				endMonthCombo.getItems().add(i);
				startYearCombo.getItems().add(LocalDate.now().getYear() + i - 1);
				endYearCombo.getItems().add(LocalDate.now().getYear() + i - 1);
			}
		}
		
		startDayCombo.getSelectionModel().select(LocalDate.now().getDayOfMonth() - 1);
		startMonthCombo.getSelectionModel().select(LocalDate.now().getMonthValue() - 1);
		startYearCombo.getSelectionModel().select(0);
		
		endDayCombo.getSelectionModel().select(LocalDate.now().getDayOfMonth() - 1);
		endMonthCombo.getSelectionModel().select(LocalDate.now().getMonthValue() - 1);
		endYearCombo.getSelectionModel().select(0);
		
		dateErrorMessage = new Label("");
		
		for(int i = 1; i <= 31; i++)
		{
			startDayCombo.getItems().add(i);
			endDayCombo.getItems().add(i);
			
			if(i <= 12)
			{
				startMonthCombo.getItems().add(i);
				endMonthCombo.getItems().add(i);
				startYearCombo.getItems().add(LocalDate.now().getYear() + i - 1);
				endYearCombo.getItems().add(LocalDate.now().getYear() + i - 1);
			}
		}
		
		
		//Bind Date ComboBoxes to Handler
		startDayCombo.setOnAction(new DateHandler());
		startMonthCombo.setOnAction(new DateHandler());
		startYearCombo.setOnAction(new DateHandler());
		
		endDayCombo.setOnAction(new DateHandler());
		endMonthCombo.setOnAction(new DateHandler());
		endYearCombo.setOnAction(new DateHandler());
		
		
		//Date Grid Pane
		dateGridPane = new GridPane();
		
		dateGridPane.add(startDateLabel, 0, 1);
		dateGridPane.add(startDayLabel, 1, 0);
		dateGridPane.add(startMonthLabel, 2, 0);
		dateGridPane.add(startYearLabel, 3, 0);
		dateGridPane.add(startDayCombo, 1, 1);
		dateGridPane.add(startMonthCombo, 2, 1);
		dateGridPane.add(startYearCombo, 3, 1);
		
		dateGridPane.add(endDateLabel, 0, 3);
		dateGridPane.add(endDayLabel, 1, 2);
		dateGridPane.add(endMonthLabel, 2, 2);
		dateGridPane.add(endYearLabel, 3, 2);
		dateGridPane.add(endDayCombo, 1, 3);
		dateGridPane.add(endMonthCombo, 2, 3);
		dateGridPane.add(endYearCombo, 3, 3);
		
		dateGridPane.setHgap(5);
		dateGridPane.setVgap(5);
		
		//Discount Percentage and Coupon Text
	    discountLabel = new Label("Discount Percentage: ");
		discountTF = new TextField();
		discountErrorMessage = new Label("");
		
		couponCodeLabel = new Label("Coupon Code: ");
		couponCodeTF = new TextField();
		
		//Bind Discount Percentage and Coupon Text to Handler
		discountTF.setOnAction(new ManagerAddCouponHandler());
		couponCodeTF.setOnAction(new ManagerAddCouponHandler());
		
		discountHBox = new HBox();
		discountHBox.getChildren().addAll(discountLabel, discountTF);
		
		couponCodeHBox = new HBox();
		couponCodeHBox.getChildren().addAll(couponCodeLabel, couponCodeTF);
		
		couponSpecifyVBox = new VBox();
		couponSpecifyVBox.getChildren().addAll(typeHBox, dateGridPane, dateErrorMessage, discountHBox, discountErrorMessage, couponCodeHBox);
		
		//Summary VBox (Right Half)
		couponSumVBox = new VBox();
		
		couponSumLabel = new Label("Coupon Summary");
		couponSumTextArea = new TextArea(coupon.toString());
		couponSumTextArea.setEditable(false);
		confirmButton = new Button("Confirm and Add");
		
		confirmButton.setOnAction(new ManagerAddCouponHandler());
		
		couponSumVBox.getChildren().addAll(couponSumLabel, couponSumTextArea, confirmButton);
		
		this.getChildren().addAll(couponSpecifyVBox, couponSumVBox);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);
		this.setPadding(new Insets(15, 2, 2, 2));
		
	}//ManagerAddCouponPane()
	
	public class ManagerAddCouponHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			//Radio Button
			if(event.getSource().equals(applyOne) || event.getSource().equals(applyAll))
			{
				RadioButton radioButton = (RadioButton)event.getSource();
				coupon.setType(radioButton.getText());
				couponSumTextArea.setText(coupon.toString());
			}
			
			//Discount Percentage
			else if(event.getSource().equals(discountTF))
			{
				try {
					int discountNum = Integer.parseInt(discountTF.getText());
					if(discountNum < 101 && discountNum > 0)
					{
						coupon.setDiscountPercentage(discountNum);
						couponSumTextArea.setText(coupon.toString());
					}
					else
					{
						discountErrorMessage.setText("Discount must be between 1-100%");
					}
				}
				catch(NumberFormatException e)
				{
					discountErrorMessage.setText("Discount percentage must be entered as an integer");
				}
			}
			
			//CouponCode
			else if(event.getSource().equals(couponCodeTF))
			{
				coupon.setCouponText(couponCodeTF.getText());
				couponSumTextArea.setText(coupon.toString());
			}
			
			//ConfirmButton
			else if(event.getSource().equals(confirmButton))
			{
				try {
					if(LocalDate.now().compareTo(coupon.getStartDate()) < 0)
						coupon.setStatus("To Be Active");
					else
						coupon.setStatus("Active");
					
					CouponCode newCoupon = new CouponCode(coupon.getType(), coupon.getStartDate(), coupon.getEndDate(), coupon.getDiscountPercentage(), coupon.getCouponText(), coupon.getStatus());
					
					if(coupon.getDiscountPercentage() != 0 && !coupon.getCouponText().equals(""))
					{
						SpecialsAndCouponLog.addCoupon(newCoupon);
						couponSumTextArea.setText(coupon.toString() + "\n\nCoupon Successfully Added");
						coupon = new CouponCode();
					}
					else
						discountErrorMessage.setText("Values must be set for discount percentage and coupon code text");
				}
				catch(Exception e)
				{
					discountErrorMessage.setText("Error adding new Coupon Code to system");
				}
			}
			
		}//handle()
		
	}//ManagerAddCouponHandler
	
	public class DateHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent event)
		{
			//StartDate
			if(event.getSource().equals(startYearCombo) || event.getSource().equals(startMonthCombo) || event.getSource().equals(startDayCombo))
			{
				if(LocalDate.of(startYearCombo.getValue(), startMonthCombo.getValue(), startDayCombo.getValue()).compareTo(LocalDate.now()) < 0)
					dateErrorMessage.setText("Start date cannot be before current date");
				else
					coupon.setStartDate(LocalDate.of(startYearCombo.getValue(), startMonthCombo.getValue(), startDayCombo.getValue()));
			}
			
			//EndDate
			else if(event.getSource().equals(endYearCombo) || event.getSource().equals(endMonthCombo) || event.getSource().equals(endDayCombo))
			{
				if(LocalDate.of(endYearCombo.getValue(), endMonthCombo.getValue(), endDayCombo.getValue()).compareTo(coupon.getStartDate()) < 0)
					dateErrorMessage.setText("End date must be after start date");
				else if (LocalDate.of(endYearCombo.getValue(), endMonthCombo.getValue(), endDayCombo.getValue()).compareTo(LocalDate.now()) < 0)
					dateErrorMessage.setText("End date cannot be before the current date");
				else
					coupon.setEndDate(LocalDate.of(endYearCombo.getValue(), endMonthCombo.getValue(), endDayCombo.getValue()));
			}
			
			couponSumTextArea.setText(coupon.toString());
			
		}//handle()
		
	}//DateHandler
	
}//ManagerAddCouponPane
