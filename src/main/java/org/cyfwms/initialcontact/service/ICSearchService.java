package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICSearchCriteriaDto;
import org.cyfwms.initialcontact.dto.ICSearchResultsDto;
import org.cyfwms.initialcontact.repository.ICSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ICSearchService {
     @Autowired
     ICSearchRepository iCSearchRepository ;
    public List<ICSearchResultsDto> search(ICSearchCriteriaDto iCSearchCriteriaDto){
        return  iCSearchRepository.searchInitialContacts(iCSearchCriteriaDto);
    }

}
