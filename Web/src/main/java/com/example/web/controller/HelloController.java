package com.example.web.controller;

import com.example.web.err.CustomException;
import com.example.web.service.TortInfo;
import com.example.web.service.TortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hello")
public class HelloController {

    private TortService tortService;


    @Autowired

    public void setTortService(TortService tortService) {
        this.tortService = tortService;
    }

    /**
     * Показать все торты
     *
     * @return
     */
    @GetMapping
    public ModelAndView getAllTortsView() {
        List<TortInfo> torts = tortService.getAll();

        ModelAndView view = new ModelAndView();
        view.setViewName("/tortListBS.jsp");
        if(torts.isEmpty()) {
            view.addObject("emptyMessage", "В базе данных нет тортов.<br>" + "Обратитесь к администратору ресурса");

        }
        view.addObject("alltorts", torts);
        return view;
    }

    /**
     * Показать один торт
     *
     * @return
     */

    @GetMapping("/tort")
    public ModelAndView getTortView(@RequestParam("tortId")String tortId) {
        TortInfo tort = tortService.getTort(tortId);

        ModelAndView view =  new ModelAndView();
        view.setViewName("/detailedTort.jsp");
        view.addObject("tort", tort);

        return view;


    }

    /**
     * Показать страницу создания нового торта.
     */

    @GetMapping("/tort/create")
    public ModelAndView getCreateTortView() {
        List<TortInfo> torts = tortService.getAll();

        ModelAndView view = new ModelAndView();
        view.addObject("AllTorts", torts);
        view.setViewName("/createTortBS.jsp");
    }

    /**
     * Создать новый торт
     */

    @PostMapping("/tort/create")
    public ModelAndView createTort(@RequestParam("name")
                                   String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("price") String priceStr,
                                   @RequestParam("weight") String weightStr) {

        String message;
        try {
            double price = parsePrice(priceStr);
            double weight = parseWeight(weightStr);
            TortInfo tortInfo = new TortInfo();
            tortInfo.setName(name);
            tortInfo.setDescription(description);
            tortInfo.createId();
            tortInfo.setPrice(price);
            tortInfo.setWeight(weight);
            tortService.create(tortInfo);

            message = "Торт" + name + "успешно создан";
        } catch (Exception e) {
            message = "Ошибка при создании торта:" +
                    e.getMessage();
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("createTortBS.jsp");
        view.addObject("message", message);
        return view;
    }

    private double parseWeight(String weight) {
        return Double.parseDouble(weight);
    }

    private double parsePrice(String price) {
        return Double.parseDouble(price);
    }

    @GetMapping("tort/update")
    public ModelAndView
    getUpdateTortView(@RequestParam("tordId") String tortId) {
        TortInfo tort = tortService.getTort(tortId);

        ModelAndView view = new ModelAndView();
        view.setViewName("/updateTortBS.jsp");
        view.addObject("tort", tort);

        return view;
    }

    @PostMapping("/tort/update")
    public ModelAndView updateTort(@RequestParam("id") String id,
                                   @RequestParam("name") String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("weight") String weightStr,
                                   @RequestParam("price") String priceStr) {
        String message;
    try {
        double price = parsePrice(priceStr);
        double weight = parseWeight(weightStr);

        TortInfo tortInfo =  new TortInfo();
        tortInfo.setId(id);
        tortInfo.setName(name);
        tortInfo.setWeight((int) weight);
        tortInfo.setPrice((int) price);
        tortService.update(tortInfo);

        message = "Торт" + name + "успешно обновлен в базе данных";
        ModelAndView view = getCreateTortView(id);
        view.addObject("message", message);
        return view;

    } catch (CustomException e) {
        message = "При обновлении торта" + name +
                "в базе данных, произошла ошибка:<br>" +
                e.getMessage();
        ModelAndView view = getUpdateTortView(id);
        view.addObject("message", message);
        return view;
    }

    @GetMapping("/tort/delete")
            public ModelAndView getDeleteTortView() {
        List<TortInfo> torts = tortService.gettAll();

        ModelAndView view = new ModelAndView();
        view.setViewName("/deleteTortBS.jsp");
        view.addObject("AllTorts", torts);
        if (torts.size() == 0) {
            view.addObject("emptyMessage", "База данных пуста!");


            }
            return view;
        }

        @PostMapping("tort/delete")
                public ModelAndView deleteTort(@RequestParam ("id")
        String tortId) {

        try {
            tortService.delete(tortId);
            return getDeleteTortView();
        }catch (Exception ex) {
            return getDeleteTortView();
        }
        }
        }
}