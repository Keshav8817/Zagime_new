package org.cyfwms.participant.service;

public interface ParticipantDeleteService {
    void removeHouseholdMember(Long participantId);

    void removeParticipantContact(Long participantContactId);

    void removeEducationAndEmployment(Long participantId);

    void removeCriminalHistory(Long criminalHistoryId);


    void removeFamilyPhysician(Long participantId);

    void removeCounselorCFSWorker(Long participantId);

    void removeParticipantOtherInformation(Long participantOtherInfoId);
}
