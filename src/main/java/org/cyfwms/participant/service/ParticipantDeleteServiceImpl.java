package org.cyfwms.participant.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.participant.entity.*;
import org.cyfwms.participant.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ParticipantDeleteServiceImpl implements ParticipantDeleteService {
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private ParticipantContactRepository participantContactRepo;
    @Autowired
    private HouseholdMemberRepository householdMemberRepo;
    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private EmploymentRepository employmentRepo;
    @Autowired
    private CriminalHistoryRepository criminalHistoryRepository;
    @Autowired
    private FamilyPhysicianRepository fPRepository;
    @Autowired
    private CounselorCFSWorkerRepository counselorCFSWorkerRepo;
    @Autowired
    private ParticipantOtherInformationRepository participantOtherInformationRepo;

    @Override
    public void removeHouseholdMember(Long participantId) {
        log.info("Inside RemoveHouseholdMember");
        List<HouseholdMember> hmDtoList = householdMemberRepo.findByParticipantId(participantId)
                .stream().map(hm -> {
                    HouseholdMember householdMember = new HouseholdMember();
                    BeanUtils.copyProperties(hm, householdMember);
                    householdMember.setStatus("INACTIVE");
                    householdMemberRepo.save(householdMember);
                    return householdMember;
                }).collect(Collectors.toList());
    }

    @Override
    public void removeParticipantContact(Long participantContactId) {
        log.info("Inside RemoveParticipantContact");
        ParticipantContact pc =
                participantContactRepo.findById(participantContactId).filter(a->a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(participantContactId))));
        log.info("Exit RemoveParticipantContact");
        participantContactRepo.delete(pc);
    }

    @Override
    public void removeEducationAndEmployment(Long participantId) {
        log.info("Inside RemoveEducation");
        Education education =
                educationRepository.findByparticipantId(participantId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(participantId))));

        if (education.getStatus().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(participantId)));
        }
        education.setStatus("INACTIVE");
        log.info("Exit RemoveEducation");
        educationRepository.save(education);

        log.info("Inside RemoveEmployment");
        Employment e =
                employmentRepo.findByparticipantId(participantId)
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(participantId))));

        if (e.getStatus().equalsIgnoreCase("INACTIVE")) {
            throw new NoSuchElementFoundException(
                    messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                            String.valueOf(participantId)));
        }
        e.setStatus("INACTIVE");
        log.info("Exit RemoveEmployment");
        employmentRepo.save(e);
    }

    @Override
    public void removeCriminalHistory(Long criminalHistoryId) {
        log.info("Inside RemoveCriminalHistory");
        CriminalHistory c =
                criminalHistoryRepository.findById(criminalHistoryId).filter(a->a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(criminalHistoryId))));
        c.setStatus("INACTIVE");
        log.info("Exit RemoveCriminalHistory");
        criminalHistoryRepository.save(c);
    }

    @Override
    public void removeFamilyPhysician(Long participantId) {
        log.info("Inside RemoveHouseholdMember");
        List<FamilyPhysician> fpDtoList = fPRepository.findByParticipantId(participantId)
                .stream().map(fp -> {
                    FamilyPhysician familyPhysician = new FamilyPhysician();
                    BeanUtils.copyProperties(fp, familyPhysician);
                    familyPhysician.setStatus("INACTIVE");
                    fPRepository.save(familyPhysician);
                    return familyPhysician;
                }).collect(Collectors.toList());
    }
    @Override
    public void removeCounselorCFSWorker(Long participantId) {
        log.info("Inside RemoveCounselorCFSWorker");
        List<CounselorCFSWorker> hmDtoList = counselorCFSWorkerRepo.findByParticipantId(participantId)
                .stream().map(cw -> {
                    CounselorCFSWorker counselorCFSWorker = new CounselorCFSWorker();
                    BeanUtils.copyProperties(cw, counselorCFSWorker);
                    counselorCFSWorker.setStatus("INACTIVE");
                    counselorCFSWorkerRepo.save(counselorCFSWorker);
                    return counselorCFSWorker;
                }).collect(Collectors.toList());

    }

    @Override
    public void removeParticipantOtherInformation(Long participantOtherInfoId) {
        log.info("Inside RemoveParticipantOtherInformation");
        ParticipantOtherInformation pi =
                participantOtherInformationRepo.findById(participantOtherInfoId).filter(a->a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(participantOtherInfoId))));
        pi.setStatus("INACTIVE");
        log.info("Exit RemoveParticipantOtherInformation");
        participantOtherInformationRepo.save(pi);
    }
}
