package tourism.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tourism.model.TouristAttraction;

import java.util.ArrayList;


import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tourism.service.TouristService;

import java.util.List;


@Controller
@RequestMapping("attractions")
public class TouristController {

    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }
    //    GET /attractions
    @GetMapping
    public String getAttractions(Model model){
        model.addAttribute("attractions", service.getAttractions());
        return "attractionList";
    }
    //    GET /attractions/{name}
    @GetMapping("{name}")
    public String getOneNamedAttraction(@PathVariable String name){
        return "";
    }

    //    GET /attractions/{name}/tags
    @GetMapping("{name}/tags")
    public String getTagsFromOneNamedAttraction(@PathVariable String name, Model model){
        TouristAttraction a = service.getOneNamedAttraction(name);
        if (a == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction not found");

        model.addAttribute("attraction", a);
        model.addAttribute("tags", a.getTags() == null ? List.of() : a.getTags());
        // Fallback: returner tom liste i stedet for null, så Thymeleaf kan loope sikkert

        return "tags";
    }

    //    GET /attractions/add
    @GetMapping("add")
    public String addNamedAttraction(Model model){
        model.addAttribute("cities", service.getCities());
        model.addAttribute("allTags", service.getTags());
        return "addAttraction";
    }
    //    POST /attractions/save
    @PostMapping("save")
    public String saveAttractions(@RequestBody TouristAttraction touristAttraction){
        service.addNamedAttraction(touristAttraction);
        return "redirect:/attractions";
    }
    // GET /attractions/{name}/edit  -> show the edit form
    @GetMapping("{name}/edit")
    public String editOneNamedAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.getOneNamedAttraction(name);
        if (attraction == null) {
            return "redirect:/attractions";
        }
        model.addAttribute("attraction", attraction);               // object to bind
        model.addAttribute("cities", service.getCities());   // dropdown options
        model.addAttribute("tags", service.getTags());       // checkbox options
        return "updateAttraction";                                   // view name
    }

    //    POST /attractions/update
    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction form) {
        if (form.getTags() == null) form.setTags(new ArrayList<>());
        TouristAttraction a = service.updateAttraction(form);

        if (a == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return "redirect:/attractions";
    }

    //    POST /attractions/delete/{name}
    @PostMapping("delete/{name}")
    public String deleteOneNamedAttraction(@PathVariable String name){
        return "redirect:/attractions";
    }
}
