package org.cyfwms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cyfwms.common.dto.ReminderDto;
import org.cyfwms.participant.api.ParticipantController;
import org.cyfwms.participant.dto.*;
import org.cyfwms.participant.service.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = CYFWMSServerApplication.class)
public class ParticipantControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParticipantController participantController;


    @MockBean
    private ParticipantContactService participantContactService;

    @MockBean
    private ParticipantService participantService;

    @MockBean
    private HouseholdMemberService householdMemberService;

    @MockBean
    private CriminalHistoryService criminalHistoryService;

    @MockBean
    private FamilyPhysicianService familyPhysicianService;

    @MockBean
    private CounselorCFSWorkerService counselorCFSWorkerService;

    @MockBean
    private ParticipantOtherInformationService participantOtherInformationService;

    @MockBean
    private EducationAndEmploymentService educationAndEmploymentService;

    @MockBean
    private ParticipantSearchService participantSearchService;

    @MockBean
    private ParticipantCommonDataService participantCommonDataService;


    @Autowired
    private ObjectMapper objectMapper;




    @Test
    public void readParticipantIdentityTest() throws Exception {

        ParticipantIdentityDto participantIdentityDto = ParticipantIdentityDto.builder()
                .firstname("raj")
                .middleName("verma")
                .gender("male")
                .referenceId(4L)
                .participantId(1L)
                .dateOfBirth(LocalDate.of(2000,02,01))
                .participantImageType("json").build();

        Mockito.when(participantService.readParticipantIdentity(anyLong())).thenReturn(participantIdentityDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/participantservice/readParticipantIdentity/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("raj"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.middleName").value("verma"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("male"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveParticipantIdentityTest() throws Exception {
        ParticipantIdentityDto participantIdentityDto = new ParticipantIdentityDto();
        participantIdentityDto.setReferenceId(2L);
        participantIdentityDto.setParticipantId(1L);
        participantIdentityDto.setFirstname("raj");
        participantIdentityDto.setParticipantImageId(3L);

        BufferedImage img;
        img = ImageIO.read(new File("C:\\Users\\rajeev\\Pictures\\Screenshots\\Screenshot12.jpg"));
        WritableRaster raster = img.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
        byte[] testImage = data.getData();
        participantIdentityDto.setImage(testImage);

        MockMultipartFile  image = new MockMultipartFile("image", "", "application/json", "{\"image\": \"C:\\Users\\rajeev\\Pictures\\Screenshots\\Screenshot12.jpg\"}".getBytes());

        when(participantService.saveParticipantIdentity(participantIdentityDto , image)).thenReturn(participantIdentityDto);

        mockMvc.perform(multipart("/v1/participantservice/saveParticipantIdentity").file(image).param("image", "test-title")
                .content(objectMapper.writeValueAsString(participantIdentityDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(405))
                .andDo(print());


    }

    @Test
    public void removeParticipantTest() throws Exception {
        long referenceId = 9L;

        willDoNothing().given(participantService).removeParticipant(referenceId);

        mockMvc.perform(delete("/v1/participantservice/removeParticipant/9"))
                .andDo(print());

    }
    @Test
    public void readParticipantContactTest() throws Exception {

        ParticipantContactDto participantContactDto = ParticipantContactDto.builder().participantId(1L).participantContactId(2L).addressLine1("vijaynagar").addressLine2("vijaynagar")
                .city("indore").emailAddress("yash@gmail.com").homePhone("1234").postalCode("4567").province("india").workPhone("1234").cellPhone("1234")
                .build();

        Mockito.when(participantContactService.readParticipantContact(anyLong())).thenReturn(participantContactDto);

        mockMvc.perform(get("/v1/participantservice/readParticipantContact/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantContactId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressLine1").value("vijaynagar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressLine2").value("vijaynagar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("indore"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailAddress").value("yash@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.homePhone").value("1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.postalCode").value("4567"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.province").value("india"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.workPhone").value("1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cellPhone").value("1234"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveParticipantContactTest() throws Exception {

        ParticipantContactDto participantContactDto = ParticipantContactDto.builder().participantId(2L)
                .participantContactId(5L)
                .addressLine1("rau")
                .addressLine2("rau")
                .city("indore")
                .province("no")
                .postalCode("123")
                .homePhone("12345")
                .workPhone("12345")
                .cellPhone("12345")
                .emailAddress("yash@gmail.com").build();

        Mockito.when(participantContactService.saveParticipantContact(any(ParticipantContactDto.class))).thenReturn(participantContactDto);


        mockMvc.perform(MockMvcRequestBuilders.put("/v1/participantservice/saveParticipantContact")
                        .content(objectMapper.writeValueAsString(participantContactDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantContactId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantId", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressLine1",
                        is("rau")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addressLine2",
                        is("rau")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city",
                        is("indore")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.province",
                        is("no")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.postalCode",
                        is("123")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.homePhone",
                        is("12345")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.workPhone",
                        is("12345")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cellPhone",
                        is("12345")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailAddress",
                        is("yash@gmail.com")));
    }

    @Test
    public void getAllHouseholdMembersTest() throws Exception {

        List<HouseholdMemberDto> hmDtoList=new ArrayList<>();
        hmDtoList.add(HouseholdMemberDto.builder().householdMemberId(2L).participantId(4L).dateOfBirth(LocalDate.of(2021,05,12)).relationship("brother").name("ankit").gender("male").residing("India").build());
        given(householdMemberService.getAllHouseholdMembers(any())).willReturn(hmDtoList);

        mockMvc.perform(get("/v1/participantservice/getAllHouseholdMembers/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(hmDtoList.size())));

    }

    @Test
    public void saveHouseholdMembersTest() throws Exception {

        List<HouseholdMemberDto> hmDtoList=new ArrayList<>();
        hmDtoList.add(HouseholdMemberDto.builder().participantId(1L).householdMemberId(2L).name("ankit").gender("male").residing("India").dateOfBirth(LocalDate.of(2021,05,12)).relationship("brother").build());

        given(householdMemberService.saveAllHouseholdMembers(any())).willReturn(hmDtoList);

        mockMvc.perform(put("/v1/participantservice/saveAllHouseholdMembers")
                        .content(objectMapper.writeValueAsString(hmDtoList))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(hmDtoList.size())));

    }
    @Test
    public void removeHouseholdMembers() throws Exception{

        long householdMemberId = 1L;
        willDoNothing().given(householdMemberService).removeHouseholdMembers(householdMemberId);

        ResultActions response = mockMvc.perform(delete("/v1/participantservice/removeAddMoreHouseholdMember/{householdMemberId}", householdMemberId));

        response.andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void readCriminalHistoryTest() throws Exception {

        CriminalHistoryDto criminalHistoryDto = CriminalHistoryDto.builder().criminalHistoryId(2L).participantId(3L).conditions("true").parole(true).criminalHistoryRecordList(new ArrayList<>()).probation(true)
                .courtWorkerAndContactInfo("info").build();

        Mockito.when(criminalHistoryService.readCriminalHistory(anyLong())).thenReturn(criminalHistoryDto);

        mockMvc.perform(get("/v1/participantservice/readCriminalHistory/3"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.criminalHistoryId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.conditions").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.parole").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.criminalHistoryRecordList").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.probation").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courtWorkerAndContactInfo").value("info"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveCriminalHistoryTest() throws Exception {

        CriminalHistoryDto criminalHistoryDto = CriminalHistoryDto.builder().criminalHistoryId(2L).participantId(3L).conditions("true").parole(true).criminalHistoryRecordList(new ArrayList<>()).probation(true)
                .courtWorkerAndContactInfo("info").build();

        Mockito.when(criminalHistoryService.saveCriminalHistory(any(CriminalHistoryDto.class))).thenReturn(criminalHistoryDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/participantservice/saveCriminalHistory")
                        .content(objectMapper.writeValueAsString(criminalHistoryDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.criminalHistoryId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.conditions").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.parole").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.criminalHistoryRecordList").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.probation").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courtWorkerAndContactInfo").value("info"))
                .andExpect(status().isCreated());

    }
    @Test
    public void removeCriminalHistoryRecord() throws Exception{

        long criminalHistoryRecordId = 1L;
        willDoNothing().given(criminalHistoryService).removeCriminalHistoryRecord(criminalHistoryRecordId);

        ResultActions response = mockMvc.perform(delete("/v1/participantservice/removeAddMoreCriminalHistory/{criminalhistoryrecordid}", criminalHistoryRecordId));

        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getAllFamilyPhysiciansTest() throws Exception {

        List<FamilyPhysicianDto> familyPhysicianDtoList=new ArrayList<>();
        familyPhysicianDtoList.add(FamilyPhysicianDto.builder().participantId(5L).familyPhysicianId(2L).name("ankit").phone("123").listOfMedication("no list").cell("12345").build());

        given(familyPhysicianService.getAllFamilyPhysicians(any())).willReturn(familyPhysicianDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/participantservice/getAllFamilyPhysicians/5"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(familyPhysicianDtoList.size())));
    }

    @Test
    public void saveAllFamilyPhysicianTest() throws Exception {

        List<FamilyPhysicianDto> familyPhysicianDtoList=new ArrayList<>();
        familyPhysicianDtoList.add(FamilyPhysicianDto.builder().participantId(5L).familyPhysicianId(2L).name("ankit").phone("123").listOfMedication("no list").cell("12345").build());

        given(familyPhysicianService.saveAllFamilyPhysicians(any())).willReturn(familyPhysicianDtoList);

        mockMvc.perform(put("/v1/participantservice/saveAllFamilyPhysicians")
                        .content(objectMapper.writeValueAsString(familyPhysicianDtoList))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(familyPhysicianDtoList.size())));
    }
    @Test
    public void removeFamilyPhysicianTest() throws Exception{

        long familyPhysicianId = 1L;
        willDoNothing().given(familyPhysicianService).removeFamilyPhysician(familyPhysicianId);

        ResultActions response = mockMvc.perform(delete("/v1/participantservice/removeAddMoreFamilyPhysician/{familyPhysicianId}", familyPhysicianId));

        response.andExpect(status().isAccepted())
                .andDo(print());
    }

    @Test
    public void getAllCounselorCFSWorkersTest()throws Exception{

        List<CounselorCFSWorkersDto> counselorCFSWorkersDtoList=new ArrayList<>();
        counselorCFSWorkersDtoList.add(CounselorCFSWorkersDto.builder().participantId(5L).counselorCFSWorkerId(2L).name("ankit").role("employee").contactInformation("info").startDate(LocalDate.of(2021,01,02)).endDate(LocalDate.of(2022,01,02)).build());

        given(counselorCFSWorkerService.getAllCounselorCFSWorkers(any())).willReturn(counselorCFSWorkersDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/participantservice/getAllCounselorCFSWorkers/6"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(counselorCFSWorkersDtoList.size())));

    }

    @Test
    public void saveAllCounselorCFSWorkersTest()throws Exception{
        List<CounselorCFSWorkersDto> counselorCFSWorkersDtoList=new ArrayList<>();
        counselorCFSWorkersDtoList.add(CounselorCFSWorkersDto.builder().participantId(5L).counselorCFSWorkerId(2L).name("ankit").role("employee").contactInformation("info").startDate(LocalDate.of(2021,01,02)).endDate(LocalDate.of(2022,01,02)).build());
        given(counselorCFSWorkerService.saveAllCounselorCFSWorkers(any())).willReturn(counselorCFSWorkersDtoList);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/participantservice/saveAllCounselorCFSWorkers")
                        .content(objectMapper.writeValueAsString(counselorCFSWorkersDtoList))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(counselorCFSWorkersDtoList.size())));
    }
    @Test
    public void removeAddMoreCounselorCFSWorkerTest()throws Exception{

        long counselorCFSWorkerId = 1L;
        willDoNothing().given(counselorCFSWorkerService).removeCounselorCFSWorker(counselorCFSWorkerId);

        ResultActions response = mockMvc.perform(delete("/v1/participantservice/removeAddMoreCounselorCFSWorker/{counselorcfsworkerid}", counselorCFSWorkerId));

        response.andExpect(status().isAccepted())
                .andDo(print());
    }

    @Test
    public void readParticipantOtherInformationTest() throws Exception {
        ParticipantOtherInformationServiceDto participantOtherInformationServiceDto = ParticipantOtherInformationServiceDto.builder()
                .participantId(1l)
                .participantOtherInfoId(2l)
                .effectiveCopingSkills("copy")
                .skills("logic")
                .weakness("strength")
                .experiences("no")
                .strength("strong").build();

        Mockito.when(participantOtherInformationService.readParticipantOtherInformation(1l)).thenReturn(participantOtherInformationServiceDto);

        mockMvc.perform(get("/v1/participantservice/readParticipantOtherInformation/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantOtherInfoId", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.experiences", is("no")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.strength", is("strong")))
                .andExpect(status().isOk());

    }
    @Test
    public void saveParticipantOtherInformationTest()throws Exception{
        ParticipantOtherInformationServiceDto participantOtherInformationServiceDto = ParticipantOtherInformationServiceDto.builder()
                .participantOtherInfoId(2L)
                .participantId(1L)
                .effectiveCopingSkills("copy")
                .skills("logic")
                .weakness("strength")
                .experiences("no")
                .strength("strong").build();

        Mockito.when(participantOtherInformationService.saveParticipantOtherInformation(any(ParticipantOtherInformationServiceDto.class))).thenReturn(participantOtherInformationServiceDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/participantservice/saveParticipantOtherInformation")
                        .content(objectMapper.writeValueAsString(participantOtherInformationServiceDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantOtherInfoId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.effectiveCopingSkills", is("copy")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.skills", is("logic")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weakness", is("strength")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.strength", is("strong")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.experiences", is("no")))
                .andExpect(status().isOk());

    }
    @Test
    public void readEmploymentAndEducationTest() throws Exception {
        EducationAndEmploymentCompositeDto educationAndEmploymentCompositeDto=EducationAndEmploymentCompositeDto.builder()
                .educationId(1L).employmentId(2L).participantId(3L).desiredProfession("professional").employed("yes").grade("A").typeOfEmployment("student")
                .school("wishwood").attendingSchool("little angles").build();

        Mockito.when(educationAndEmploymentService.readEducationAndEmployment(anyLong())).thenReturn(educationAndEmploymentCompositeDto);

        mockMvc.perform(get("/v1/participantservice/readEmploymentAndEducation/3"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.educationId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employmentId", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.school", is("wishwood")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.attendingSchool", is("little angles")));

    }

    @Test
    public void saveEmploymentAndEducationTest() throws Exception {
        EducationAndEmploymentCompositeDto educationAndEmploymentCompositeDto=EducationAndEmploymentCompositeDto.builder()
                .educationId(1L).employmentId(2L).participantId(3L)
                .desiredProfession("professional").employed("yes").grade("A").typeOfEmployment("student")
                .school("wishwood").attendingSchool("little angles").build();

        Mockito.when(educationAndEmploymentService.saveEducationAndEmployment(any(EducationAndEmploymentCompositeDto.class))).thenReturn(educationAndEmploymentCompositeDto);

        mockMvc.perform(put("/v1/participantservice/saveEmploymentAndEducation")
                        .content(objectMapper.writeValueAsString(educationAndEmploymentCompositeDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.educationId",is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employmentId", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.school", is("wishwood")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.attendingSchool", is("little angles")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.desiredProfession",is("professional")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employed", is("yes")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.grade", is("A")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.typeOfEmployment", is("student")));

    }

    @Test
    public void searchParticipantsTest()throws Exception{
        List<ParticipantSearchResultsDto> participantSearchCriteriaDto=new ArrayList<>();
        participantSearchCriteriaDto.add(ParticipantSearchResultsDto.builder().referenceId(1L)
                .firstname("sonu").surname("dwivedi").middleName("kumar").city("indore").cellPhone("12345").dateOfBirth(LocalDate.ofEpochDay(10/12/1994)).maritalStatus("married").build());
        participantSearchCriteriaDto.add(ParticipantSearchResultsDto.builder().referenceId(2L)
                .firstname("monu").surname("dwivedi").middleName("kumar").city("mhow").cellPhone("12345789").dateOfBirth(LocalDate.ofEpochDay(11/11/1995)).maritalStatus("married").build());

        Mockito.when(participantSearchService.search(any())).thenReturn(participantSearchCriteriaDto);
        mockMvc.perform(get("/v1/participantservice/searchParticipants/1/null/null/null/null/null/null/null")
                        .content(objectMapper.writeValueAsString(participantSearchCriteriaDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(participantSearchCriteriaDto.size())));
    }
    @Test
    public void readAllOutPutParticipantTest()throws Exception{

        ParticipantCommonDataDto participantCommonDataDto=ParticipantCommonDataDto.builder()
                .participantId(1L).firstname("shubh").gender("male").counselorCFSWorker(new ArrayList<>()).build();

        Mockito.when(participantCommonDataService.readParticipantCommonData(any())).thenReturn(participantCommonDataDto);

        mockMvc.perform(get("/v1/participantservice/readAllOutputParticipant/4")
                        .content(objectMapper.writeValueAsString(participantCommonDataDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.participantId",is(1)))
                .andExpect(jsonPath("$.firstname",is("shubh")))
                .andExpect(jsonPath("$.gender",is("male")));

    }


}
