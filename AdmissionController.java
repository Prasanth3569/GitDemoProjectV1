package com.todoapp.controller;

import com.todoapp.model.Admission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdmissionController {

    @Autowired
    private AdmissionRepository admissionRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("admissions", admissionRepository.findAll());
        model.addAttribute("newAdmission", new Admission());
        return "index";
    }

    @PostMapping("/apply")
    public String applyAdmission(@ModelAttribute Admission admission) {
        admissionRepository.save(admission);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        model.addAttribute("admissions", admissionRepository.findAll());
        return "admin";
    }

    @PostMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        Admission admission = admissionRepository.findById(id).orElseThrow();
        admission.setStatus(status);
        admissionRepository.save(admission);
        return "redirect:/admin";
    }
} 