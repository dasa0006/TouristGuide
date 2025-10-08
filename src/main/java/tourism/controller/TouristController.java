package tourism.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tourism.service.TouristService;
import java.util.List;

@Controller
@RequestMapping("/attractions")
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
    @GetMapping("/{name}")
    public String getByName(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.getByName(name);
        model.addAttribute("attraction", attraction);
        return "attractionDetail";
    }

    //    GET /attractions/{name}/tags
    @GetMapping("/{name}/tags")
    public String getTagsFromOneNamedAttraction(@PathVariable String name, Model model){
        TouristAttraction a = service.getByName(name);
        model.addAttribute("attraction", a);
        model.addAttribute("tags", a.getTags() == null ? List.of() : a.getTags());
        return "tags";
    }

    // vis form
    //    GET /attractions/add
    @GetMapping("/add")
    public String addNamedAttraction(Model model){
        model.addAttribute("touristAttraction", new TouristAttraction());
        model.addAttribute("cities", service.getCities());
        model.addAttribute("allTags", service.getTags());
        return "addAttraction";
    }

    // gem i db
    //    POST /attractions/add
    @PostMapping("/add")
    public String addAttraction(@ModelAttribute("touristAttraction") TouristAttraction form) {
        service.addAttraction(form);
        return "redirect:/attractions";
    }

    // vis form
    //    GET /attractions/{name}/edit
    @GetMapping("{name}/edit")
    public String editOneNamedAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.getByName(name);
        if (attraction == null) return "redirect:/attractions";

        model.addAttribute("attraction", attraction);
        model.addAttribute("cities", service.getCities());
        model.addAttribute("allTags", service.getTags());
        return "updateAttraction";
    }

    // opdater
    //    POST /attractions/{name}/update
    @PostMapping("/{name}/update")
    public String updateAttraction(@PathVariable String name, @ModelAttribute TouristAttraction form) {
        form.setName(name);
        service.updateAttraction(form);
        return "redirect:/attractions";
    }

    // slet
    //    POST /attractions/{name}/delete
    @PostMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable String name){
        service.deleteAttraction(name);
        return "redirect:/attractions";
    }
}