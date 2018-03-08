package com.scriptofan.ecommerce.Database;

import com.scriptofan.ecommerce.CSVParser.ParserCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path="/database")
public class DatabaseController {

    @Autowired
    ParserCsvService parserCsvService;


    @Autowired
    private Repository repository;


    @GetMapping("/item")
    public @ResponseBody String addMap() {

        Item item = new Item();
        item.setId(1);


        List<Map<String, String>> list;

        list = parserCsvService.getListOfItems();

        for (Map<String, String> map : list) {
           item.setMap(map);
        }

        repository.save(item);

        return "Item saved";
    }

}
