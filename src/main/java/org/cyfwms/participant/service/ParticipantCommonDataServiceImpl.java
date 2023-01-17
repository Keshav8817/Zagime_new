package org.cyfwms.participant.service;

import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.participant.dto.*;
import com.google.common.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.participant.dto.ParticipantCommonDataDto;
import org.cyfwms.participant.entity.*;
import org.cyfwms.participant.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ParticipantCommonDataServiceImpl implements ParticipantCommonDataService {
    @Autowired
    private ParticipantRepository participantRepo;

    @Autowired
    private ParticipantContactRepository participantContactRepo;

    @Autowired
    private EducationRepository educationRepo;

    @Autowired
    private EmploymentRepository employmentRepo;

    @Autowired
    private CriminalHistoryRepository criminalHistoryRepo;

    @Autowired
    private HouseholdMemberRepository hhMemberRepo;

    @Autowired
    private ParticipantOtherInformationRepository participantOtherInformationRepo;

    @Autowired
    private FamilyPhysicianRepository fpRepository;
    @Autowired
    CounselorCFSWorkerRepository cfsWorkerRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Override
    public ParticipantCommonDataDto readParticipantCommonData(Long referenceId) {
        log.info("Inside RaedParticipantCommonData");
        ParticipantCommonDataDto participantCommonDataDto = new ParticipantCommonDataDto();
        if (referenceId != 0) {
            Participant participant = readReference(referenceId);
            if (participant != null) {
                System.out.println(participant.getParticipantId());
                Long participantId = participant.getParticipantId();
                BeanUtils.copyProperties(participant, participantCommonDataDto);

                // Participant Contact
                Optional<ParticipantContact> pContactOtp = Optional
                        .ofNullable(participantContactRepo.findByParticipantId(participantId))
                        .filter(pc -> pc.getStatus().equalsIgnoreCase("ACTIVE"));
                if (pContactOtp.isPresent()) {
                    ParticipantContactDto participantContactDto = new ParticipantContactDto();
                    BeanUtils.copyProperties(pContactOtp.get(), participantContactDto);
                    participantCommonDataDto.setParticipantContact(participantContactDto);
                }

                // Education
                Optional<Education> educationOpt = Optional
                        .ofNullable(educationRepo.findByParticipantId(participantId));
                if (educationOpt.isPresent()) {
                    EducationDto educationDto = new EducationDto();
                    BeanUtils.copyProperties(educationOpt.get(), educationDto);
                    participantCommonDataDto.setEducation(educationDto);
                }

                // Employment
                Optional<Employment> employmentOpt = Optional
                        .ofNullable(employmentRepo.findByParticipantId(participantId));
                if (employmentOpt.isPresent()) {
                    EmploymentDto employmentDto = new EmploymentDto();
                    BeanUtils.copyProperties(employmentOpt.get(), employmentDto);
                    participantCommonDataDto.setEmployment(employmentDto);
                }

                // Criminal History
                Optional<CriminalHistory> criminalHistoryOpt = Optional
                        .ofNullable(criminalHistoryRepo.findByParticipantId(participantId));
                if (criminalHistoryOpt.isPresent()) {
                    CriminalHistory criminalHistory = criminalHistoryOpt.get();
                    CriminalHistoryDto criminalHistoryDto = new CriminalHistoryDto();
                    List<CriminalHistoryRecordDto> criminalHistoryRecordDtoList = new ArrayList<>();
                    List<CriminalHistoryRecord> criminalHistoryRecordList = criminalHistory
                            .getCriminalHistoryRecordList()
                            .stream()
                            .filter(chRecord -> chRecord.getStatus().equalsIgnoreCase("ACTIVE"))
                            .map(cHistoryRecord -> {
                                if (cHistoryRecord.getArrestDate() == null) {
                                    cHistoryRecord.setArrestDate(LocalDate.of(1, 1, 1));
                                }
                                return cHistoryRecord;
                            })
                            .collect(Collectors.toList());
                    criminalHistoryRecordDtoList = criminalHistoryRecordList.stream().map(chRecordDto -> {
                        CriminalHistoryRecordDto criminalHistoryRecordDto = new CriminalHistoryRecordDto();
                        BeanUtils.copyProperties(chRecordDto, criminalHistoryRecordDto);
                        return criminalHistoryRecordDto;
                    }).collect(Collectors.toList());
                    BeanUtils.copyProperties(criminalHistory, criminalHistoryDto);
                    criminalHistoryDto.setCriminalHistoryRecordList(criminalHistoryRecordDtoList);
                    participantCommonDataDto.setCriminalHistory(criminalHistoryDto);
                }

                List<HouseholdMemberDto> hmDtoList = hhMemberRepo.findByParticipantId(participantId)
                        .stream().map(hm -> {
                            HouseholdMemberDto hmDTO = new HouseholdMemberDto();
                            BeanUtils.copyProperties(hm, hmDTO);
                            if (hmDTO.getDateOfBirth() == null) {
                                hmDTO.setDateOfBirth(LocalDate.of(1, 1, 1));
                            }
                            return hmDTO;
                        }).collect(Collectors.toList());
                participantCommonDataDto.setHouseholdMember(hmDtoList);

                List<FamilyPhysicianDto> fpDtoList = new ArrayList<FamilyPhysicianDto>();
                if (participantId != 0) {
                    fpDtoList = fpRepository.findByParticipantId(participantId)
                            .stream()
                            .map(fp -> {
                                FamilyPhysicianDto fpDto = new FamilyPhysicianDto();
                                BeanUtils.copyProperties(fp, fpDto);
                                return fpDto;
                            }).collect(Collectors.toList());
                }
                participantCommonDataDto.setFamilyPhysicians(fpDtoList);

                List<CounselorCFSWorkersDto> counselorCFSWorkersDtoList = new ArrayList<CounselorCFSWorkersDto>();
                if (participantId != 0) {
                    counselorCFSWorkersDtoList = cfsWorkerRepository.findByParticipantId(participantId)
                            .stream()
                            .map(counselorCFSWorker -> {
                                CounselorCFSWorkersDto ccWorkerDto = new CounselorCFSWorkersDto();
                                BeanUtils.copyProperties(counselorCFSWorker, ccWorkerDto);
                                if (ccWorkerDto.getStartDate() == null) {
                                    ccWorkerDto.setStartDate(LocalDate.of(1, 1, 1));
                                }
                                if (ccWorkerDto.getEndDate() == null) {
                                    ccWorkerDto.setEndDate(LocalDate.of(1, 1, 1));
                                }
                                return ccWorkerDto;
                            }).collect(Collectors.toList());
                }
                participantCommonDataDto.setCounselorCFSWorker(counselorCFSWorkersDtoList);

                Optional<ParticipantOtherInformation> pOtherInfoOtp = Optional
                        .ofNullable(participantOtherInformationRepo.findByParticipantId(participantId));
                if (pContactOtp.isPresent()) {
                    ParticipantOtherInformationServiceDto pOtherInfoDto = new ParticipantOtherInformationServiceDto();
                    BeanUtils.copyProperties(pOtherInfoOtp.get(), pOtherInfoDto);
                    participantCommonDataDto.setParticipantOtherInfo(pOtherInfoDto);
                }

            }
        }
        log.info("Exit ReadParticipantCommonData");
        return participantCommonDataDto;
    }

    private Participant readReference(Long referenceId) {
        log.info("Inside ReadReference");
        Participant participant = null;
        Optional<Participant> participantOpt = Optional
                .ofNullable(participantRepo.findByReferenceId(referenceId).filter(p -> p.getStatus().equals("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(messageUtil
                                .getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(), String.valueOf(referenceId)))));
        if (participantOpt.isPresent()) {
            participant = participantOpt.get();
        }
        log.info("Exit ReadReference");
        return participant;
    }
}
