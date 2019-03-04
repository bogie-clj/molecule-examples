package com.github.bogieclj.molecule.sql.example1.testpkg;

import com.iomolecule.system.TypeConverter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

import static com.iomolecule.util.JSONUtils.OBJECT_MAPPER;

@Slf4j
public class PersonConverter implements TypeConverter{

    @Override
    public Object convert(Object in) {
        log.info("Converting {}",in);
        if(in instanceof String){
            try {
                return OBJECT_MAPPER.readValue((String)in,Person.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(in instanceof Map){
            return OBJECT_MAPPER.convertValue((Map)in,Person.class);
        }
        return in;
    }
}
