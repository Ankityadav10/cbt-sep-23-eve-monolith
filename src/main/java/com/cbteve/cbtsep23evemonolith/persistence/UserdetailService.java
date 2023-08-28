package com.cbteve.cbtsep23evemonolith.persistence;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserdetailService {


    UserdetailRepository userdetailRepository;
    UsertypelinkRepository usertypelinkRepository;

    UserdetailService(

            UserdetailRepository userdetailRepository,
            UsertypelinkRepository usertypelinkRepository
    )
    {
        this.userdetailRepository = userdetailRepository;
        this.usertypelinkRepository = usertypelinkRepository;
    }

     public FullUserDetail getFullUserDetail(String username) {

        FullUserDetail fullUserDetail = new FullUserDetail();
        
        if(userdetailRepository.findById(username).isPresent())
        {
            fullUserDetail.setUserdetail(userdetailRepository.findById(username).get());
        }       
        
        fullUserDetail.setTypes(usertypelinkRepository.findByUsername(username).stream().
                map(Usertypelink::getType).collect(Collectors.toList()));
        
         return fullUserDetail;


    }

    public List<String> getAllUsernamesByType(String type)
    {
        return usertypelinkRepository.findByType(type).stream().
                map(Usertypelink::getUsername).collect(Collectors.toList());
    }

}
