package com.ServerSpring.Instagrao.controller;


import com.ServerSpring.Instagrao.models.Instagrao;
import com.ServerSpring.Instagrao.repository.InstagraoRepository;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class InstagraoController {

    @Autowired
    private InstagraoRepository _instagraoRepository;


    @RequestMapping(value= "/posts", method= RequestMethod.GET)
    public List<Instagrao> Get(){

        return (List<Instagrao>) _instagraoRepository.findAll();
    }


    @RequestMapping(value= "/posts/{id}", method= RequestMethod.GET, produces="application/json")
    public ResponseEntity<Instagrao>GetById(@PathVariable(value = "id")String id)
    {
        Optional<Instagrao> instagrao = _instagraoRepository.findById(id);
        if(instagrao.isPresent())
            return new ResponseEntity<Instagrao>(instagrao.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value="/posts", method=RequestMethod.POST, produces="application/json", consumes="application/json")
    public List<Instagrao> Post(@Valid @RequestBody Instagrao instagrao, HttpServletResponse response)
    {
        try {
            _instagraoRepository.save(instagrao);
        }catch(Exception e){
            response.setStatus(500);
            System.err.println("Erro na Persistencia no Banco" + e.getMessage());
            //return "Erro na Persistencia no Banco";
        }
        response.setStatus(200);
        System.out.println("Conectado com sucesso ao servidor!");
        return _instagraoRepository.findAll();
    }


    @RequestMapping(value = "/posts/{id}/like", method =RequestMethod.PUT)
    public ResponseEntity<Instagrao> Put(@PathVariable(value = "id") String id)
    {
        Optional<Instagrao> oldInstagrao = _instagraoRepository.findById(id);
        if(oldInstagrao.isPresent()){

            Instagrao instagraoz = oldInstagrao.get();
           instagraoz.newLike();
            _instagraoRepository.save(instagraoz);
            return new ResponseEntity<Instagrao>(instagraoz, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}


