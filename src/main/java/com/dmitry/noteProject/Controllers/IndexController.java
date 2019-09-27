package com.dmitry.noteProject.Controllers;

import com.dmitry.noteProject.Entities.NoteEntity;
import com.dmitry.noteProject.Repositories.NoteRepository;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/note")
public class IndexController {

    private final NoteRepository noteRepository;

    public IndexController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        List<NoteEntity> nodeList = (List<NoteEntity>) noteRepository.findAll();
        model.addObject("nodeList", nodeList );
        model.addObject("note", new NoteEntity());
        model.setViewName("note/index");

        return model;
    }
//
//    @RequestMapping(value = "/new", method = RequestMethod.GET)
//    public ModelAndView NewNote() {
//
//        ModelAndView model = new ModelAndView();
////        System.out.println("NoteName !!!!!!! = " + NoteName );
//        model.addObject("note", new NoteEntity());
//        model.setViewName("note/index");
//
//        return model;
//    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView save(@Valid NoteEntity note, BindingResult result) {
        ModelAndView model = new ModelAndView();
        if (result.hasErrors()) {
            model.addObject("note", note);
            model.setViewName("note/index");
            return model;
        }
        noteRepository.save(note);
        model.setViewName("redirect:/note/");
        return model;
    }
}