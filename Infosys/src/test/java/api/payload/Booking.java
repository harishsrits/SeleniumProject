package api.payload;

import api.test.Responce;
import io.restassured.response.Response;

public class Booking {
	
	String username;
	String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static Response createBooking(Booking userPayload) {
		// TODO Auto-generated method stub
		return null;
	}
	public static Response updateBooking(String username2, Booking userPayload) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
