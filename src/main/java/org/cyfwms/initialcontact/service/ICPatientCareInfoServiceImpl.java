package org.cyfwms.initialcontact.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyfwms.initialcontact.dto.ICPatientCareInfoDto;
import org.cyfwms.initialcontact.entity.ICPatientCareInfo;
import org.cyfwms.initialcontact.entity.PatientCareInfoInpatient;
import org.cyfwms.initialcontact.entity.PatientCareInfoOutpatient;
import org.cyfwms.initialcontact.repository.ICPatientCareInfoRepository;
import org.cyfwms.initialcontact.repository.PatientCareInfoInpatientRepository;
import org.cyfwms.initialcontact.repository.PatientCareInfoOutpatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
@Slf4j
public class ICPatientCareInfoServiceImpl implements ICPatientCareInfoService {
    @Autowired
    ICPatientCareInfoRepository iCPatientCareInfoRepository;

    @Autowired
    PatientCareInfoOutpatientRepository patientCareInfoOutpatientRepository;
    @Autowired
    PatientCareInfoInpatientRepository patientCareInfoInpatientRepository;

    @Override
    public ICPatientCareInfoDto readAllPatientCareInfo(Long fileDetailsId) {
        log.info("Inside InitialContact ReadAllPatientCareInfo");
        ICPatientCareInfoDto iCPatientCareInfoDto = new ICPatientCareInfoDto();
        if (fileDetailsId != 0) {
            ICPatientCareInfo iCPatientCareInfo = iCPatientCareInfoRepository.findByFileDetailsId(fileDetailsId);
            if (iCPatientCareInfo != null) {
            PatientCareInfoInpatient p=patientCareInfoInpatientRepository.findByPatientCareInfoId(iCPatientCareInfo.getPatientCareInfoId());
            iCPatientCareInfo.setInpatient(p);
            PatientCareInfoOutpatient p1=patientCareInfoOutpatientRepository.findByPatientCareInfoId(iCPatientCareInfo.getPatientCareInfoId());
            iCPatientCareInfo.setOutpatient(p1);


                BeanUtils.copyProperties(iCPatientCareInfo, iCPatientCareInfoDto);
            }
        }
        log.info("Exit InitialContact ReadAllPatientCareInfo");
            return iCPatientCareInfoDto;
    }

    @Override
    public ICPatientCareInfoDto saveAllPatientCareInfo(ICPatientCareInfoDto iCPatientCareInfoDto) {
        log.info("Inside InitialContact SaveAllPatientCareInfo");
        ICPatientCareInfo iCPatientCareInfo=null;
        if(iCPatientCareInfoDto.getPatientCareInfoId()==0) {
            iCPatientCareInfo = new ICPatientCareInfo();
            BeanUtils.copyProperties(iCPatientCareInfoDto, iCPatientCareInfo);
            iCPatientCareInfo.setStatus("ACTIVE");
        }
        else {
            iCPatientCareInfo = iCPatientCareInfoRepository
                    .findById(iCPatientCareInfoDto.getPatientCareInfoId()).get();
            BeanUtils.copyProperties(iCPatientCareInfoDto, iCPatientCareInfo);
        }
        iCPatientCareInfo = iCPatientCareInfoRepository.save(iCPatientCareInfo);

        if(iCPatientCareInfo.getTypeOfPatient()!=null) {
            if (iCPatientCareInfo.getTypeOfPatient().equals("Outpatient")) {
                PatientCareInfoOutpatient outpatient = new PatientCareInfoOutpatient();
                Optional<PatientCareInfoInpatient> op = Optional.ofNullable(patientCareInfoInpatientRepository.findByPatientCareInfoId(iCPatientCareInfoDto.getPatientCareInfoId()));
                if (op.isPresent()) {
                    PatientCareInfoInpatient inpatient = new PatientCareInfoInpatient();
                    inpatient = op.get();
                    inpatient.setStatus("INACTIVE");
                    patientCareInfoInpatientRepository.save(inpatient);
                }
                iCPatientCareInfo.getOutpatient().setStatus("ACTIVE");
                BeanUtils.copyProperties(iCPatientCareInfo.getOutpatient(), outpatient);
                outpatient.setPatientCareInfoId(iCPatientCareInfo.getPatientCareInfoId());
                patientCareInfoOutpatientRepository.save(outpatient);
                iCPatientCareInfoDto.getOutpatient().setOutpatientId(iCPatientCareInfo.getOutpatient().getOutpatientId());
                iCPatientCareInfoDto.setPatientCareInfoId(iCPatientCareInfo.getPatientCareInfoId());
                iCPatientCareInfoDto.getOutpatient().setPatientCareInfoId(iCPatientCareInfo.getOutpatient().getPatientCareInfoId());

            } else {
                Optional<PatientCareInfoOutpatient> op = Optional.ofNullable(patientCareInfoOutpatientRepository.findByPatientCareInfoId(iCPatientCareInfoDto.getPatientCareInfoId()));
                if (op.isPresent()) {
                    PatientCareInfoOutpatient outpatient = new PatientCareInfoOutpatient();
                    outpatient = op.get();
                    BeanUtils.copyProperties(op, outpatient);
                    outpatient.setStatus("INACTIVE");
                    patientCareInfoOutpatientRepository.save(outpatient);
                }
                iCPatientCareInfo.getInpatient().setStatus("ACTIVE");
                PatientCareInfoInpatient inpatient = new PatientCareInfoInpatient();
                BeanUtils.copyProperties(iCPatientCareInfo.getInpatient(), inpatient);
                inpatient.setPatientCareInfoId(iCPatientCareInfo.getPatientCareInfoId());
                inpatient = patientCareInfoInpatientRepository.save(inpatient);
                iCPatientCareInfoDto.setPatientCareInfoId(inpatient.getPatientCareInfoId());
                iCPatientCareInfoDto.getInpatient().setInpatientId(iCPatientCareInfo.getInpatient().getInpatientId());
                iCPatientCareInfoDto.getInpatient().setPatientCareInfoId(iCPatientCareInfo.getInpatient().getPatientCareInfoId());
            }
        }
        log.info("Exit InitialContact SaveAllPatientCareInfo");
        return iCPatientCareInfoDto;
    }
}

