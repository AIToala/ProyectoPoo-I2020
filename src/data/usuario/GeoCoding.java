/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.usuario;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
public class GeoCoding { 
        
    @RequestMapping(path = "/geocode", method = RequestMethod.GET )
    public static String[] getGeocode(@RequestParam String address) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        Request request = new Request.Builder()
                .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=es&address=" + encodedAddress)
                .get()
                .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "24a671bb00mshbaa48e755d8535dp188662jsn091334f46ad0")
                .build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        String resultado = responseBody.string();
       
        String status = resultado.substring(resultado.indexOf("\"status\" :"), resultado.indexOf("\"status\" :")+15);
        
        if (status.equals("\"status\" : \"OK\"")){
       
            int indexLat = resultado.indexOf("\"location\" : {\n               \"lat\" : ")+38;
            int indexLng = resultado.indexOf("\"lng\" : ", indexLat)+8;
            int indexClose = resultado.indexOf("\n            }", indexLng);
            String lat = resultado.substring(indexLat, indexLng-25);
            String lng = resultado.substring(indexLng, indexClose);
            
            String[] output = {lat, lng};
            return output;
        } return null;
    }

}