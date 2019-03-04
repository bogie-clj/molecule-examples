package com.github.bogieclj.molecule.sql.example1.testpkg;

import com.iomolecule.system.annotations.FnProvider;
import com.iomolecule.system.annotations.Id;

import javax.inject.Named;

@FnProvider
public class TestSysFnProvider {


    @Id("function://test-sys/domain1/function1")
    @Named("greeting")
    public String function1(@Named("name") String name, @Named("sex") String sex,@Named("age") Integer age){
        String greetingFormat = "Hello %s.%s welcome to iomolecule! You are %d yrs old!";

        String message = null;
        if(sex.equalsIgnoreCase("m")){
            message = String.format(greetingFormat,"Mr",name,age);
        }else{
            message = String.format(greetingFormat,"Ms",name,age);
        }
        return message;
    }

    @Id("function://test-sys/domain1/function2")
    @Named("greeting")
    public String function2(@Named("name") String name,@Named("sex") String sex,@Named("age") Integer age){
        String greetingFormat = "Hello %s.%s welcome to iomolecule! You are %d yrs old!";

        String message = null;
        if(sex.equalsIgnoreCase("m")){
            message = String.format(greetingFormat,"Mr",name,age);
        }else{
            message = String.format(greetingFormat,"Ms",name,age);
        }
        return message;
    }


    @Id("function://test-sys/domain1/function3")
    @Named("greeting")
    public String function3(@Named("person") Person person){
        String greetingFormat = "Hello %s.%s welcome to iomolecule! You are %d yrs old!";

        String message = null;
        if(person.getSex().equalsIgnoreCase("m")){
            message = String.format(greetingFormat,"Mr",person.getName(),person.getAge());
        }else{
            message = String.format(greetingFormat,"Ms",person.getName(),person.getAge());
        }
        return message;
    }

    @Id("function://test-sys/domain1/function4")
    @Named("greeting")
    public String function4(){
        return "Hello From Function4";
    }

}
