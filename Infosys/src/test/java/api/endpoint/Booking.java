package api.endpoint;
import static io.restassured.RestAssured.given;

import api.payload.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created for perform Create,Read, Update,Delete 

public class Booking 
{
 public static Response CreateBooking(Booking payload)
 {
	 Response responce=given()
	 .contentType(ContentType.JSON)
	 .accept(ContentType.JSON)
	 .body(payload)
     .when()	
     .post(Routes.post_url);
	 
	 return responce;

	}

 public static Response readBooking(String userName)
 {
	 Response responce=given()
	.pathParam("userName", userName)
     .when()	
     .get(Routes.get_url);
	 
	 return responce;

	}
 
 public static Response updateBooking(String username ,Booking payload)
 {
	 Response responce=given()
	 .contentType(ContentType.JSON)
	 .accept(ContentType.JSON)
	 .pathParam("username", username)
	 .body(payload)
     .when()	
     .put(Routes.update_url);
	 
	 return responce;

	}
 public static Response deleteBooking(String userName)
 {
	 Response responce=given()
	.pathParam("userName", userName)
     .when()	
     .delete(Routes.delete_url);
	 
	 return responce;

	}
 
}
