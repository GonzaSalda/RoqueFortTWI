<input class="text-black w-96 p-2 outline-0 rounded-2xl" type="text" id="direccion-input" name="direccion"
       placeholder="Ingresa una direccion"><br>
<div  id="map"  class="hidden" style="height: 400px;"></div>
<%--
--%>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAgPKYfbfkbP1nYnUMZO4LPV4neuoYOyrY&libraries=places"></script><script>
    function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 12
        });

        var input = document.getElementById('direccion-input');
        var autocomplete = new google.maps.places.Autocomplete(input);

        autocomplete.addListener('place_changed', function () {
            var place = autocomplete.getPlace();

            if (!place.geometry) {
                window.alert('No se encontraron detalles para el lugar ingresado');
                return;
            }

            if (place.geometry.viewport) {
                map.fitBounds(place.geometry.viewport);
            } else {
                map.setCenter(place.geometry.location);
                map.setZoom(17);
            }

            var marker = new google.maps.Marker({
                map: map,
                position: place.geometry.location
            });

            map.addListener('click', function (event) {
                var geocoder = new google.maps.Geocoder();
                geocoder.geocode({'location': event.latLng}, function (results, status) {
                    if (status === 'OK') {
                        if (results[0]) {
                            input.value = results[0].formatted_address;
                            marker.setPosition(event.latLng);
                        } else {
                            alert('No se encontraron resultados');
                        }
                    } else {
                        alert('Geocoder falló debido a: ' + status);
                    }
                });
            });
        });
    }

    document.addEventListener('DOMContentLoaded', initMap);
</script>

