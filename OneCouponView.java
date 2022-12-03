package application;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class OneCouponView extends HBox
{
	CheckBox couponCheckBox;
	Label status;
	
	CouponCode coupon;
	
	public OneCouponView()
	{
		couponCheckBox = new CheckBox();
		status = new Label();
		
		coupon = new CouponCode();
	}//oneCouponView()
	
	public OneCouponView(CouponCode coupon)
	{
		this.coupon = coupon;
		
		couponCheckBox = new CheckBox();
		status = new Label(coupon.getStatus());
		
		couponCheckBox.setText(coupon.toString());
		
		this.getChildren().addAll(couponCheckBox, status);
		
	}//oneCouponView()
	
}//oneCouponView