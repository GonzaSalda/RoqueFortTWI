
<input class="w-96 mb-2" type="text" id="direccion-input" name="direccion" placeholder="Ingresa una dirección">
  <div id="map" class="w-5/6" style="height: 400px;"></div>

  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAgPKYfbfkbP1nYnUMZO4LPV4neuoYOyrY&libraries=places"></script>
  <script>
    function initMap() {
      var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 12
      });

      fetch('https://ipapi.co/json/')
        .then(response => response.json())
        .then(data => {
          var location = data.city + ', ' + data.region + ', ' + data.country;
          document.getElementById('direccion-input').value = location;

          var geocoder = new google.maps.Geocoder();
          geocoder.geocode({'address': location}, function(results, status) {
            if (status === 'OK') {
              map.setCenter(results[0].geometry.location);
              var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location
              });

              map.addListener('click', function(event) {
                geocoder.geocode({'location': event.latLng}, function(results, status) {
                  if (status === 'OK') {
                    if (results[0]) {
                      document.getElementById('direccion-input').value = results[0].formatted_address;
                      marker.setPosition(event.latLng);
                    } else {
                      alert('No se encontraron resultados');
                    }
                  } else {
                    alert('Geocoder falló debido a: ' + status);
                  }
                });
              });
            } else {
              alert('Geocode falló debido a: ' + status);
            }
          });
        })
        .catch(error => {
          console.error('Error al obtener la ubicación: ', error);
        });
    }

    google.maps.event.addDomListener(window, 'load', initMap);
  </script>