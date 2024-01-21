package com.mitocode.controller;

import com.mitocode.dto.VitalSignDTO;
import com.mitocode.model.VitalSign;
import com.mitocode.service.IVitalSignService;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vital-signs")
@RequiredArgsConstructor
public class VitalSignController {

    private final IVitalSignService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<VitalSignDTO>> findAll() throws Exception{
        List<VitalSignDTO> list = service.findAll().stream().map(this::convertToDto).toList(); //e -> convertToDto(e)

        return new ResponseEntity<>(list, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSignDTO> findById(@PathVariable("id") Integer id) throws Exception{
        VitalSign obj = service.findById(id);

        return new ResponseEntity<>(convertToDto(obj), OK);
    }
        
    @PostMapping
    public ResponseEntity<Void> save( @RequestBody VitalSignDTO dto) throws Exception{
        System.out.println("GLORIA: POST GUARDADO: " + dto.toString());
        VitalSign obj = service.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVitalSign()).toUri();

        return ResponseEntity.created(location).build();
    } 

    @PutMapping("/{id}")
    public ResponseEntity<VitalSignDTO> update( @RequestBody VitalSignDTO dto, @PathVariable("id") Integer id) throws Exception{
        dto.setIdVitalSign(id);
        VitalSign obj = service.update(convertToEntity(dto), id);

        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<VitalSignDTO>> listPage(Pageable pageable){
        Page<VitalSignDTO> page = service.listPage(pageable).map(p -> mapper.map(p, VitalSignDTO.class));

        return new ResponseEntity<>(page, OK);
    }

    private VitalSignDTO convertToDto(VitalSign obj){
        return mapper.map(obj, VitalSignDTO.class);
    }

    private VitalSign convertToEntity(VitalSignDTO dto){
        return mapper.map(dto, VitalSign.class);
    }
}
