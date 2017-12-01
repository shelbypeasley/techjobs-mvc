package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
    public String search(Model model,
                         @RequestParam String searchType, @RequestParam String searchTerm) {


           if (searchType.equals("all")) {
               ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
               int count = jobs.size();
               model.addAttribute("columns", ListController.columnChoices);
               model.addAttribute("jobs", jobs);
               model.addAttribute("count", count + " Result(s)");
               return "search";
           }

           else {
               ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
               int count = jobs.size();
               model.addAttribute("columns", ListController.columnChoices);
               model.addAttribute("jobs", jobs);
               model.addAttribute("count", count);
               return "search";
           }
    }
    // TODO #1 - Create handler to process search request and display results

}
