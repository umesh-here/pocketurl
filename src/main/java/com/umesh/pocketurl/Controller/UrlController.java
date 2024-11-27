package com.umesh.pocketurl.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.umesh.pocketurl.Entity.Url;
import com.umesh.pocketurl.UrlRepository.UrlRepository;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class UrlController {

    @Autowired
    private UrlRepository UrlRepo;

    @GetMapping("/")
    public String showForm() {
        return "index"; 
    }

    @GetMapping("/shorten")
    public String urlshorten(@RequestParam String longUrl, HttpServletRequest request, Model model) {
        try {
            Optional<Url> existingUrl = UrlRepo.findByLongUrl(longUrl);

            if (existingUrl.isPresent()) {
                model.addAttribute("shortUrl", buildFullUrl(existingUrl.get().getShortUrl(), request));
            } else {
                Url url = new Url();
                url.setLongUrl(longUrl);
                UrlRepo.save(url);
                model.addAttribute("shortUrl", buildFullUrl(url.getShortUrl(), request));
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error decoding URL");
            return "index";
        }
        return "index"; 
    }

    private String buildFullUrl(String shortUrl, HttpServletRequest request) {
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();

        String portPart = (port == 80 || port == 443) ? "" : ":" + port;

        return scheme + "://" + host + portPart + "/" + shortUrl;
    }

    @GetMapping("/{shortUrl}")
    public String redirectToLongUrl(@PathVariable String shortUrl) {
        Optional<Url> optionalUrl = UrlRepo.findByShortUrl(shortUrl);

        if (optionalUrl.isEmpty()) {
            return "404"; 
        }

        Url url = optionalUrl.get();
        String longUrl = url.getLongUrl();

        return "redirect:" + (longUrl.startsWith("http://") || longUrl.startsWith("https://") ? longUrl : "https://" + longUrl);
    }
}
