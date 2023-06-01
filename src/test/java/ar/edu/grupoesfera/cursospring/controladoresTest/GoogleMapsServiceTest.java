package ar.edu.grupoesfera.cursospring.controladoresTest;



        import com.google.maps.DistanceMatrixApi;
        import com.google.maps.GeoApiContext;
        import com.google.maps.errors.ApiException;
        import com.google.maps.model.DistanceMatrix;
        import com.google.maps.model.TravelMode;
        import org.junit.Test;

        import java.io.IOException;

        import static org.junit.Assert.assertNotNull;

public class GoogleMapsServiceTest {
    @Test
    public void testGoogleMapsAPIConfiguration() throws InterruptedException, ApiException, IOException {
// Configura tu clave de API de Google Maps
        String apiKey = "AIzaSyAgPKYfbfkbP1nYnUMZO4LPV4neuoYOyrY";

// Crea el contexto de la API de Google Maps
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

// Realiza una llamada de prueba a la API de Google Maps
        DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context)
                .origins("New York, NY")
                .destinations("Los Angeles, CA")
                .mode(TravelMode.DRIVING)
                .await();

// Verifica si la respuesta de la API es no nula
        assertNotNull(distanceMatrix);
    }

    @Test
    public void testGoogleMapsAPIConfiguration2() throws InterruptedException, ApiException, IOException {
// Configura tu clave de API de Google Maps
        String apiKey = "AIzaSyAgPKYfbfkbP1nYnUMZO4LPV4neuoYOyrY";

// Crea el contexto de la API de Google Maps
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

// Realiza una llamada de prueba a la API de Google Maps
        DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context)
                .origins("New York, NY")
                .destinations("Los Angeles, CA")
                .mode(TravelMode.DRIVING)
                .await();

// Verifica si la respuesta de la API es no nula
        assertNotNull(distanceMatrix);

// Obtiene el tiempo estimado de llegada en segundos
        long durationInSeconds = distanceMatrix.rows[0].elements[0].duration.inSeconds;

// Calcula el tiempo en horas, minutos y segundos
        long hours = durationInSeconds / 3600;
        long minutes = (durationInSeconds % 3600) / 60;
        long seconds = durationInSeconds % 60;

// Muestra el tiempo estimado de llegada
        System.out.println("Tiempo estimado de llegada: " + hours + " horas, " + minutes + " minutos, " + seconds + " segundos.");
    }

}