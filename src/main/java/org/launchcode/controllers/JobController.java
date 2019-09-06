package org.launchcode.controllers;

import org.launchcode.models.*;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String add(Model model, @Valid JobForm jobForm, Errors errors, RedirectAttributes attributes) {

        if (errors.hasErrors()) {
            return "new-job";
        }
        System.out.println("**************" + jobForm.getCoreCompetencyId());
        String jobName = jobForm.getName();
        Employer jobEmployer = jobData.getEmployers().findById(jobForm.getEmployerId());
        Location jobLocation = jobData.getLocations().findById(jobForm.getLocationId());
        PositionType jobPosition = jobData.getPositionTypes().findById(jobForm.getPositionTypeId());
        CoreCompetency jobCompetency = jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId());

        Job newJob = new Job(jobName, jobEmployer, jobLocation, jobPosition, jobCompetency);

        jobData.add(newJob);

        attributes.addAttribute("id", newJob.getId());

        return "redirect:/job";

    }
}
