package com.appdynamicspilot.views;

import org.glassfish.jersey.process.internal.RequestScoped;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;

import java.util.ArrayList;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.context.FacesContext;
import javax.faces.component.UIOutput;

/**
 * Created by aleftik on 9/1/16.
 */

@Named
@RequestScoped
public class CountryView {

    private List<SelectItem> countries = new ArrayList<SelectItem>();
    private List<SelectItem> states = new ArrayList<SelectItem>();


    @PostConstruct
    public void init() {
        countries.add(new SelectItem("United States","United States"));
        countries.add(new SelectItem("Canada","Canada"));
        countries.add(new SelectItem("Mexico","Mexico"));
        addUSA();
    }

    public void addUSA() {
        states.add(new SelectItem("AL","Alabama"));
        states.add(new SelectItem("AK","Alaska"));
        states.add(new SelectItem("AZ","Arizona"));
        states.add(new SelectItem("AZ","Arizona"));
        states.add(new SelectItem("AR","Arkansas"));
        states.add(new SelectItem("CA","California"));
        states.add(new SelectItem("CO","Colorado"));
        states.add(new SelectItem("CT","Connecticut"));
        states.add(new SelectItem("DE","Delaware"));
        states.add(new SelectItem("FL","Florida"));
        states.add(new SelectItem("GA","Georgia"));
        states.add(new SelectItem("HI","Hawaii"));
        states.add(new SelectItem("ID","Idaho"));
        states.add(new SelectItem("IL","Illinois"));
        states.add(new SelectItem("IN","Indiana"));
        states.add(new SelectItem("IA","Iowa"));
        states.add(new SelectItem("KS","Kansas"));
        states.add(new SelectItem("KY","Kentucky"));
        states.add(new SelectItem("LA","Louisiana"));
        states.add(new SelectItem("ME","Maine"));
        states.add(new SelectItem("MD","Maryland"));
        states.add(new SelectItem("MA","Massachusetts"));
        states.add(new SelectItem("MI","Michigan"));
        states.add(new SelectItem("MN","Minnesota"));
        states.add(new SelectItem("MS","Mississippi"));
        states.add(new SelectItem("MS","Missouri"));
        states.add(new SelectItem("MO","Montana"));
        states.add(new SelectItem("NE","Nebraska"));
        states.add(new SelectItem("NV","Nevada"));
        states.add(new SelectItem("NH","New Hampshire"));
        states.add(new SelectItem("NJ","New Jersey"));
        states.add(new SelectItem("NM","New Mexico"));
        states.add(new SelectItem("NY","New York"));
        states.add(new SelectItem("NC","North Carolina"));
        states.add(new SelectItem("ND"," North Dakota"));
        states.add(new SelectItem("OH","Ohio"));
        states.add(new SelectItem("OK","Oklahoma"));
        states.add(new SelectItem("OR","Oregon"));
        states.add(new SelectItem("PA","Pennsylvania"));
        states.add(new SelectItem("RI","Rhode Island"));
        states.add(new SelectItem("SC","South Carolina"));
        states.add(new SelectItem("SD","South Dakota"));
        states.add(new SelectItem("TN","Tennessee"));
        states.add(new SelectItem("TX","Texas   "));
        states.add(new SelectItem("UH","Utah"));
        states.add(new SelectItem("VT","Vermont"));
        states.add(new SelectItem("VA","Virginia"));
        states.add(new SelectItem("WA","Washington"));
        states.add(new SelectItem("WV","West Virginia"));
        states.add(new SelectItem("WI","Wisconsin"));
        states.add(new SelectItem("WY","Wyoming"));
    }

    public void addCanada() {
        states.add(new SelectItem("AB","Alberta"));
        states.add(new SelectItem("BC","British Columbia"));
        states.add(new SelectItem("MB","Manitoba"));
        states.add(new SelectItem("NB","New Brunswick"));
        states.add(new SelectItem("NL","Newfoundland and Labrador"));
        states.add(new SelectItem("NS","Nova Scotia"));
        states.add(new SelectItem("ON","Ontario"));
        states.add(new SelectItem("PE","Prince Edward Island"));
        states.add(new SelectItem("QC","Quebec"));
        states.add(new SelectItem("SK","Saskatchewan"));
    }

    public void addMexico() {
        states.add(new SelectItem("AGS","Aguascalientes"));
        states.add(new SelectItem("BC","Baja California"));
        states.add(new SelectItem("CAM","Campeche"));
        states.add(new SelectItem("CHP","Chiapas"));
        states.add(new SelectItem("CHH","Chihuahua"));
        states.add(new SelectItem("COA","Coahuila"));
        states.add(new SelectItem("DUR","Durango"));
        states.add(new SelectItem("GTO","Guanajuato"));
        states.add(new SelectItem("GRO","Guerrero"));
        states.add(new SelectItem("HDG","Hidalgo"));
        states.add(new SelectItem("EM","State of Mexico"));
        states.add(new SelectItem("DF","Federal District"));
        states.add(new SelectItem("MIC","Michoacán"));
        states.add(new SelectItem("MOR","Morelos"));
        states.add(new SelectItem("NAY","Nayarit"));
        states.add(new SelectItem("NL","Nuevo León"));
        states.add(new SelectItem("OAX","Oaxaca"));
        states.add(new SelectItem("QUE","Querétaro"));
        states.add(new SelectItem("QR","Quintana Roo"));
        states.add(new SelectItem("SLP","San Luis Potosí"));
        states.add(new SelectItem("SIN","Sinaloa"));
        states.add(new SelectItem("SON","Sonora"));
        states.add(new SelectItem("TAB","Tabasco"));
        states.add(new SelectItem("TAM","Tamaulipas"));
        states.add(new SelectItem("TLA","Tlaxcala"));
        states.add(new SelectItem("VER","Veracruz"));
        states.add(new SelectItem("YUC","Yucatán"));
        states.add(new SelectItem("ZAC","Zacatecas"));
    }

    public List<SelectItem> getCountries() {
        return countries;
    }

    public void setCountries(List<SelectItem> countries) {
        this.countries = countries;
    }

    public List<SelectItem> getStates() {
        return states;
    }

    public void setStates(List<SelectItem> states) {
        this.states = states;
    }

    public void changedCountry(AjaxBehaviorEvent event) {
        String newValue = (String) ((UIOutput)event.getSource()).getValue();
        if (newValue.equals("Mexico")) {
            states.clear();
            addMexico();
        } else if (newValue.equals("Canada")) {
            states.clear();
            addCanada();
        } else {
            states.clear();
            addUSA();
        }
    }
}
