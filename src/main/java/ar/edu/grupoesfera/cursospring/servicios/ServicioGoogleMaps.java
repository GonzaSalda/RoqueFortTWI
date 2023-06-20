package ar.edu.grupoesfera.cursospring.servicios;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import java.io.IOException;

public class ServicioGoogleMaps {

    public String GoogleMapsAPIConfiguration(String direccion) throws InterruptedException, ApiException, IOException {
        try {
            // Crea el contexto de la API de Google Maps con tu clave de API
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyAgPKYfbfkbP1nYnUMZO4LPV4neuoYOyrY")
                    .build();

            // Realiza la geocodificación inversa para obtener la información geográfica
            GeocodingResult[] results = GeocodingApi.geocode(context, direccion).await();

            // Verifica si se encontraron resultados
            if (results.length > 0) {
                // Obtén la información geográfica deseada, como latitud y longitud
                String latitud = String.valueOf(results[0].geometry.location.lat);
                String longitud = String.valueOf(results[0].geometry.location.lng);

                // Realiza la llamada a la API de distancia de Google Maps para obtener el tiempo de viaje
                DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context)
                        .origins("San Justo, Buenos Aires, AR")
                        .destinations(direccion)
                        .await();

                // Verifica si se encontraron resultados en la matriz de distancias
                if (distanceMatrix.rows.length > 0) {
                    DistanceMatrixRow row = distanceMatrix.rows[0];
                    DistanceMatrixElement element = row.elements[0];

                    // Obtén el tiempo estimado de viaje en segundos
                    long tiempoViajeSegundos = element.duration.inSeconds;

                    // Convierte el tiempo a minutos
                    long tiempoViajeMinutos = Long.parseLong(String.valueOf(tiempoViajeSegundos / 60));

                    return "Tiempo estimado de entrega: " + tiempoViajeMinutos + " minutos.";
                } else {
                    return "No se encontraron resultados para la dirección proporcionada.";
                }
            } else {
                return "No se encontraron resultados para la dirección proporcionada.";
            }
        } catch (Exception e) {
            return "Ocurrió un error al obtener la información geográfica: " + e.getMessage();
        }
    }



    public long obtenerTiempoViajeMinutos(String direccion) throws InterruptedException, ApiException, IOException {
        try {
            // Crea el contexto de la API de Google Maps con tu clave de API
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyAgPKYfbfkbP1nYnUMZO4LPV4neuoYOyrY")
                    .build();

            // Realiza la llamada a la API de distancia de Google Maps para obtener el tiempo de viaje
            DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context)
                    .origins("San Justo, Buenos Aires, AR")
                    .destinations(direccion)
                    .await();

            // Verifica si se encontraron resultados en la matriz de distancias
            if (distanceMatrix.rows.length > 0) {
                DistanceMatrixRow row = distanceMatrix.rows[0];
                DistanceMatrixElement element = row.elements[0];

                // Obtén el tiempo estimado de viaje en segundos
                long tiempoViajeSegundos = element.duration.inSeconds;

                // Convierte el tiempo a minutos
                return Long.parseLong(String.valueOf(tiempoViajeSegundos / 60));
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }
}
