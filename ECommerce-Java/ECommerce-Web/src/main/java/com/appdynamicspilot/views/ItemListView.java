package com.appdynamicspilot.views;


import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.service.ItemService;


import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
/**
 * Created by aleftik on 8/28/16.
 */

@Named
@ManagedBean
@RequestScoped
public class ItemListView implements Serializable {
    @Inject
    private ItemService itemService = null;

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    private Item selectedItem = null;

    public List<Item> getItems() {
        Map<String, String> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String category = requestMap.get("c");
        String type = requestMap.get("t");
        if ((type != null) && (category != null)) {
            return itemService.getItemByCategory(Item.ItemType.valueOf(type.toUpperCase()),category);
        }  else if ((type != null) && (category == null)) {
            return itemService.getItemByType(Item.ItemType.valueOf(type.toUpperCase()));
        }
        return itemService.getAllItems();
    }

}
