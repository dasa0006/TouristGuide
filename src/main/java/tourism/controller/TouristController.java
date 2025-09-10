package tourism.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;

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
        return "attractionsList";
    }
    //    GET /attractions/{name}
    @GetMapping("{name}")
    public String getOneNamedAttraction(@PathVariable String name){
        return "";
    }
    //    GET /attractions/{name}/tags
    @GetMapping("{name}/tags")
    public String getTagsFromOneNamedAttraction(@PathVariable String name){
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
    //    GET /attractions/{name}/edit
    @GetMapping("{name}/edit")
    public String editOneNamedAttraction(@PathVariable String name){
        return "updateAttraction";
    }
    //    POST /attractions/update
    @PostMapping("update")
    public String updateAttractions(){
        return "redirect:/attractions";
    }
    //    POST /attractions/delete/{name}
    @PostMapping("delete/{name}")
    public String deleteOneNamedAttraction(@PathVariable String name){
        return "redirect:/attractions";
    }
}
