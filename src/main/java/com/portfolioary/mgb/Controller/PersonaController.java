
package com.portfolioary.mgb.Controller;

import com.portfolioary.mgb.Dto.dtoPersona;
import com.portfolioary.mgb.Entity.Persona;
import com.portfolioary.mgb.Security.Controller.Mensaje;
import com.portfolioary.mgb.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://frontendary.web.app")
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    ImpPersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity <List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona){
        if(!personaService.existById(id)){
            return new ResponseEntity(new Mensaje ("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(personaService.existByNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId() !=id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);     
        }
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje ("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
       // if(StringUtils.isBlank(dtopersona.getDescripcion())){
        //    return new ResponseEntity(new Mensaje ("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        //}
        
       // if(StringUtils.isBlank(dtopersona.getApellido())){
       //     return new ResponseEntity(new Mensaje ("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
       // }
        
       // if(StringUtils.isBlank(dtopersona.getTitulo())){
       //     return new ResponseEntity(new Mensaje ("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
       // }
        
       // if(StringUtils.isBlank(dtopersona.getProvincia())){
       //     return new ResponseEntity(new Mensaje ("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
       // }
        
      //  if(StringUtils.isBlank(dtopersona.getImg())){
      //      return new ResponseEntity(new Mensaje ("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
      //  }
        
        Persona persona = personaService.getOne(id).get();
        
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setTitulo(dtopersona.getTitulo());
        persona.setProvincia(dtopersona.getProvincia());
        persona.setImg(dtopersona.getImg());
        
        personaService.save(persona);
        
        return new ResponseEntity(new Mensaje ("persona actualizada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!personaService.existById(id)){
            return new ResponseEntity(new Mensaje ("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
}
