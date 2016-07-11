/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appdynamics.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.InputSource;

import com.opensymphony.xwork2.ActionContext;
import com.appdynamicspilot.model.Cart;
import com.appdynamicspilot.model.Item;
import com.appdynamicspilot.model.User;
import com.appdynamicspilot.service.CartService;
import com.appdynamicspilot.service.ItemService;
import com.appdynamicspilot.util.SpringContext;

public class CastorUtil {

    public List<Item> saveCartItems(String xml) {
        ItemService itemService = (ItemService) SpringContext.getBean("itemService");
        CartService cartService = (CartService) SpringContext.getBean("cartService");
        StringReader sr = new StringReader(xml);
        CartVO cartVo;
        Cart cart = new Cart();
        try {
            System.out.println(">>>>>>>>>>>>>>>xml is >>>>>>>>>>>>>>>" + xml);
            cartVo = getCartVO(xml);
            if (cartVo == null) {
                System.out.println(">>>>>> Cart is null>>>>>>>>>>" + cartVo);
            }
            List<ItemVO> items = cartVo.getItems();
            for (ItemVO item : items) {
                cart.addItem(itemService.getItemByName(item.getName()));
                User user = (User) ActionContext.getContext().getSession().get("USER");
                cart.setUser(user);
                cartService.saveItemInCart(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cart.getItems();
    }

    public CartVO getCartVO(String xml) {
        CartVO cartVO = null;
        Mapping mapping = new Mapping();
        String mappingRules = getMappingRule();
        StringReader sr = new StringReader(mappingRules);
        InputSource source = new InputSource(sr);
        mapping.loadMapping(source);
        Unmarshaller um = new Unmarshaller();
        try {
            um.setMapping(mapping);
            cartVO = (CartVO) um.unmarshal(CartVO.class, new StringReader(xml));
        } catch (MappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MarshalException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cartVO;

    }

    public String getMappingRule() {
        return "<?xml version=\"1.0\"?><!DOCTYPE mapping PUBLIC \"-//EXOLAB/Castor Object Mapping DTD Version 1.0//EN\" \"http://castor.exolab.org/mapping.dtd\"><mapping><description>A mapping file Cart application</description><class name=\"com.appdynamics.xml.ItemVO\"><field name=\"name\" type=\"string\"/></class><class name=\"com.appdynamics.xml.CartVO\"><field name=\"items\" type=\"com.appdynamics.xml.ItemVO\" collection=\"collection\" /></class></mapping>";
    }

    public String getXML(CartVO cartVO) {
        StringWriter sw = new StringWriter();
        try {
            Mapping mapping = new Mapping();
            String mappingRules = getMappingRule();
            StringReader sr = new StringReader(mappingRules);
            InputSource source = new InputSource(sr);
            mapping.loadMapping(source);
            Marshaller m = new Marshaller();
            m.setMapping(mapping);
            m.marshal(cartVO, sw);
        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (MappingException e) {
            e.printStackTrace();
        }

        return sw.toString();
    }

    public static void main(String[] args) {
        //Marshaller.marshal(ItemVO., handler)

        CastorUtil cu = new CastorUtil();
        CartVO cvo = new CartVO();
        cvo.getItems().add(new ItemVO("asdasd"));
        cvo.getItems().add(new ItemVO("aaa"));
        String xml = cu.getXML(cvo);
        cu.getCartVO(xml);
        System.out.println(xml);
    }
}
