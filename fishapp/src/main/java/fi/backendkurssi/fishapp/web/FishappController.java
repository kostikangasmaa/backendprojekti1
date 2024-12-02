package fi.backendkurssi.fishapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.backendkurssi.fishapp.domain.Fish;
import fi.backendkurssi.fishapp.domain.FishRepository;
import fi.backendkurssi.fishapp.domain.User;
import fi.backendkurssi.fishapp.domain.UserRepository;

@Controller
public class FishappController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FishRepository fishRepository;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/fishlist", method = RequestMethod.GET)
    public String fishList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            currentUsername = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        User currentUser = userRepository.findByUsername(currentUsername);
        List<Fish> fishes = fishRepository.findByUser(currentUser);
        model.addAttribute("fishes", fishes);

        return "fishlist";
    }

    @GetMapping("/addfish")
    public String showAddFishForm(Model model) {
        model.addAttribute("fish", new Fish());
        return "addfish";
    }

    @PostMapping("/savefish")
    public String addFish(@ModelAttribute Fish fish) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            currentUsername = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        User currentUser = userRepository.findByUsername(currentUsername);
        fish.setUser(currentUser);
        fishRepository.save(fish);

        return "redirect:/fishlist";
    }

    @PostMapping("/deletefish/{id}")
    public String deleteFish(@PathVariable("id") Long id) {
        Fish fish = fishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid fish Id:" + id));
        fishRepository.delete(fish);
        return "redirect:/fishlist";
    }

    @GetMapping("/editfish/{id}")
    public String showEditFishForm(@PathVariable("id") Long id, Model model) {
        Fish fish = fishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid fish Id:" + id));
        model.addAttribute("fish", fish);
        return "editfish";
    }

    @PostMapping("/updatefish/{id}")
    public String updateFish(@PathVariable("id") Long id, @ModelAttribute Fish fish) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            currentUsername = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        User currentUser = userRepository.findByUsername(currentUsername);
        Fish existingFish = fishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid fish Id:" + id));
        existingFish.setSpecies(fish.getSpecies());
        existingFish.setLength(fish.getLength());
        existingFish.setWeight(fish.getWeight());
        existingFish.setDate(fish.getDate());
        existingFish.setLocation(fish.getLocation());
        existingFish.setUser(currentUser);
        fishRepository.save(existingFish);

        return "redirect:/fishlist";
    }
}
