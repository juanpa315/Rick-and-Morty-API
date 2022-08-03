import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class Base {

    @BeforeClass
    public static void setUpRestAssured() {
        RestAssured.baseURI = "https://rickandmortyapi.com";
    }

}
