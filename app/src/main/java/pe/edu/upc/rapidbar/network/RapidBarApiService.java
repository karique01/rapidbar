package pe.edu.upc.rapidbar.network;

public class RapidBarApiService {
    public static String BASE_URL = "http://52.15.243.101/";
    public static String PRODUCTS_BY_ORDER_URL = BASE_URL+"api/productsByOrder";
    public static String REGISTER_URL = BASE_URL+"api/register";
    public static String PRODUCTS_URL = BASE_URL + "api/products/";
    public static String PRODUCTS_DRINKS_URL = PRODUCTS_URL + "drinks";
    public static String PRODUCTS_SNACKS_URL = PRODUCTS_URL + "snacks";
    public static String BARS_URL = BASE_URL + "api/bars/";

    public static String CREDIT_CARDS_URL = BASE_URL +"api/creditCard/";
    public static String ORDERS_URL = BASE_URL + "api/order/";
    public static String ALL_ORDERS_URL = BASE_URL + "api/order/2";
    public static String LOGIN_URL = BASE_URL + "api/login";

    public static String ORDERS_DETAILS_URL(String idOrden){
        return BASE_URL + "api/order/"+idOrden+"/details";
    }
    public static String BAR_DRINKS_BY_ID(String idBar){
        return BARS_URL+idBar+"/products/drinks";
    }
    public static String BAR_SNACKS_BY_ID(String idBar){
        return BARS_URL+idBar+"/products/snacks";
    }
    public static String BAR_PRODUCTS_BY_ID(String idBar){
        return BARS_URL+idBar+"/products";
    }
}