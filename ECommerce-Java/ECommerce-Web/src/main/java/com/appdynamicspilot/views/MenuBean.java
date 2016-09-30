package com.appdynamicspilot.views;

/**
 * Created by aleftik on 8/28/16.
 */

import com.appdynamicspilot.controllers.RegistrationController;
import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.service.ItemService;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aleftik
 */
@Named
@ApplicationScoped
@Scope("singleton")
public class MenuBean implements Serializable {

    @Inject
    ItemService itemService;
    private MenuModel model = null;

    public MenuBean() {

    }

    private void buildMenu() {
        DefaultSubMenu homeSub = new DefaultSubMenu();
        homeSub.setLabel("Home");
        homeSub.setIcon("ui-icon ui-icon-home");
        DefaultMenuItem  home = new DefaultMenuItem();
        home.setValue("Home");
        home.setIcon("ui-icon ui-icon-home");
        home.setUrl("/index.xhtml");
        homeSub.addElement(home);
        model.addElement(homeSub);

        DefaultSubMenu categorySub = new  DefaultSubMenu();
        categorySub.setLabel("Categories");
        categorySub.setIcon("ui-icon ui-icon-list");


        Item.ItemType[] types = Item.ItemType.values();
        for (Item.ItemType t:types) {
            DefaultSubMenu typeMenu = new DefaultSubMenu();
            typeMenu.setLabel(t.toString());

            List<String> categories = itemService.getCategoriesByType(t);
            for (String i: categories) {
                DefaultMenuItem categoryMenu = new DefaultMenuItem();
                categoryMenu.setValue(i);
                categoryMenu.setUrl ("/index.xhtml?t=" + t.toString() + "&c=" +i);
                typeMenu.addElement(categoryMenu);
            }
            categorySub.addElement(typeMenu);
        }

        model.addElement(categorySub);

    }

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        buildMenu();
    }

    private boolean isLoggedIn() {

        RegistrationController controller = FacesContext.getCurrentInstance().getApplication()
                .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{registrationController}", RegistrationController.class);
        if (controller == null) {
            return false;
        }
        return controller.getIsLoggedIn();
    }

    public MenuModel getModel() {
        return this.model;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}

