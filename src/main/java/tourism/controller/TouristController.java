package tourism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("attractions")
public class TouristController {

    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    //    GET /attractions
    @GetMapping
    public String getAttractions(){
        return "";
    }
    //    GET /attractions/{name}
    @GetMapping("{name}")
    public String getOneNamedAttraction(@PathVariable String name){
        return "";
    }
    //    GET /attractions/{name}/tags
    @GetMapping("{name}/tags")
    public String getTagsFromOneNamedAttraction(@PathVariable String name){
        return "";
    }
    //    GET /attractions/add
    @GetMapping("add")
    public String addNamedAttraction(){
        return "";
    }
    //    POST /attractions/save
    @PostMapping("save")
    public String saveAttractions(){
        return "";
    }
    //    GET /attractions/{name}/edit
    @GetMapping("{name}/edit")
    public String editOneNamedAttraction(@PathVariable String name){
        return "";
    }
    //    POST /attractions/update
    @PostMapping("update")
    public String updateAttractions(){
        return "";
    }
    //    POST /attractions/delete/{name}
    @PostMapping("delete/{name}")
    public String deleteOneNamedAttraction(@PathVariable String name){
        return "";
    }
}
