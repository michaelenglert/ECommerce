package com.appdynamics.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;



@Path("/service/json")
public class Service {

	@POST
	@Path("/login")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public String ValidateUser(@Context HttpServletRequest req,
			@FormParam("username") String name,
			@FormParam("password") String password) {
		Client client = Client.create();
		WebResource webResource = client.resource(GetConfigrestv1()
				+ "user/login");
		try {
			MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("username", name);
			formData.add("password", password);
			ClientResponse response = webResource
					.type("application/x-www-form-urlencoded")
					.post(ClientResponse.class, formData);
			return response.getEntity(String.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@GET
	@Path("/getallitems")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllItems() throws Exception {
		URL url = new URL(GetConfigrestv2() + "items/all");
		try {
			URLConnection conn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			List<Item> data = new Gson().fromJson(in,
					new TypeToken<List<Item>>() {
					}.getType());
			return new Gson().toJson(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@GET
	@Path("/getcartitems")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public String getCartItems(@Context HttpServletRequest req)
			throws Exception {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		try {
			List<CartItem> response = Client.create(clientConfig)
					.resource(GetConfigrestv2() + "cart/all")
					.accept(MediaType.APPLICATION_JSON)
					.header("username", req.getHeader("username"))
					.get(new GenericType<List<CartItem>>() {
					});
			return new Gson().toJson(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@GET
	@Path("/additemtocart/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String addItemToCart(@Context HttpServletRequest req,
			@PathParam("id") int id) throws Exception {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		try {
			CartResponse response = Client.create(clientConfig)
					.resource(GetConfigrestv2() + "cart/" + id)
					.accept(MediaType.APPLICATION_JSON)
					.header("username", req.getHeader("username"))
					.get(new GenericType<CartResponse>() {
					});
			return new Gson().toJson(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@DELETE
	@Path("/removeitemfromcart/{id}")
	@Produces({ MediaType.TEXT_PLAIN })
	public String removeItemFromCart(@Context HttpServletRequest req,
			@PathParam("id") int id) throws Exception {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		try {
			ClientResponse response = Client.create(clientConfig)
					.resource(GetConfigrestv2() + "cart/" + id)
					.header("username", req.getHeader("username"))
					.delete(ClientResponse.class);
			return response.getEntity(String.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@GET
	@Path("/checkout")
	@Produces(MediaType.TEXT_PLAIN)
	public String CheckOutItemsFromCart(@Context HttpServletRequest req)
			throws Exception {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		try {
			ClientResponse response = Client.create(clientConfig)
					.resource(GetConfigrestv2() + "cart/co")
					.header("username", req.getHeader("username"))
					.get(ClientResponse.class);
			return response.getEntity(String.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	private String GetConfigrestv2() {
		CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues();
		try {
			return properties.getrestv2Values();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String GetConfigrestv1() {
		CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues();
		try {
			return properties.getrestv1Values();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}