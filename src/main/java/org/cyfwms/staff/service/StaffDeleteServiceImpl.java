package org.cyfwms.staff.service;

import lombok.extern.slf4j.Slf4j;
import org.cyfwms.common.exception.I18Constants;
import org.cyfwms.common.exception.MessageUtil;
import org.cyfwms.common.exception.NoSuchElementFoundException;
import org.cyfwms.participant.entity.HouseholdMember;
import org.cyfwms.staff.entity.StaffContactInformation;
import org.cyfwms.staff.entity.StaffInventory;
import org.cyfwms.staff.entity.StaffJobAndBanking;
import org.cyfwms.staff.entity.StaffMedicalAndEmergency;
import org.cyfwms.staff.repository.StaffContactInformationRepository;
import org.cyfwms.staff.repository.StaffInventoryRepository;
import org.cyfwms.staff.repository.StaffJobAndBankingRepository;
import org.cyfwms.staff.repository.StaffMedicalAndEmergencyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StaffDeleteServiceImpl implements StaffDeleteService{
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private StaffContactInformationRepository staffContactInformationRepo;
    @Autowired
    private StaffJobAndBankingRepository staffJobAndBankingRepo;
    @Autowired
    private StaffMedicalAndEmergencyRepository staffMedicalAndEmergencyRepo;
    @Autowired
    private StaffInventoryRepository staffInventoryRepo;
    @Override
    public void removeContactInformation(Long staffContactInformationId) {
        log.info("Inside RemoveParticipantContact");
        StaffContactInformation pc =
                staffContactInformationRepo.findById(staffContactInformationId).filter(a->a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(staffContactInformationId))));
        pc.setStatus("INACTIVE");
        log.info("Exit RemoveParticipantContact");
        staffContactInformationRepo.save(pc);
    }

    @Override
    public void removeJobAndBanking(Long staffJobAndBankingId) {
        log.info("Inside RemoveJobAndBanking");
        StaffJobAndBanking pc =
                staffJobAndBankingRepo.findById(staffJobAndBankingId).filter(a->a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(staffJobAndBankingId))));
        pc.setStatus("INACTIVE");
        log.info("Exit RemoveJobAndBanking");
        staffJobAndBankingRepo.save(pc);
    }

    @Override
    public void removeMedicalEmergency(Long staffMedicalAndEmergencyId) {
        log.info("Inside RemoveMedicalEmergency");
        StaffMedicalAndEmergency pc =
                staffMedicalAndEmergencyRepo.findById(staffMedicalAndEmergencyId).filter(a->a.getStatus().equalsIgnoreCase("ACTIVE"))
                        .orElseThrow(() -> new NoSuchElementFoundException(
                                messageUtil.getLocalMessage(I18Constants.NO_ITEM_FOUND.getKey(),
                                        String.valueOf(staffMedicalAndEmergencyId))));
        pc.setStatus("INACTIVE");
        log.info("Exit RemoveMedicalEmergency");
        staffMedicalAndEmergencyRepo.save(pc);
    }

    @Override
    public void removeInventories(Long staffId) {
        log.info("Inside RemoveHouseholdMember");
        List<StaffInventory> InventoriesList = staffInventoryRepo.findByStaffId(staffId)
                .stream().map(inventory -> {
                    StaffInventory staffInventory = new StaffInventory();
                    BeanUtils.copyProperties(inventory, staffInventory);
                    staffInventory.setStatus("INACTIVE");
                    staffInventoryRepo.save(staffInventory);
                    return staffInventory;
                }).collect(Collectors.toList());
    }
}
