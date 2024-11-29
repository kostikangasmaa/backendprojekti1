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
        // Get the current logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            currentUsername = ((UserDetails) authentication.getPrincipal()).getUsername();
        }

        // Find the user by username
        User currentUser = userRepository.findByUsername(currentUsername);

        // Retrieve fishes for the current user
        List<Fish> fishes = fishRepository.findByUser(currentUser);

        // Add fishes to the model
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
}

