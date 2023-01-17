package org.cyfwms.initialcontact.service;

import org.cyfwms.initialcontact.dto.ICContactNotesSearchCriteriaDto;
import org.cyfwms.initialcontact.dto.ICContactNotesSearchResultsDto;
import org.cyfwms.initialcontact.repository.ICContactNotesSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ICContactNotesSearchService {
    @Autowired
    ICContactNotesSearchRepository iCContactNotesSearchRepository;
    public List<ICContactNotesSearchResultsDto> search(ICContactNotesSearchCriteriaDto initialContactContactNotesSearchCriteriaDto) {
        return iCContactNotesSearchRepository.searchICContactNotes(initialContactContactNotesSearchCriteriaDto);
    }
}
