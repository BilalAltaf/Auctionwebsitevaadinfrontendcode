package com;

/**
 * Created by bilal on 10/15/2016.
 */

import com.vaadin.server.VaadinSession;
import elemental.json.*;
import elemental.json.impl.JreJsonFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.*;

/**
 * Does the actual communication with the back-end. Data sent should be encrypted in some suitable way, also, we should
 * probably have some mechanism for ensuring that we're actually talking to the back-end and not someone else...
 */
public class HTTPService implements Service {

    //private final static String backendURL = "http://86.50.119.47:3000/api/";

    //private final static String backendURL = "http://192.168.0.110:3000/api/";
    private final static String backendURL = "http://127.0.0.1:8000/api/";
    private final JreJsonFactory factory = new JreJsonFactory();

    // TODO: Get rid of duplication

    private JsonObject jsonObjectRequest(String url, JsonObject data) throws ServiceException {
        Utils.printIfDebug("Request data: " + data.toString());

        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response;

        try {
            HttpPost request = new HttpPost(url);
            StringEntity json = new StringEntity(
                    data.toString(),
                    ContentType.APPLICATION_JSON
            );
            request.setEntity(json);
            User user = Utils.getCurrentUser();
            if (user != null) {
                request.addHeader("authorization", user.getToken());
            }
            response = client.execute(request);

            String responseString = EntityUtils.toString(response.getEntity());

            // Create JSON object from response
            JsonObject responseData = factory.parse(responseString);

            Utils.printIfDebug("Response data: " + responseData.toString());

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Check status code of JSON data as well
                int jsonStatusCode = (int) responseData.getNumber("statusCode");

                if (jsonStatusCode == 200) {
                    return responseData.getObject("data");
                } else if (jsonStatusCode == 201) {
                    return responseData.getObject("data");
                } else if (jsonStatusCode == 402) {
                    return responseData.getObject("data");
                } else if (jsonStatusCode == 400) {
                    return responseData.getObject("data");
                } else {
                    return null;
                }
            } else if (statusCode == 400) {
                int jsonStatusCode = (int) responseData.getNumber("statusCode");
                if (jsonStatusCode == 400) {
                    return responseData.getObject("data");
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) { // TODO: What kind of exceptions are possible?
            throw new ServiceException("Error when contacting back-end");
        }
    }

    private JsonObject jsonObjectRequestget(String url, JsonObject data) throws ServiceException {
        Utils.printIfDebug("Request data: " + data.toString());

        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response;

        try {
            HttpGet request = new HttpGet(url);
            // StringEntity json = new StringEntity(
            //       data.toString(),
            //       ContentType.APPLICATION_JSON
            //  );
            //request.setEntity(json);
            User user = Utils.getCurrentUser();
            if (user != null) {
                request.addHeader("authorization", user.getToken());
            }
            response = client.execute(request);

            String responseString = EntityUtils.toString(response.getEntity());

            // Create JSON object from response
            JsonObject responseData = factory.parse(responseString);

            Utils.printIfDebug("Response data: " + responseData.toString());

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Check status code of JSON data as well
                int jsonStatusCode = (int) responseData.getNumber("statusCode");

                if (jsonStatusCode == 200) {
                    return responseData.getObject("data");
                } else if (jsonStatusCode == 201) {
                    return responseData.getObject("data");
                } else if (jsonStatusCode == 402) {
                    return responseData.getObject("data");
                } else if (jsonStatusCode == 400) {
                    return responseData.getObject("data");
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) { // TODO: What kind of exceptions are possible?
            throw new ServiceException("Error when contacting back-end");
        }
    }

    private JsonArray jsonArrayRequest(String url, JsonObject data) throws ServiceException {
        Utils.printIfDebug("Request data: " + data.toString());

        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response;

        try {
            HttpPost request = new HttpPost(url);
            StringEntity json = new StringEntity(
                    data.toString(),
                    ContentType.APPLICATION_JSON
            );
            User user = Utils.getCurrentUser();
            if (user != null) {
                request.addHeader("authorization", user.getToken());
            }
            request.setEntity(json);
            response = client.execute(request);

            String responseString = EntityUtils.toString(response.getEntity());

            // Create JSON object from response
            JsonObject responseData = factory.parse(responseString);

            Utils.printIfDebug("Response data: " + responseData.toString());

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Check status code of JSON data as well
                int jsonStatusCode = (int) responseData.getNumber("statusCode");

                if (jsonStatusCode == 200) {
                    return responseData.getArray("data");
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) { // TODO: What kind of exceptions are possible?
            throw new ServiceException("Error when contacting back-end");
        }
    }

    private JsonArray jsonArrayRequestget(String url, JsonObject data) throws ServiceException {
        Utils.printIfDebug("Request data: " + data.toString());

        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response;

        try {
            HttpGet request = new HttpGet(url);
/*            StringEntity json = new StringEntity(
                    data.toString(),
                    ContentType.APPLICATION_JSON
            );
          //  request.setEntity(json);*/
            User user = Utils.getCurrentUser();
            if (user != null) {
                request.addHeader("authorization", user.getToken());
            }
            response = client.execute(request);

            String responseString = EntityUtils.toString(response.getEntity());

            // Create JSON object from response
            JsonObject responseData = factory.parse(responseString);

            Utils.printIfDebug("Response data: " + responseData.toString());

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Check status code of JSON data as well
                int jsonStatusCode = (int) responseData.getNumber("statusCode");

                if (jsonStatusCode == 200) {
                    return responseData.getArray("data");
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) { // TODO: What kind of exceptions are possible?
            throw new ServiceException("Error when contacting back-end");
        }
    }

    public String login(String email, String password) throws ServiceException {
        JsonObject data = factory.createObject();

        data.put("username", email);
        data.put("password", password);

        JsonObject response1 = jsonObjectRequest(backendURL + "loginUser/", data);

        if (response1 == null) {
            String message = "error while login.. try again!";
            return message;
        }
        else if(response1.getString("Message").equals(""))
        {
            User user = new User(
                    response1.getString("username"),
                    response1.getString("firstName"),
                    response1.getString("lastName"),
                    response1.getString("email"),
                    response1.getString("phone"),
                    response1.getString("address"),
                    response1.getString("Token"),
                    response1.getBoolean("IsSuperUser"));
                    user.setId((int) response1.getNumber("id"));
                    VaadinSession.getCurrent().setAttribute("user", user);
        }
        return response1.getString("Message");


    }

    public boolean forgetpassword(String email) throws ServiceException {
        return true;
    }

    public boolean verifyemail(String email) throws ServiceException {
        return true;
    }

    public Collection<Auction> allauction() throws ServiceException {
        JsonObject data = factory.createObject();

        data.put("uid", "1");
        Collection<Auction> auction = new ArrayList();

        JsonArray response = jsonArrayRequestget(backendURL + "allauctions", data);


        for (int i = 0; i < response.length(); i++) {
            JsonObject jsonAuction = response.getObject(i);
            JsonObject jsonAuction_sellerinfo = jsonAuction.get("seller");
            Auction auction1 = new Auction((int) jsonAuction.getNumber("id"), jsonAuction.getString("title"), jsonAuction.getString("itemDescription"), (float) jsonAuction.getNumber("minPrice"), (float) jsonAuction.getNumber("topBid"), (float) jsonAuction.getNumber("topBidNo"), jsonAuction.getString("deadline"), jsonAuction.getString("createdDate"), jsonAuction.getString("winner"), (int) jsonAuction.getNumber("category"));
            auction1.setStatus((int) jsonAuction.getNumber("status"));
            auction1.setOwner_id(Integer.parseInt(jsonAuction_sellerinfo.getString("UserId")));
            auction1.setCategoryName((int) jsonAuction.getNumber("category"));
            auction.add(auction1);

        }

        return auction;
    }

    public List<String> showcategory() throws ServiceException {
        List<String> category = new ArrayList<String>();
        JsonObject data = factory.createObject();
        data.put("uid", "1");
        JsonArray response = jsonArrayRequestget(backendURL + "allCategories/", data);
        if (response != null) {
            for (int i = 0; i < response.length(); i++) {

                JsonObject jsonCategory = response.getObject(i);
                category.add(jsonCategory.getString("typeName"));

            }
            return category;
        }
        return category;
    }

    public Collection<Auction> myauction() throws ServiceException {
        JsonObject data = factory.createObject();

        data.put("uid", "1");
        Collection<Auction> auction = new ArrayList();

        JsonArray response = jsonArrayRequestget(backendURL + "getMyAuctions/", data);


        for (int i = 0; i < response.length(); i++) {
            JsonObject jsonAuction = response.getObject(i);
            Auction auction1 = new Auction((int) jsonAuction.getNumber("id"), jsonAuction.getString("title"), jsonAuction.getString("itemDescription"), (float) jsonAuction.getNumber("minPrice"), (float) jsonAuction.getNumber("topBid"), (float) jsonAuction.getNumber("topBidNo"), jsonAuction.getString("deadline"), jsonAuction.getString("createdDate"), jsonAuction.getString("winner"), (int) jsonAuction.getNumber("category"));
            auction1.setStatus((int) jsonAuction.getNumber("status"));
            auction1.setCategoryName((int) jsonAuction.getNumber("category"));
            auction.add(auction1);

        }

        return auction;
    }
    public Collection<Auction> categoryauction(int category) throws ServiceException
    {
        JsonObject data = factory.createObject();

        data.put("uid", "1");
        Collection<Auction> auction = new ArrayList();

        JsonArray response = jsonArrayRequestget(backendURL + "auctionsByCat/" + category + "/", data);


        for (int i = 0; i < response.length(); i++) {
            JsonObject jsonAuction = response.getObject(i);
            Auction auction1 = new Auction((int) jsonAuction.getNumber("id"), jsonAuction.getString("title"), jsonAuction.getString("itemDescription"), (float) jsonAuction.getNumber("minPrice"), (float) jsonAuction.getNumber("topBid"), (float) jsonAuction.getNumber("topBidNo"), jsonAuction.getString("deadline"), jsonAuction.getString("createdDate"), jsonAuction.getString("winner"), (int) jsonAuction.getNumber("category"));
            auction1.setStatus((int) jsonAuction.getNumber("status"));
            auction1.setCategoryName((int) jsonAuction.getNumber("category"));
            auction.add(auction1);

        }

        return auction;
    }

    public boolean createAuction(String title, String Deadline, String startprice, String description, int category) throws ServiceException {
        JsonObject data = factory.createObject();
        data.put("AuctionTitle", title);
        data.put("Deadline", Deadline);
        data.put("StartingPrice", startprice);
        data.put("Description", description);
        data.put("Category", category);
        JsonObject response = jsonObjectRequest(backendURL + "CreateAuction/", data);

        if (response == null) {
            return false;
        }
        return true;
    }

    public Auction singleauctionview(int auction_Id) throws ServiceException {
        JsonObject data = factory.createObject();

        data.put("uid", "1");

        JsonObject response = jsonObjectRequestget(backendURL + "viewauctions/" + auction_Id + "/", data);
        JsonObject jsonAuction = response.get("Auction");
        if (jsonAuction == null) {
            return null;
        } else {
            Auction auction1;
            auction1 = new Auction((int) jsonAuction.getNumber("id"), jsonAuction.getString("title"), jsonAuction.getString("itemDescription"), (float) jsonAuction.getNumber("minPrice"), (float) jsonAuction.getNumber("topBid"), (float) jsonAuction.getNumber("topBidNo"), jsonAuction.getString("deadline"), jsonAuction.getString("createdDate"), jsonAuction.getString("winner"), (int) jsonAuction.getNumber("category"));
            auction1.setStatus((int) jsonAuction.getNumber("status"));
            JsonObject jsonAuction_sellerinfo = jsonAuction.get("seller");
            String name = jsonAuction_sellerinfo.getString("FirstName") + jsonAuction_sellerinfo.getString("LastName");
            auction1.setOwner(name);
            auction1.setCategoryName((int) jsonAuction.getNumber("category"));
            return auction1;
        }
    }

    public Collection<Bid> singleauctionbidinfo(int auction_Id) throws ServiceException {
        Collection<Bid> bid = new ArrayList<>();
        JsonObject data = factory.createObject();

        data.put("uid", "1");

        JsonObject response = jsonObjectRequestget(backendURL + "viewauctions/" + auction_Id + "/", data);
        JsonArray responsebiding = response.get("Biding");
        if (responsebiding == null) {
            return null;
        } else {
            for (int i = 0; i < responsebiding.length(); i++) {
                JsonObject jsonAuction = responsebiding.getObject(i);
                JsonObject jsonAuction_biderinfo = jsonAuction.get("bidUser");
                String name = jsonAuction_biderinfo.getString("FirstName") + jsonAuction_biderinfo.getString("LastName");
                Bid bid1 = new Bid(Integer.parseInt(jsonAuction_biderinfo.getString("UserId")), (int) jsonAuction.getNumber("bidNumber"), jsonAuction.getString("comment"), jsonAuction.getString("bidDate"), (float) jsonAuction.getNumber("amount"));
                bid1.setUserName(name);
                bid.add(bid1);
            }
            return bid;
        }
    }

    public String bidauction(int auction_id, float auction_version, float amount) throws ServiceException {
        JsonObject data = factory.createObject();
        data.put("Auctionid", auction_id);
        data.put("BidVersion", auction_version);
        data.put("bidAmount", amount);
        JsonObject response = jsonObjectRequest(backendURL + "bidauctions/" + auction_id + "/", data);
        if (response == null) {
            String message = "error while placing biding try again!";
            return message;
        }
        return response.getString("Message");
    }

    public String register(String username, String Password, String firstname, String lastname, String emailaddress, String confirmPassword) throws ServiceException {
        JsonObject data = factory.createObject();
        data.put("username", username);
        data.put("email", emailaddress);
        data.put("password", Password);
        data.put("firstName", firstname);
        data.put("lastName", lastname);
        data.put("phone", "");
        data.put("address", emailaddress);
        data.put("conformpassword", confirmPassword);
        JsonObject response = jsonObjectRequest(backendURL + "registerUser/", data);
        if (response == null) {
            String message = "error while registering user try again!";
            return message;
        }
        return response.getString("Message");
    }
    public String editUser(String username, String password, String firstname, String lastname,String emailaddress,String confirmPassword)throws ServiceException
    {
        JsonObject data = factory.createObject();
        data.put("username", username);
        data.put("email", emailaddress);
        data.put("password", password);
        data.put("firstName", firstname);
        data.put("lastName", lastname);
        data.put("phone", "");
        data.put("address", emailaddress);
        data.put("conformpassword", confirmPassword);
        JsonObject response = jsonObjectRequest(backendURL + "editUser/", data);
        if (response == null) {
            String message = "error while editing user try again!";
            return message;
        }
        return response.getString("Message");
    }
    public String banauction(int auction_id) throws ServiceException
    {
        JsonObject data = factory.createObject();
        data.put("username", "");
        JsonObject response = jsonObjectRequestget(backendURL + "banAuction/" + auction_id + "/", data);
        if (response == null) {
            String message = "error while banning try again!";
            return message;
        }
        return response.getString("Message");
    }

    public String editauction(String description,int category,int auction_id) throws ServiceException
    {
        JsonObject data = factory.createObject();
        data.put("Description",description);
        data.put("Category",category);
        JsonObject response = jsonObjectRequest(backendURL + "editAuction/" +auction_id+ "/", data);
        if (response == null) {
            String message = "error while banning try again!";
            return message;
        }
        return response.getString("Message");
    }

}

