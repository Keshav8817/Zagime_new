package org.cyfwms.initialcontact.service;
import org.cyfwms.initialcontact.dto.ICContactNotesDto;
import org.springframework.http.ResponseEntity;
public interface ICContactNotesService {
    ICContactNotesDto saveContactNotes(ICContactNotesDto initialContactContactNotesDto);
    ICContactNotesDto readContactNotes(Long fileDetailsID);
    void removeContactNotes(Long contactNotesId);
}
