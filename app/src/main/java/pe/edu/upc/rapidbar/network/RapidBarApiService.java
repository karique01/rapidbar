package pe.edu.upc.rapidbar.network;

public class RapidBarApiService {
    public static String BASE_URL = "http://52.15.243.101/";
    public static String PRODUCTS_URL = BASE_URL + "api/products/";
    public static String BARS_URL = BASE_URL + "api/bars/";

    public static String CREDIT_CARDS_URL = BASE_URL + "api/creditCard/";
    public static String ORDERS_URL = BASE_URL + "api/order/";
    public static String ALL_ORDERS_URL = BASE_URL + "api/order/2";
    public static String LOGIN_URL = BASE_URL + "api/login";

    public static String ORDERS_DETAILS_URL(String idOrden){
        return BASE_URL + "api/order/"+idOrden+"/details";
    }
}