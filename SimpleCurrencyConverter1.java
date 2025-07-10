// import java.util.Scanner;

// public class CurrencyConverter{

//     public static void main(String[] args) {

//         Scanner scanner = new Scanner(System.in);

//         System.out.println(x:"Welcome to Currency Converter");
//         System.out.println(x:"1. USD to Rupee");
//         System.out.println(x:"2. Rupee to USD");
//         System.out.println(x:"3. Euro to Rupee");
//         System.out.println(x:"Rupee to Euro");
//         System.out.print(s:"Enter your choice (1 or 2 or 3 or 4): ");
//         int choice = scanner.nextInt();

//         if(choice == 1) {
//             System.out.print("Enter the amount in USD");
//             double USD = scanner.nextDouble();
//             double  Rupee = usdToRupee
//         }

//     }
// }


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SimpleCurrencyConverter {

    private static final String API_URL = "https://open.er-api.com/v6/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Currency Converter ===");

        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            double rate = fetchExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * rate;
            System.out.printf("Converted Amount: %.2f %s\n", convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    private static double fetchExchangeRate(String base, String target) throws Exception {
        String urlStr = API_URL + base;
        URL url = new URL(urlStr);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        // Manually parse the rate for the target currency
        String search = "\"" + target + "\":";
        int index = response.indexOf(search);
        if (index == -1) {
            throw new Exception("Target currency not found.");
        }

        int start = index + search.length();
        int end = response.indexOf(",", start);
        if (end == -1) {
            end = response.indexOf("}", start);
        }

        String rateStr = response.substring(start, end).trim();
        return Double.parseDouble(rateStr);
    }
}
