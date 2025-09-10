package tourism.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;
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
    public String getTagsFromOneNamedAttraction(@PathVariable String name){
        return "tags";
    }
    //    GET /attractions/add
    @GetMapping("add")
    public String addNamedAttraction(){
        return "addAttraction";
    }
    //    POST /attractions/save
    @PostMapping("save")
    public String saveAttractions(){
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
    @PostMapping("update")
    public String updateAttractions(@ModelAttribute TouristAttraction attraction) {
        service.updateAttraction(attraction);
        return "redirect:/attractions";
    }
    //    POST /attractions/delete/{name}
    @PostMapping("delete/{name}")
    public String deleteOneNamedAttraction(@PathVariable String name){
        return "redirect:/attractions";
    }
}
