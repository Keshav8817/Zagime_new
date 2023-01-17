package org.cyfwms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cyfwms.culturalprogram.dto.CPAIdentityDto;
import org.cyfwms.culturalprogram.dto.CPAParticipantDto;
import org.cyfwms.culturalprogram.dto.CPAParticipantSearchResultsDto;
import org.cyfwms.culturalprogram.dto.CPASearchResultsDto;
import org.cyfwms.culturalprogram.service.CPAIdentityService;
import org.cyfwms.culturalprogram.service.CPAParticipantSearchService;
import org.cyfwms.culturalprogram.service.CPAParticipantService;
import org.cyfwms.culturalprogram.service.CPASearchService;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
public class CulturalProgramControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CPAParticipantSearchService CPAParticipantSearchService;

    @MockBean
    private CPASearchService CPASearchService;

    @MockBean
    private CPAIdentityService CPAIdentityService;

    @MockBean
    private CPAParticipantService CPAParticipantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public  void readCpaIdentityTest() throws Exception {

        CPAIdentityDto cpaIdentityDto=CPAIdentityDto.builder()
                .culturalProgramId(1L)
                .name("amit")
                .caseworker("yes")
                .costOrParticipationDetails("nodetail")
                .notes("cultural")
                .outcomes("no")
                .sessionDetails("no")
                .status("ACTIVE")
                .totalCost("one")
                .startDate(LocalDate.of(2022,04,01))
                .endDate(LocalDate.of(2022,06,04)).totalParticipation("two").type("culture").build();

        Mockito.when(CPAIdentityService.readCpaIdentity(any())).thenReturn(cpaIdentityDto);

        mockMvc.perform(get("/v1/culturalprogandactservice/readCulturalProgAndAct/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.culturalProgramId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("amit"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.caseworker").value("yes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.costOrParticipationDetails").value("nodetail"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.notes").value("cultural"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.outcomes").value("no"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ACTIVE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalCost").value("one"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value(LocalDate.of(2022,04,01).toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value(LocalDate.of(2022,06,04).toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalParticipation").value("two"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("culture"))
                .andExpect(status().isOk());
    }
    @Test
    public  void saveCpaIdentityTest() throws Exception {

        CPAIdentityDto cpaIdentityDto=CPAIdentityDto.builder()
                .culturalProgramId(1L)
                .name("amit")
                .caseworker("yes")
                .costOrParticipationDetails("nodetail")
                .notes("cultural")
                .outcomes("no")
                .sessionDetails("no")
                .status("ACTIVE")
                .totalCost("one")
                .startDate(LocalDate.of(2022,04,01))
                .endDate(LocalDate.of(2022,06,04)).totalParticipation("two").type("culture").build();

        Mockito.when(CPAIdentityService.saveCpaIdentity(any())).thenReturn(cpaIdentityDto);

        mockMvc.perform(put("/v1/culturalprogandactservice/saveCulturalProgAndAct")
                .content(objectMapper.writeValueAsString(cpaIdentityDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.culturalProgramId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("amit"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.caseworker").value("yes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.costOrParticipationDetails").value("nodetail"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.notes").value("cultural"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.outcomes").value("no"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ACTIVE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalCost").value("one"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value(LocalDate.of(2022,04,01).toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value(LocalDate.of(2022,06,04).toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalParticipation").value("two"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("culture"))
                .andExpect(status().isCreated());
    }

    @Test
    public void searchCulturalProgAndActTest()throws Exception{

        List<CPASearchResultsDto> cpaSearchResultsDtoList=new ArrayList<>();
        cpaSearchResultsDtoList.add(CPASearchResultsDto.builder().CulturalProgramId(2L).name("ayush").type("worker").caseworker("yes")
                .referenceId(3L).status("ACTIVE").startDate(LocalDate.of(2021,01,02)).build());

        Mockito.when(CPASearchService.searchCulturalProgAndAct(any())).thenReturn(cpaSearchResultsDtoList);
        mockMvc.perform(get("/v1/culturalprogandactservice/culturalProgAndActSearch/null/ayush/null/null/null/null")
                .content(objectMapper.writeValueAsString(cpaSearchResultsDtoList))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(cpaSearchResultsDtoList.size())));
    }
    @Test
    public void removeCpaIdentityTest()throws Exception{
        long culturalProgramId=1L;
        willDoNothing().given(CPAIdentityService).removeCpaIdentity(culturalProgramId);

        mockMvc.perform(delete("/v1/culturalprogandactservice/removeCulturalProgAndAct/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void readCpaParticipantTest()throws Exception{
        CPAParticipantDto cpaParticipantDto=CPAParticipantDto.builder().participantId(1L).culturalProgramId(2L).participantCulturalProId(3L)
                .participant("participantcontact").notes("note").role("role").build();

        Mockito.when(CPAParticipantService.readCpaParticipant(any())).thenReturn(cpaParticipantDto);
        mockMvc.perform(get("/v1/culturalprogandactservice/readParticipantsCulturalAndAct/2"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.culturalProgramId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantCulturalProId").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participant").value("participantcontact"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.notes").value("note"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("role"))
                .andExpect(status().isOk());

    }
    @Test
    public void saveCpaParticipantTest()throws Exception{
        CPAParticipantDto cpaParticipantDto=CPAParticipantDto.builder().participantId(1L).culturalProgramId(2L).participantCulturalProId(3L)
                .participant("participantcontact").notes("note").role("role").build();

        Mockito.when(CPAParticipantService.saveCpaParticipant(any())).thenReturn(cpaParticipantDto);
        mockMvc.perform(put("/v1/culturalprogandactservice/saveParticipantCulturalProg")
                .content(objectMapper.writeValueAsString(cpaParticipantDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.culturalProgramId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participantCulturalProId").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participant").value("participantcontact"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.notes").value("note"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("role"))
                .andExpect(status().isCreated());

    }
    @Test
    public void removeCpaParticipantTest()throws Exception{
        long participantCulturalProId=3L;
        willDoNothing().given(CPAParticipantService).removeCpaParticipant(participantCulturalProId);

        mockMvc.perform(delete("/v1/culturalprogandactservice/removeParticipantCulturalProg/3"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void searchCulturalProgramTest()throws Exception{
        List<CPAParticipantSearchResultsDto> cpaParticipantSearchResultsDtoList=new ArrayList<>();
        cpaParticipantSearchResultsDtoList.add(CPAParticipantSearchResultsDto.builder().culturalProgramId(1L).participant("participant").role("admin")
                .participantCulturalProId(4L).notes("successfull").build());

        Mockito.when(CPAParticipantSearchService.searchParticipantCulturalProgAndAct(any())).thenReturn(cpaParticipantSearchResultsDtoList);

        mockMvc.perform(get("/v1/culturalprogandactservice/participantCulturalProgSearch/4")
                .content(objectMapper.writeValueAsString(cpaParticipantSearchResultsDtoList))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(cpaParticipantSearchResultsDtoList.size())));

    }
}
