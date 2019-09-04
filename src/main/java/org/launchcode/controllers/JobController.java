package org.launchcode.controllers;

import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, @RequestParam int id) {
        model.addAttribute("job", jobData.findById(id));

        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        if (errors.hasErrors()) {
            //what do I need here to display an error message to user
            //note it asks us to pass jobForm, will need to use this somehow
            return "new-job";
        } else {
            jobData.add(newJob);
            //how do I extract the ID of the new job so I can direct to that job-detail page above?
            //asks us to use jobData.add(newJob) to create a new Job object
            //Need to extract form data and turn it into a newJob instance first
            return "/job/?id=";
        }

    }
}
