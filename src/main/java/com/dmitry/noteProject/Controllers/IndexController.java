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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView save(@Valid NoteEntity note, BindingResult result) {
        ModelAndView model = new ModelAndView();
        if (result.hasErrors()) {
            model.addObject("note", note);
            model.setViewName("note/index");
            return model;
        }
        if(note.getId() != 0 ) {
            note.setDateCreate(noteRepository.findById(note.getId()).get().getDateCreate());
        }

        noteRepository.save(note);
        model.setViewName("redirect:/note/");
        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable (value = "id") int id){
        ModelAndView model = new ModelAndView();
        NoteEntity note  = noteRepository.findById(id).get();
        model.addObject("note", note);
        model.setViewName("note/show");
        return model;
    }

//    @RequestMapping(value ="/{id}/edit", method = RequestMethod.GET)
//    public  ModelAndView edit(@PathVariable (value = "id") int id ){
//        ModelAndView model = new ModelAndView();
//        model.addObject("note",noteRepository.findById(id));
//        model.setViewName("note/edit");
//        return model;
//
//    }

    @RequestMapping(value ="/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable (value = "id") int id){
        ModelAndView model = new ModelAndView();
        noteRepository.deleteById(id);
        model.setViewName("redirect:/note/");
        return model;

    }
}