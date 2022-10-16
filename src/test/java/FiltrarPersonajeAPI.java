import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FiltrarPersonajeAPI extends Base {

    int StatusCode = 200;

    // consumir la API y validar el status code

    @Test(priority = 1)
    public void validateStatusCode() {

        Response response = given().log().uri()
                .when().get("/api/character");
        Assert.assertEquals(response.getStatusCode(), StatusCode);
    }

    // Validar que se puedan filtrar personajes por name
    @Test
    public void filterWithName() {

        // data
        String nombre = "Rick";
        int actualPage = 1;
        int sum = 0;

        // get request a la API
        Response response = given().log().uri().queryParam("name", nombre)
                .when().get("/api/character");

        // Cantidad paginas y personajes en el response
        int countPages = response.jsonPath().getInt("info.pages");
        int countCharacters = response.jsonPath().getInt("info.count");
        System.out.println("La cantidad de paginas es: " + countPages);

        // Bucle para consultar todas las paginas y validar todos los personajes
        while (actualPage <= countPages) {
            Response createResponse = given().log().uri().queryParam("page", actualPage).queryParam("name", nombre)
                    .when().get("/api/character");

            ArrayList<String> filtroName = createResponse.path("results.name");

            for (int i = 0; i < filtroName.size(); i++) {

                Assert.assertTrue(filtroName.get(i).toLowerCase().contains(nombre.toLowerCase()),
                        "El personaje: " + filtroName.get(i) + " No Cumple la condición");

            }

            sum = sum + filtroName.size();
            actualPage++;
        }

        // Validacion tag Count
        Assert.assertEquals(countCharacters, sum, "El conteo de personajes no concuerda con el valor real");
        System.out.println("La cantidad de personajes fue: " + sum);

        //Se valida que funcione el git

    }

    // Validar que se puedan filtrar personajes por status
    @Test
    public void filterWithStatus() {

        // data
        String status = "alive";
        int actualPage = 1;
        int sum = 0;

        // get request a la API
        Response response = given().log().uri().queryParam("status", status)
                .when().get("/api/character");

        // Cantidad paginas y personajes en el response
        int countPages = response.jsonPath().getInt("info.pages");
        int countCharacters = response.jsonPath().getInt("info.count");
        System.out.println("La cantidad de paginas es: " + countPages);

        // Bucle para consultar todas las paginas y validar todos los personajes
        while (actualPage <= countPages) {
            Response createResponse = given().log().uri().queryParam("page", actualPage).queryParam("status", status)
                    .when().get("/api/character");

            ArrayList<String> filtroStatus = createResponse.path("results.status");

            for (int i = 0; i < filtroStatus.size(); i++) {

                Assert.assertTrue(filtroStatus.get(i).toLowerCase().contains(status.toLowerCase()),
                        "El personaje: " + filtroStatus.get(i) + " No Cumple la condición");

            }

            sum = sum + filtroStatus.size();
            actualPage++;
        }

        // Validacion tag Count
        Assert.assertEquals(countCharacters, sum, "El conteo de personajes no concuerda con el valor real");
        System.out.println("La cantidad de personajes fue: " + sum);

    }

    // Validar que se puedan filtrar personajes por species
    @Test
    public void filterWithSpecies() {

        // data
        String specie = "Alien";
        int actualPage = 1;
        int sum = 0;

        // get request a la API
        Response response = given().log().uri().queryParam("species", specie)
                .when().get("/api/character");

        // Cantidad paginas y personajes en el response
        int countPages = response.jsonPath().getInt("info.pages");
        int countCharacters = response.jsonPath().getInt("info.count");
        System.out.println("La cantidad de paginas es: " + countPages);

        // Bucle para consultar todas las paginas y validar todos los personajes
        while (actualPage <= countPages) {
            Response createResponse = given().log().uri().queryParam("page", actualPage).queryParam("species", specie)
                    .when().get("/api/character");

            ArrayList<String> filtroSpecie = createResponse.path("results.species");

            System.out.println("La cantidad de personajes es: " + filtroSpecie.size());

            for (int i = 0; i < filtroSpecie.size(); i++) {

                Assert.assertTrue(filtroSpecie.get(i).toLowerCase().contains(specie.toLowerCase()),
                        "El personaje: " + filtroSpecie.get(i) + " No Cumple la condición");

            }

            sum = sum + filtroSpecie.size();
            actualPage++;
        }

        // Validacion tag Count del response
        Assert.assertEquals(countCharacters, sum, "El conteo de personajes no concuerda con el valor real");
        System.out.println("La cantidad de personajes fue: " + sum);

    }

    // Validar que se puedan filtrar personajes por gender
    @Test
    public void filterWithGender() {

        // data
        String gender = "unknown";
        int actualPage = 1;
        int sum = 0;

        // get request a la API
        Response response = given().log().uri().queryParam("gender", gender)
                .when().get("/api/character");

        // Cantidad paginas y personajes en el response
        int countPages = response.jsonPath().getInt("info.pages");
        int countCharacters = response.jsonPath().getInt("info.count");
        System.out.println("La cantidad de paginas es: " + countPages);

        // Bucle para consultar todas las paginas y validar todos los personajes
        while (actualPage <= countPages) {
            Response createResponse = given().log().uri().queryParam("page", actualPage).queryParam("gender", gender)
                    .when().get("/api/character");

            ArrayList<String> filtroGender = createResponse.path("results.gender");

            for (int i = 0; i < filtroGender.size(); i++) {

                Assert.assertTrue(filtroGender.get(i).toLowerCase().contains(gender.toLowerCase()),
                        "El personaje: " + filtroGender.get(i) + " No Cumple la condición");

            }

            sum = sum + filtroGender.size();
            actualPage++;
        }

        // Validacion tag Count del response
        Assert.assertEquals(countCharacters, sum, "El conteo de personajes no concuerda con el valor real");
        System.out.println("La cantidad de personajes fue: " + sum);
    }

    // Realizar una consulta con varios filtros
   /* @Test
    public void allFilterRequest() {

        // data
        String name = "Rick";
        String status = "Alive";
        String species = "Human";
        String gender = "male";
        int actualPage = 1;

        // get request a la API
        Response response = given().log().uri()
                .queryParam("name", name)
                .queryParam("status", status)
                .queryParam("species", species)
                .queryParam("gender", gender)
                .when().get("/api/character");

        // Cantidad paginas y personajes en el response
        int countPages = response.jsonPath().getInt("info.pages");
        int countCharacters = response.jsonPath().getInt("info.count");
        System.out.println("La cantidad de paginas es: " + countPages);
        System.out.println("La cantidad de personajes es: " + countCharacters);

        // Bucle para consultar todas las paginas y validar todos los personajes

        while (actualPage <= countPages) {
            String jsonResponse = given().log().uri()
                    .queryParam("page", actualPage)
                    .queryParam("name", name)
                    .queryParam("status", status)
                    .queryParam("species", species)
                    .queryParam("gender", gender)
                    .when().get("/api/character")
                    .then().extract().response().asString();

            // System.out.println(jsonResponse);

            JsonPath js = new JsonPath(jsonResponse);

            List<String> lista = js.getList("results");
            System.out.println("El tamaño de la lista es: " + lista.size());
            // System.out.println("Los elementos de la lista son: " + lista);

            for (int i = 0; i < lista.size(); i++) {

                System.out.println("El nombre es" + i + ": " + js.get("results.name"));
                Assert.assertTrue(js.get("results.name".toLowerCase().contains(name.toLowerCase())),,
                        "El personaje: " + js.get("results.name") + "No Cumple la condición");

            }

            actualPage++;

        }

        // Validacion tag Count del response
        /*
         * Assert.assertEquals(countCharacters, sum,
         * "El conteo de personajes no concuerda con el valor real");
         * System.out.println("La cantidad de personajes fue: " + sum);
         */

    }
