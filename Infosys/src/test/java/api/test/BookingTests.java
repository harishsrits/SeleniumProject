package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.payload.Booking;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;
import net.bytebuddy.asm.Advice.This;

public class BookingTests {

	Faker faker;
	
	Booking userPayload;
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload=new Booking();
		
		userPayload.setUsername(faker.name().username());
		
		userPayload.setPassword(faker.internet().password(5,10));
		
	}
	@Test(priority=1)
	public void CreateBooking()
	{
		Response responce=Booking.createBooking(userPayload);
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(),200);
		
	}
	@Test(priority=2)
	public void GetBooking()
	{
		Response response=Booking.readUser(This.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	public void UpdateBooking()
	
	{
	   userPayload.setUsername(faker.name().Name());
		
		userPayload.setPassword(faker.internet().password(5,10));
		
		Response responce=Booking.updateBooking(this.userPayload.getUsername(),userPayload)
		((ValidatableResponseOptions<ValidatableResponse, Response>) responce.then().log()).statusCode(200);
		Assert.assertEquals(responce.getStatusCode(),200);
		
	}
	
	
}
