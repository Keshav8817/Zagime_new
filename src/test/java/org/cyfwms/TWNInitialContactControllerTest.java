package org.cyfwms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cyfwms.initialcontact.dto.*;
import org.cyfwms.initialcontact.service.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class TWNInitialContactControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICFileDetailsService iCFileDetailsService;

    @MockBean
    private ICIncidentReportService iCIncidentReportService;

    @MockBean
    private ICPatientCareInfoService iCPatientCareInfoService;

    @MockBean
    private ICPresentConcernsService iCPresentConcernsService;

    @MockBean
    private ICReferralInfoService iCReferralInfoService;

    @MockBean
    private ICContactNotesService iCContactNotesService;

    @MockBean
    private ICContactNotesSearchService iCContactNotesSearchService;


    @MockBean
    private ICSearchService searchService;


    @MockBean
    private ICCommonDataService iCCommonDataService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void readAllFileDetailsTest()throws Exception{
        ICFileDetailsDto icFileDetailsDto=ICFileDetailsDto.builder().fileDetailsId(1L).clientName("rajat").caseworker("no").fileNumber(2L).build();

        Mockito.when(iCFileDetailsService.readAllFileDetails(any())).thenReturn(icFileDetailsDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/initialcontactservice/readAllFileDetails/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientName").value("rajat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.caseworker").value("no"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileNumber").value(2))
                .andExpect(status().isOk());

    }

    @Test
    public void saveAllFileDetailsTest()throws Exception{
        ICFileDetailsDto icFileDetailsDto = ICFileDetailsDto.builder()
                .fileDetailsId(2L)
                .participantId(1L)
                .status("Active")
                .startingDate(LocalDate.of(2021,04,01))
                .dateClosed(LocalDate.of(2021,05,01))
                .clientName("rajat")
                .caseworker("no")
                .fileNumber(5L).build();

        Mockito.when(iCFileDetailsService.saveAllFileDetails(any(ICFileDetailsDto.class))).thenReturn(icFileDetailsDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/initialcontactservice/saveAllFileDetails")
                        .content(objectMapper.writeValueAsString(icFileDetailsDto))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.participantId", is(1)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.clientName", is("rajat")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.caseworker", is("no")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.fileNumber",is(5)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.startingDate", is(LocalDate.of(2021,04,01).toString())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.dateClosed", is(LocalDate.of(2021,05,01).toString())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.status",is("Active")));

                   Mockito.verify(iCFileDetailsService).saveAllFileDetails(Mockito.any(ICFileDetailsDto.class));

    }
    @Test
    public void readAllIncidentReportsTest()throws Exception{
        ICIncidentReportDto icIncidentReportDto=ICIncidentReportDto.builder().fileDetailsId(1L).incidentReportId(2L).reportedBy("rajat").incidentLocation("newyork").build();

        Mockito.when(iCIncidentReportService.readAllIncidentReports(any())).thenReturn(icIncidentReportDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/initialcontactservice/readAllIncidentReports/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.incidentReportId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reportedBy").value("rajat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.incidentLocation").value("newyork"))
                .andExpect(status().isOk());

  }

    @Test
    public void saveAllIncidentReportsTest()throws Exception{

        ICIncidentReportDto icIncidentReportDto= ICIncidentReportDto.builder()
              .fileDetailsId(2L)
              .incidentLocation("newyork")
              .actionPlan("no")
              .witnesses("yes")
              .build();

      Mockito.when(iCIncidentReportService.saveAllIncidentReports(any(ICIncidentReportDto.class))).thenReturn(icIncidentReportDto);

      mockMvc.perform(put("/v1/initialcontactservice/saveAllIncidentReports")
              .content(objectMapper.writeValueAsString(icIncidentReportDto))
              .contentType(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").value(2))
              .andExpect(MockMvcResultMatchers.jsonPath("$.witnesses",is("yes")))
              .andExpect(MockMvcResultMatchers.jsonPath("$.incidentLocation").value("newyork"))
              .andExpect(MockMvcResultMatchers.jsonPath("$.actionPlan").value("no"))
              .andExpect(status().isCreated());
  }

    @Test
    public void readAllPatientCareInfoTest()throws Exception{

        ICPatientCareInfoDto icPatientCareInfoDto=ICPatientCareInfoDto.builder()
                .patientCareInfoId(1L)
                .typeOfPatient("fever")
                .fileDetailsId(2L)
                .build();

        Mockito.when(iCPatientCareInfoService.readAllPatientCareInfo(any())).thenReturn(icPatientCareInfoDto);

        mockMvc.perform(get("/v1/initialcontactservice/readAllPatientCareInfo/2"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientCareInfoId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.typeOfPatient").value("fever"))
                .andExpect(status().isOk());
    }
    @Test
    public void saveAllPatientCareInfoTest()throws Exception{

        ICPatientCareInfoDto icPatientCareInfoDto=ICPatientCareInfoDto.builder()
                .patientCareInfoId(1L)
                .typeOfPatient("fever")
                .fileDetailsId(2L)
                .build();

        Mockito.when(iCPatientCareInfoService.saveAllPatientCareInfo(any(ICPatientCareInfoDto.class))).thenReturn(icPatientCareInfoDto);

        mockMvc.perform(put("/v1/initialcontactservice/saveAllPatientCareInfo")
                .content(objectMapper.writeValueAsString(icPatientCareInfoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientCareInfoId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.typeOfPatient").value("fever"))
                .andExpect(status().isCreated());
    }

      @Test
      public void readPresentConcernsTest()throws Exception{
          ICPresentConcernsDto icPresentConcernsDto=ICPresentConcernsDto.builder()
                  .fileDetailsId(1L)
                  .presentConcernsId(2L)
                  .situation("nosituation")
                  .build();

          Mockito.when(iCPresentConcernsService.readPresentConcerns(any())).thenReturn(icPresentConcernsDto);

          mockMvc.perform(get("/v1/initialcontactservice/readAllPresentConcerns/1"))
                  .andDo(print())
                  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                  .andExpect(MockMvcResultMatchers.jsonPath("$.presentConcernsId").value(2))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.situation").value("nosituation"))
                  .andExpect(status().isOk());
      }
      @Test
      public void savePresentConcernsTest()throws Exception{

          ICPresentConcernsDto icPresentConcernsDto=ICPresentConcernsDto.builder()
                  .fileDetailsId(1L)
                  .presentConcernsId(2L)
                  .situation("nosituation")
                  .build();

          Mockito.when(iCPresentConcernsService.savePresentConcerns(any())).thenReturn(icPresentConcernsDto);

          mockMvc.perform(put("/v1/initialcontactservice/saveAllPresentConcerns")
                        .content(objectMapper.writeValueAsString(icPresentConcernsDto))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.presentConcernsId").value(2))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.situation").value("nosituation"))
                        .andExpect(status().isCreated());
    }
    @Test
    public void readAllReferralInfoTest()throws Exception{
        ICReferralInfoDto icReferralInfoDto=ICReferralInfoDto.builder()
                .fileDetailsId(1L)
                .referralInfoId(2L)
                .address("nosituation")
                .agencyName("infotect")
                .name("rajat")
                .build();

        Mockito.when(iCReferralInfoService.readAllReferralInfo(any())).thenReturn(icReferralInfoDto);

        mockMvc.perform(get("/v1/initialcontactservice/readAllReferralInfo/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.referralInfoId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("nosituation"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agencyName").value("infotect"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("rajat"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveAllReferralInfoTest()throws Exception{
        ICReferralInfoDto icReferralInfoDto=ICReferralInfoDto.builder()
                .fileDetailsId(1L)
                .referralInfoId(2L)
                .address("nosituation")
                .agencyName("infotect")
                .name("rajat")
                .build();

        Mockito.when(iCReferralInfoService.saveAllReferralInfo(any())).thenReturn(icReferralInfoDto);

        mockMvc.perform(put("/v1/initialcontactservice/saveAllReferralInfo")
                .content(objectMapper.writeValueAsString(icReferralInfoDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.referralInfoId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("nosituation"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agencyName").value("infotect"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("rajat"))
                .andExpect(status().isCreated());
    }

    @Test
    public void readContactNotesTest()throws Exception{
        ICContactNotesDto icContactNotesDto=ICContactNotesDto.builder()
                .fileDetailsId(1L)
                .contactNotesId(2L)
                .name("nosituation")
                .additionalInformation("infotect")
                .casePlanProgress("rajat")
                .build();

        Mockito.when(iCContactNotesService.readContactNotes(any())).thenReturn(icContactNotesDto);

        mockMvc.perform(get("/v1/initialcontactservice/readAllContactNotes/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactNotesId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("nosituation"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.additionalInformation").value("infotect"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.casePlanProgress").value("rajat"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveContactNotesTest()throws Exception{
        ICContactNotesDto icContactNotesDto=ICContactNotesDto.builder()
                .fileDetailsId(1L)
                .contactNotesId(2L)
                .name("nosituation")
                .additionalInformation("infotect")
                .casePlanProgress("rajat")
                .build();

        Mockito.when(iCContactNotesService.saveContactNotes(any(ICContactNotesDto.class))).thenReturn(icContactNotesDto);

        mockMvc.perform(put("/v1/initialcontactservice/saveAllContactNotes")
                .content(objectMapper.writeValueAsString(icContactNotesDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactNotesId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("nosituation"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.additionalInformation").value("infotect"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.casePlanProgress").value("rajat"))
                .andExpect(status().isCreated());
    }

    @Test
    public void searchICContactNotesTest()throws Exception{

        List<ICContactNotesSearchResultsDto> icContactNotesSearchResultsDtoList=new ArrayList<>();
        icContactNotesSearchResultsDtoList.add(ICContactNotesSearchResultsDto.builder().fileDetailsId(1L)
                .name("sonu").additionalInformation("visa").casePlanProgress("progress").needAddress("indore").worker("work").date(LocalDate.ofEpochDay(10/12/1994)).result("negative").build());
        icContactNotesSearchResultsDtoList.add(ICContactNotesSearchResultsDto.builder().fileDetailsId(2L)
                .name("monu").additionalInformation("visa").casePlanProgress("progress").needAddress("pune").worker("works").date(LocalDate.ofEpochDay(10/12/1994)).result("positive").build());

        Mockito.when(iCContactNotesSearchService.search(any())).thenReturn(icContactNotesSearchResultsDtoList);
        mockMvc.perform(get("/v1/initialcontactservice/searchContactNotes/1/indore")
                        .content(objectMapper.writeValueAsString(icContactNotesSearchResultsDtoList))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.size()",
                        is(icContactNotesSearchResultsDtoList.size())));
    }

    @Test
    public void removeContactNotesTest()throws Exception{

        long contactNotesId = 1L;
        willDoNothing().given(iCContactNotesService).removeContactNotes(contactNotesId);

        ResultActions response = mockMvc.perform(delete("/v1/initialcontactservice/removeContactNotes/1", contactNotesId));

        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void searchInitialContactsTest()throws Exception{

        List<ICSearchResultsDto> icSearchResultsDtoList=new ArrayList<>();
        icSearchResultsDtoList.add(ICSearchResultsDto.builder()
                .fileDetailsId(1L)
                .fileNumber(3L)
                .clientName("harsh")
                .caseworker("yes")
                .startingDate(LocalDate.of(2022,02,01))
                .status("ACTIVE").build());


        Mockito.when(searchService.search(any())).thenReturn(icSearchResultsDtoList);
        mockMvc.perform(get("/v1/initialcontactservice/search/harsh/null/null/null/null")
                        .content(objectMapper.writeValueAsString(icSearchResultsDtoList))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.size()",
                          is(icSearchResultsDtoList.size())));
    }
    @Test
    public void removeICFileDetailsTest()throws Exception{
        long fileNumber = 2L;
        willDoNothing().given(iCFileDetailsService).removeICFileDetails(fileNumber);

        ResultActions response = mockMvc.perform(delete("/v1/initialcontactservice/remove/{fileNumber}", fileNumber));

        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void iCCommonDataTest()throws Exception{
        ICCommonDataDto icCommonDataDto=ICCommonDataDto.builder()
                .fileDetailsId(1L)
                .fileNumber(2L)
                .participantId(3L)
                .clientName("ronak")
                .caseworker("yes")
                .startingDate(LocalDate.of(2021,05,06))
                .dateClosed(LocalDate.of(2021,06,07))
                .status("ACTIVE").build();
        Mockito.when(iCCommonDataService.iCCommonData(any())).thenReturn(icCommonDataDto);

        mockMvc.perform(get("/v1/initialcontactservice/readAll/2"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileDetailsId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileNumber").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantId").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientName").value("ronak"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.caseworker").value("yes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startingDate").value(LocalDate.of(2021,05,06).toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateClosed").value(LocalDate.of(2021,06,07).toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ACTIVE"))
                .andExpect(status().isOk());


    }

}
