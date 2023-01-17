package org.cyfwms.initialcontact.service;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.initialcontact.dto.ICContactNotesDto;
import org.cyfwms.initialcontact.entity.ICContactNotes;
import org.cyfwms.initialcontact.repository.ICContactNotesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
@Service
@Slf4j
public class ICContactNotesServiceImpl implements ICContactNotesService {
    @Autowired
    private ICContactNotesRepository iCContactNotesRepository;
    @Override
    public ICContactNotesDto saveContactNotes(ICContactNotesDto iCContactNotesDto) {
        log.info("Inside SaveContactNotes InitialContact");
        ICContactNotes iCContactNotes = null;
        if (iCContactNotesDto.getContactNotesId() == 0) {
            iCContactNotes = new ICContactNotes();
            BeanUtils.copyProperties(iCContactNotesDto, iCContactNotes);
            iCContactNotes.setStatus("ACTIVE");
        } else {
            iCContactNotes = iCContactNotesRepository.findById(iCContactNotesDto.getContactNotesId()).get();
            BeanUtils.copyProperties(iCContactNotesDto, iCContactNotes);
        }
        iCContactNotes = iCContactNotesRepository.save(iCContactNotes);
        iCContactNotesDto.setContactNotesId(iCContactNotes.getContactNotesId());
        log.info("Exit SaveContactNotes InitialContact");
        return iCContactNotesDto;
    }
    @Override
    public ICContactNotesDto readContactNotes(Long contactNotesId) {
        log.info("Inside ReadContactNotes InitialContact");
        ICContactNotesDto iCContactNotesDto = new ICContactNotesDto();
        if (contactNotesId != 0) {
            Optional<ICContactNotes> iCContactNotes = iCContactNotesRepository.findById(contactNotesId);

            if (iCContactNotes.isPresent()) {
                if (iCContactNotes.get().getStatus().equals("ACTIVE")){
                BeanUtils.copyProperties(iCContactNotes.get(), iCContactNotesDto);
            }
            }
        }
        log.info("Exit ReadContactNotes InitialContact");
            return iCContactNotesDto;

    }
    @Override
    public void removeContactNotes(Long contactNotesId) {
        log.info("Inside RemoveContactNotes InitialContact");
        Optional<ICContactNotes> iCContactNotesOpt = iCContactNotesRepository.findById(contactNotesId);
        if(iCContactNotesOpt.isPresent()){
            ICContactNotes iCContactNotes = iCContactNotesOpt.get();
            iCContactNotes.setStatus("INACTIVE");
            iCContactNotesRepository.save(iCContactNotes);
            log.info("Exit RemoveContactNotes InitialContact");
        }
    }
}
