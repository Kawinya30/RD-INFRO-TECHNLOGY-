import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;  // Make sure to import a JSON library like org.json or Jackson

public class WeatherApp {
    private static final String API_KEY = "YOUR_API_KEY";  // Replace with your OpenWeatherMap API key
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";  // Metric for temperature in Celsius
    
    public static void main(String[] args) {
        try {
            String city = "London";  // Default city, change as needed
            if (args.length > 0) {
                city = args[0];  // If a city is provided as an argument
            }

            // Make the API call
            String jsonResponse = getWeatherData(city);
            
            // Parse the JSON response
            JSONObject weatherJson = new JSONObject(jsonResponse);
            
            // Extract required information
            String weatherDescription = weatherJson.getJSONArray("weather").getJSONObject(0).getString("description");
            double temperature = weatherJson.getJSONObject("main").getDouble("temp");
            double humidity = weatherJson.getJSONObject("main").getDouble("humidity");
            double windSpeed = weatherJson.getJSONObject("wind").getDouble("speed");
            
            // Print the results
            System.out.println("Weather for " + city + ":");
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Condition: " + weatherDescription);
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Wind Speed: " + windSpeed + " m/s");

        } catch (Exception e) {
            System.out.println("Error fetching weather data: " + e.getMessage());
        }
    }
    
    private static String getWeatherData(String city) throws Exception {
        // Build the URL to call OpenWeatherMap API
        String urlString = String.format(API_URL, city, API_KEY);
        URL url = new URL(urlString);
        
        // Make HTTP request
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        
        // Read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        
        // Return the response as a string
        return response.toString();
    }
}
